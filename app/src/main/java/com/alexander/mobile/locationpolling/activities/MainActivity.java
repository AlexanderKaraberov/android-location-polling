package com.alexander.mobile.locationpolling.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alexander.mobile.locationpolling.R;
import com.alexander.mobile.locationpolling.services.UserLocationPollerService;

import org.joda.time.Minutes;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPolling(View v) {

        final Intent locationTrackerIntent = new Intent(this, UserLocationPollerService.class);

        locationTrackerIntent.putExtra("user_id", 42); //Pass here id of the user to associate location data

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 0, locationTrackerIntent, 0);

        //Repeat every three minutes
        alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                Minutes.THREE.toStandardDuration().getMillis(),
                pendingIntent);
    }

    public void stopPolling(View v) {

        alarmManager.cancel(pendingIntent);
    }

}
