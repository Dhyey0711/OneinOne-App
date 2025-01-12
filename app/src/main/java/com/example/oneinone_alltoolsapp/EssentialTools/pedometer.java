package com.example.oneinone_alltoolsapp.EssentialTools;


import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import com.example.oneinone_alltoolsapp.R;
public class pedometer extends AppCompatActivity implements SensorEventListener {

    private TextView tvStepCount, tvDistance, tvTime;
    private Button btnStartStop, btnReset;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private boolean isCountingSteps = false;
    private int stepCount = 0;

    private long startTimeMillis;
    private Handler timerHandler;
    private Runnable timerRunnable;

    private static final int STEP_THRESHOLD = 12; // Lower threshold for better accuracy
    private static final float STEP_LENGTH = 0.75f; // Average step length in meters

    private static final long DEBOUNCE_DELAY_MS = 500; // Time in milliseconds to debounce steps
    private long lastStepTime = 0;

    private boolean dialogShown = false; // Flag to check if dialog is already shown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        tvStepCount = findViewById(R.id.tvStepCount);
        tvDistance = findViewById(R.id.tvDistance);
        tvTime = findViewById(R.id.tvTime);
        btnStartStop = findViewById(R.id.btnStartStop);
        btnReset = findViewById(R.id.btnReset);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCountingSteps) {
                    startCountingSteps();
                } else {
                    stopCountingSteps();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPedometer();
            }
        });

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTimeMillis;
                int seconds = (int) (millis / 1000) % 60;
                int minutes = (int) ((millis / (1000 * 60)) % 60);
                int hours = (int) ((millis / (1000 * 60 * 60)) % 24);

                tvTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                timerHandler.postDelayed(this, 1000);
            }
        };
    }

    private void startCountingSteps() {
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
            btnStartStop.setText("Stop");
            btnReset.setEnabled(false); // Disable reset button while counting
            isCountingSteps = true;
            stepCount = 0;
            startTimeMillis = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            dialogShown = false; // Reset dialog shown flag
        } else {
            Toast.makeText(this, "Accelerometer not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopCountingSteps() {
        if (isCountingSteps) {
            sensorManager.unregisterListener(this);
            btnStartStop.setText("Start");
            btnReset.setEnabled(true); // Enable reset button when not counting
            isCountingSteps = false;
            timerHandler.removeCallbacks(timerRunnable);
        }
    }

    private void resetPedometer() {
        if (!isCountingSteps) {
            stepCount = 0;
            tvStepCount.setText("Step Count: " + stepCount);
            tvDistance.setText("Distance: 0.00 m");
            tvTime.setText("Time: 00:00:00");
            lastStepTime = 0; // Reset the last step time
            dialogShown = false; // Reset dialog shown flag
        } else {
            Toast.makeText(this, "Stop counting before resetting", Toast.LENGTH_SHORT).show();
        }
    }

    private void showStepCountDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage("You've reached 1000 steps!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialogShown = true; // Mark the dialog as shown
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calculate acceleration magnitude
            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

            // Simple peak detection and debouncing
            long currentTime = System.currentTimeMillis();
            if (acceleration > STEP_THRESHOLD && (currentTime - lastStepTime) > DEBOUNCE_DELAY_MS) {
                stepCount++;
                lastStepTime = currentTime;
                tvStepCount.setText("Step Count: " + stepCount);

                // Calculate distance in meters
                float distance = stepCount * STEP_LENGTH;
                tvDistance.setText(String.format("Distance: %.2f m", distance));

                // Show dialog if step count exceeds 1000 and dialog is not yet shown
                if (stepCount > 1000 && !dialogShown) {
                    showStepCountDialog();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isCountingSteps) {
            stopCountingSteps(); // Ensure the sensor is unregistered when the activity is destroyed
        }
    }
}