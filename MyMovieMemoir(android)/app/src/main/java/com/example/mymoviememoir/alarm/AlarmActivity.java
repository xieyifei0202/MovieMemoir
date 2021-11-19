package com.example.mymoviememoir.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.mymoviememoir.R;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        startService(new Intent(getBaseContext(),AlarmService.class));
    }

}
