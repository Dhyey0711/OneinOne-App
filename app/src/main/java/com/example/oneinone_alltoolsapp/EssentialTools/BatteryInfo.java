package com.example.oneinone_alltoolsapp.EssentialTools;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class BatteryInfo extends AppCompatActivity {
    private TextView batteryLevel;
    private TextView batteryType;
    private TextView batteryTemperature;
    private TextView powerSource;
    private TextView batteryStatus;
    private TextView batteryVoltage;
    private TextView batteryHealth;
    private TextView fastCharging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_info);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        batteryLevel = findViewById(R.id.battery_level);
        batteryType = findViewById(R.id.battery_type);
        batteryTemperature = findViewById(R.id.battery_temperature);
        powerSource = findViewById(R.id.power_source);
        batteryStatus = findViewById(R.id.battery_status);
        batteryVoltage = findViewById(R.id.battery_voltage);
        batteryHealth = findViewById(R.id.battery_health);
        fastCharging = findViewById(R.id.fast_charging);

        registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);

            if (batteryLevel != null) {
                batteryLevel.setText("Battery Level: " + (level * 100 / scale) + "%");
            }
            if (batteryType != null) {
                batteryType.setText("Battery Type: " + (technology != null ? technology : "Unknown"));
            }
            if (batteryTemperature != null) {
                batteryTemperature.setText("Temperature: " + (temperature / 10.0) + "°C");
            }
            if (batteryVoltage != null) {
                batteryVoltage.setText("Battery Voltage: " + voltage + "mV");
            }

            if (powerSource != null) {
                switch (plugged) {
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        powerSource.setText("Power Source: USB");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        powerSource.setText("Power Source: AC");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        powerSource.setText("Power Source: Wireless");
                        break;
                    default:
                        powerSource.setText("Power Source: Unknown");
                        break;
                }
            }

            if (batteryStatus != null) {
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        batteryStatus.setText("Battery Status: Charging");
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        batteryStatus.setText("Battery Status: Discharging");
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        batteryStatus.setText("Battery Status: Full");
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        batteryStatus.setText("Battery Status: Not Charging");
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        batteryStatus.setText("Battery Status: Unknown");
                        break;
                }
            }

            if (batteryHealth != null) {
                switch (health) {
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        batteryHealth.setText("Battery Health: Good");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        batteryHealth.setText("Battery Health: Overheat");
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        batteryHealth.setText("Battery Health: Dead");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        batteryHealth.setText("Battery Health: Over Voltage");
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        batteryHealth.setText("Battery Health: Unspecified Failure");
                        break;
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        batteryHealth.setText("Battery Health: Cold");
                        break;
                    default:
                        batteryHealth.setText("Battery Health: Unknown");
                        break;
                }
            }

            if (fastCharging != null) {
                // Fast charging status cannot be directly determined; assuming it's not supported.
                fastCharging.setText("Fast Charging: Not Supported");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryInfoReceiver);
    }
}