package com.alexander.mobile.locationpolling.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import com.alexander.mobile.locationpolling.network.SendLocationRequest;
import com.alexander.mobile.locationpolling.tasks.LocationPollingTask;

public class UserLocationPollerService extends Service {

    public static final String LOCATION_POLLING_TASK = "DriverLocationPollingTask";

    private LocationManager mLocationManager;
    private LocationPollingTask mPollingTask;
    private Handler mHandler;

    @Override
    public int onStartCommand(final Intent intent, int flags, final int startId) {

        super.onStartCommand(intent, flags, startId);
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        final int userId = intent.getIntExtra("user_id", 0);

        final LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(final Location location) {

                if ( Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(UserLocationPollerService.this,
                                android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&

                        ContextCompat.checkSelfPermission(UserLocationPollerService.this,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                    return;
                }

                mLocationManager.removeUpdates(this);

                final Runnable locationUpdatesRunnable = new Runnable() {
                    @Override
                    public void run() {

                        final SendLocationRequest request = SendLocationRequest.builder()
                                .setUserId(userId)
                                .setLatitude(location.getLatitude())
                                .setLongtitude(location.getLongitude())
                                .build();

                        mPollingTask.updateLocation(request);
                    }
                };

                mHandler.post(locationUpdatesRunnable);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        final Criteria criteria = new Criteria();
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);

        mPollingTask = new LocationPollingTask(this, intent, LOCATION_POLLING_TASK);
        mPollingTask.start();

        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER ) || mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(criteria,true),
                    0,
                    0,
                    locationListener,mPollingTask.getLooper());
        }

        mHandler = new Handler(mPollingTask.getLooper());

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
