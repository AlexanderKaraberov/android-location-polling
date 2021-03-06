package com.alexander.mobile.locationpolling.tasks;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.HandlerThread;

import com.alexander.mobile.locationpolling.application.LocationPollingApplication;
import com.alexander.mobile.locationpolling.network.APIService;
import com.alexander.mobile.locationpolling.network.SendLocationRequest;
import com.alexander.mobile.locationpolling.receivers.LocationWakefulReceiver;


import javax.inject.Inject;


public class LocationPollingTask extends HandlerThread {

    @Inject protected APIService apiService;

    private final Intent serviceIntent;
    private final Service serviceContext;

    public LocationPollingTask(Service service, Intent intent, String name) {

        super(name);

        this.serviceContext = service;
        this.serviceIntent = intent;

        LocationPollingApplication.getNetworkComponent().inject(this);
    }

    private void onPostExecute() {

        if (!LocationWakefulReceiver.completeWakefulIntent(serviceIntent)) {
            serviceContext.stopService(serviceIntent);
        }

        if (Build.VERSION.SDK_INT >= 18) {
            quitSafely();
        }
        else quit();
    }

    public void updateLocation(SendLocationRequest request) {

            apiService.updateLocationSynchronously(request);
            onPostExecute();
    }
}