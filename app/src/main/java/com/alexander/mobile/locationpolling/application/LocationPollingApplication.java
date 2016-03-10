package com.alexander.mobile.locationpolling.application;

import android.app.Application;

import com.alexander.mobile.locationpolling.injection.APIServiceComponent;
import com.alexander.mobile.locationpolling.injection.DaggerAPIServiceComponent;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by alexander on 12/7/15.
 */
public class LocationPollingApplication extends Application {

    private static APIServiceComponent networkComponent;

    @Override
    public void onCreate() {

        super.onCreate();

        networkComponent = DaggerAPIServiceComponent.builder().build();
        JodaTimeAndroid.init(this);
    }

    public static APIServiceComponent getNetworkComponent() {
        return networkComponent;
    }
}
