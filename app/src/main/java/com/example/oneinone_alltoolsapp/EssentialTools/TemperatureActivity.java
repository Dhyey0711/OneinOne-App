package com.example.oneinone_alltoolsapp.EssentialTools;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class TemperatureActivity extends AppCompatActivity {

    private TextView textViewTemperature;
    private TextView textViewTemperatureResult;
    private TextView textViewCondition;
    private TextView textViewConditionResult;
    private ImageView imageViewCondition;
    private Button buttonMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewTemperatureResult = findViewById(R.id.textViewTemperatureResult);
        textViewConditionResult = findViewById(R.id.textViewConditionResult);
        imageViewCondition = findViewById(R.id.imageViewCondition);
        buttonMeasure = findViewById(R.id.buttonMeasure);

        buttonMeasure.setOnClickListener(v -> measureTemperature());
    }

    private void measureTemperature() {
        // Get the temperature and condition
        float temperature = getMobileTemperature();
        String condition = getTemperatureCondition(temperature);

        // Update the TextViews with the temperature and condition
        textViewTemperatureResult.setText("Temperature: " + temperature + "°C");
        textViewConditionResult.setText("Condition: " + condition);



        // Display result in a dialog box with the phone's condition message
        showDialog(condition);
    }

    private float getMobileTemperature() {
        BatteryManager batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        if (batteryManager != null) {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = registerReceiver(null, ifilter);

            if (batteryStatus != null) {
                int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
                return temperature / 10.0f; // Convert to degrees Celsius
            }
        }
        return 0.0f; // Default value if temperature can't be retrieved
    }

    private String getTemperatureCondition(float temperature) {
        // Example condition logic based on temperature
        if (temperature < 15) {
            return "Cold";
        } else if (temperature < 45) {
            return "Mild";
        } else {
            return "Hot";
        }
    }



    private void showDialog(String condition) {
        String message;
        switch (condition) {
            case "Cold":
                message = "Your phone is in a cold environment. Consider warming it up.";
                break;
            case "Mild":
                message = "Your phone is in a mild environment. No action needed.";
                break;
            case "Hot":
                message = "Your phone is in a hot environment. It’s recommended to cool it down.";
                break;
            default:
                message = "Unable to determine the phone's condition.";
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Phone Condition")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
