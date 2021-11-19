package com.example.mymoviememoir.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

import com.example.mymoviememoir.MainActivity;
import com.example.mymoviememoir.R;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmService extends Service {

    public static final long FIVE_SECONDS = 5 * 1000;

    public static final long INTERVAL_WEEK = 7 * 24 * 60 * 60 * 1000;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service on", Toast.LENGTH_LONG).show();
        initAlarmManager();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service off", Toast.LENGTH_LONG).show();
    }

    private void initAlarmManager(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("alarm",1);
        PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + FIVE_SECONDS,
                FIVE_SECONDS, alarmIntent);
    }
}
