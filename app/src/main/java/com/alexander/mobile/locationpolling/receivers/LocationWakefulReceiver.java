package com.alexander.mobile.locationpolling.receivers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.alexander.mobile.locationpolling.services.UserLocationPollerService;

/**
 * Created by alexander on 12/7/15.
 */

public class LocationWakefulReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        final Intent locationTrackerIntent = new Intent(context, UserLocationPollerService.class);
        startWakefulService(context, locationTrackerIntent);
    }
}