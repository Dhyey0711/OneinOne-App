package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class StopWatch extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStartStop, btnReset;
    private boolean isRunning = false;
    private long startTime, timeInMilliseconds, timeSwapBuff, updateTime = 0L;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tvTimer = findViewById(R.id.tvTimer);
        btnStartStop = findViewById(R.id.btnStartStop);
        btnReset = findViewById(R.id.btnReset);

        // Set initial button text
        btnStartStop.setText("Start");

        btnStartStop.setOnClickListener(v -> {
            if (isRunning) {
                stopTimer();
            } else {
                startTimer();
            }
        });

        btnReset.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        if (!isRunning) {
            isRunning = true;
            btnStartStop.setText("Stop");
            startTime = System.currentTimeMillis() - updateTime;
            customHandler.postDelayed(updateTimerThread, 0);
        }
    }

    private void stopTimer() {
        if (isRunning) {
            isRunning = false;
            btnStartStop.setText("Start");
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
        }
    }

    private void resetTimer() {
        isRunning = false;
        btnStartStop.setText("Start");
        timeSwapBuff = 0;
        updateTime = 0;
        tvTimer.setText("00:00:00");
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = System.currentTimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updateTime % 1000);
            tvTimer.setText(String.format("%02d:%02d:%02d", mins, secs, milliseconds / 10));
            customHandler.postDelayed(this, 10); // Update every 10 milliseconds
        }
    };
}
