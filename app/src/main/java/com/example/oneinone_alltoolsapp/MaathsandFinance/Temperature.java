package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class Temperature extends AppCompatActivity {

    private Spinner spinnerSourceUnit, spinnerTargetUnit;
    private TextInputEditText editTextSourceTemperature, editTextTargetTemperature;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        spinnerSourceUnit = findViewById(R.id.spinnerSourceUnit);
        spinnerTargetUnit = findViewById(R.id.spinnerTargetUnit);
        editTextSourceTemperature = findViewById(R.id.editTextSourceTemperature);
        editTextTargetTemperature = findViewById(R.id.editTextTargetTemperature);
        btnConvert = findViewById(R.id.btnConvert);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String input = editTextSourceTemperature.getText().toString();

        if (!input.isEmpty()) {
            double temperature = Double.parseDouble(input);
            String sourceUnit = spinnerSourceUnit.getSelectedItem().toString();
            String targetUnit = spinnerTargetUnit.getSelectedItem().toString();
            double result;

            if (sourceUnit.equals(targetUnit)) {
                result = temperature; // If source and target units are the same, no conversion needed
            } else {
                result = convertTemperatureValue(temperature, sourceUnit, targetUnit);
            }

            editTextTargetTemperature.setText(String.format("%.2f", result));
        } else {
            showResultDialog("Please enter a valid temperature value.");
        }
    }

    private double convertTemperatureValue(double temperature, String sourceUnit, String targetUnit) {
        switch (sourceUnit) {
            case "Celsius":
                return convertFromCelsius(temperature, targetUnit);
            case "Fahrenheit":
                return convertFromFahrenheit(temperature, targetUnit);
            case "Kelvin":
                return convertFromKelvin(temperature, targetUnit);
            default:
                return temperature;
        }
    }

    private double convertFromCelsius(double temperature, String targetUnit) {
        switch (targetUnit) {
            case "Fahrenheit":
                return (temperature * 9/5) + 32;
            case "Kelvin":
                return temperature + 273.15;
            default:
                return temperature;
        }
    }

    private double convertFromFahrenheit(double temperature, String targetUnit) {
        switch (targetUnit) {
            case "Celsius":
                return (temperature - 32) * 5/9;
            case "Kelvin":
                return (temperature - 32) * 5/9 + 273.15;
            default:
                return temperature;
        }
    }

    private double convertFromKelvin(double temperature, String targetUnit) {
        switch (targetUnit) {
            case "Celsius":
                return temperature - 273.15;
            case "Fahrenheit":
                return (temperature - 273.15) * 9/5 + 32;
            default:
                return temperature;
        }
    }

    private void showResultDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Conversion Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
