package com.example.oneinone_alltoolsapp.EssentialTools;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class FlashlightActivity extends AppCompatActivity {

    private Button btnToggleFlashlight;
    private SeekBar seekBarFrequency;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;
    private Handler handler;
    private Runnable blinkRunnable;
    private int blinkDelay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btnToggleFlashlight = findViewById(R.id.btnToggleFlashlight);
        seekBarFrequency = findViewById(R.id.seekBarFrequency);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        handler = new Handler();

        btnToggleFlashlight.setOnClickListener(v -> {
            if (blinkRunnable != null) {
                handler.removeCallbacks(blinkRunnable);
                blinkRunnable = null;
            }
            toggleFlashlight();
            updateButtonText();
        });

        seekBarFrequency.setMax(4); // Set seekbar max to 4 to allow 5 positions (0 to 4)

        seekBarFrequency.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {
                    // Ensure flashlight remains on when slider is at position 0
                    if (isFlashlightOn) {
                        // Stop blinking, but keep the flashlight on
                        if (blinkRunnable != null) {
                            handler.removeCallbacks(blinkRunnable);
                            blinkRunnable = null;
                        }
                        Toast.makeText(FlashlightActivity.this, "Flashlight remains on", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    blinkDelay = progress * 200; // 1st position = 0.2 sec, 2nd = 0.4 sec, 3rd = 0.6 sec, 4th = 0.8 sec
                    if (isFlashlightOn) {
                        startBlinking();
                    }
                    // Show toast message with blinking delay in seconds
                    String toastMessage = "Blinking delay: " + (blinkDelay / 100.0) + " seconds";
                    Toast.makeText(FlashlightActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Set the initial button text based on the flashlight state
        updateButtonText();
    }

    private void toggleFlashlight() {
        try {
            if (isFlashlightOn) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashlightOn = false;
            } else {
                cameraManager.setTorchMode(cameraId, true);
                isFlashlightOn = true;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void updateButtonText() {
        if (isFlashlightOn) {
            btnToggleFlashlight.setText("OFF");
        } else {
            btnToggleFlashlight.setText("ON");
        }
    }

    private void startBlinking() {
        if (blinkRunnable != null) {
            handler.removeCallbacks(blinkRunnable);
        }
        blinkRunnable = new Runnable() {
            @Override
            public void run() {
                toggleFlashlight();
                handler.postDelayed(this, blinkDelay);
            }
        };
        handler.post(blinkRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (blinkRunnable != null) {
            handler.removeCallbacks(blinkRunnable);
        }
        // Ensure flashlight is turned off when the app is destroyed
        if (isFlashlightOn) {
            try {
                cameraManager.setTorchMode(cameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
