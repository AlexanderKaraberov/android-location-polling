package com.alexander.mobile.locationpolling.tasks;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.HandlerThread;
import android.util.Log;

import com.alexander.mobile.locationpolling.application.LocationPollingApplication;
import com.alexander.mobile.locationpolling.network.APIService;
import com.alexander.mobile.locationpolling.network.SendLocationRequest;
import com.alexander.mobile.locationpolling.receivers.LocationWakefulReceiver;


import javax.inject.Inject;


public class LocationPollingTask extends HandlerThread {

    @Inject protected APIService mApi;

    private final Intent mServiceIntent;
    private final Service mServiceContext;

    public LocationPollingTask(Service service, Intent intent, String name) {

        super(name);

        this.mServiceContext = service;
        this.mServiceIntent = intent;

        LocationPollingApplication.getNetworkComponent().inject(this);
    }

    private void onPostExecute() {

        if (!LocationWakefulReceiver.completeWakefulIntent(mServiceIntent)) {
            mServiceContext.stopService(mServiceIntent);
        }

        if (Build.VERSION.SDK_INT >= 18) {
            quitSafely();
        }
        else quit();
    }

    public void updateLocation(SendLocationRequest request) {

            mApi.updateLocationSynchronously(request);
            onPostExecute();
    }
}