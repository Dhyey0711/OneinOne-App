package com.example.oneinone_alltoolsapp.EssentialTools;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import java.io.File;

public class DeviceInfoActivity extends AppCompatActivity {

    private TextView deviceName, osVersion, apiLevel, manufacturer, model, resolution, batteryInfo, storageInfo, networkInfo;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        deviceName = findViewById(R.id.textView_device_name);
        osVersion = findViewById(R.id.textView_os_version);
        apiLevel = findViewById(R.id.textView_api_level);
        manufacturer = findViewById(R.id.textView_manufacturer);
        model = findViewById(R.id.textView_model);
        resolution = findViewById(R.id.textView_resolution);
        storageInfo = findViewById(R.id.textView_storage_info);

        displayDeviceInfo();
    }

    private void displayDeviceInfo() {
        deviceName.setText("Device Name: " + getDeviceName());
        osVersion.setText("OS Version: " + Build.VERSION.RELEASE);
        apiLevel.setText("API Level: " + Build.VERSION.SDK_INT);
        manufacturer.setText("Manufacturer: " + Build.MANUFACTURER);
        model.setText("Model: " + Build.MODEL);
        resolution.setText("Screen Resolution: " + getScreenResolution());
        storageInfo.setText("Storage Info: " + getStorageInfo());
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getScreenResolution() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return width + " x " + height;
    }



    private String getStorageInfo() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long available = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
        long total = (long) stat.getBlockCount() * (long) stat.getBlockSize();
        return "Available: " + available / (1024 * 1024 * 1024) + " GB, Total: " + total / (1024 * 1024 * 1024) + " GB";
    }

}
