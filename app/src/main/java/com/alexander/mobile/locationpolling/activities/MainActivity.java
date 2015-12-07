package com.alexander.mobile.locationpolling.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alexander.mobile.locationpolling.R;
import com.alexander.mobile.locationpolling.services.UserLocationPollerService;

public class MainActivity extends AppCompatActivity {

    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startPolling(View v) {

        final Intent locationTrackerIntent = new Intent(this, UserLocationPollerService.class);
        locationTrackerIntent.putExtra("user_id", 42);

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mPendingIntent = PendingIntent.getService(this, 0, locationTrackerIntent, 0);

        final long POLLER_PERIOD =  60 * 1000;

        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                POLLER_PERIOD,
                mPendingIntent);
    }

    public void stopPolling(View v) {

        mAlarmManager.cancel(mPendingIntent);
    }

}
