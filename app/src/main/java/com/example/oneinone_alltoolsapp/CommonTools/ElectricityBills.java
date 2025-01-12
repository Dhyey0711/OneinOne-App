package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class ElectricityBills extends AppCompatActivity {

    private EditText etPowerConsumption, etHoursUsed;
    private Spinner spinnerCountry;
    private Button btnCalculate;

    private static final double[] COUNTRY_RATES = {
            0.20, // Default rate for unspecified country
            0.30, // Rate for USA
            0.25, // Rate for India
            0.35, // Rate for UK
            0.40, // Rate for Germany
            0.50  // Rate for Australia
    };

    private static final String[] COUNTRIES = {
            "Select Country",
            "USA",
            "India",
            "UK",
            "Germany",
            "Australia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_bills);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etPowerConsumption = findViewById(R.id.et_power_consumption);
        etHoursUsed = findViewById(R.id.et_hours_used);
        spinnerCountry = findViewById(R.id.spinner_country);
        btnCalculate = findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });
    }

    private void calculateBill() {
        try {
            double powerConsumption = Double.parseDouble(etPowerConsumption.getText().toString());
            double hoursUsed = Double.parseDouble(etHoursUsed.getText().toString());
            int selectedCountryPosition = spinnerCountry.getSelectedItemPosition();

            if (selectedCountryPosition == 0) {
                Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
                return;
            }

            double rate = COUNTRY_RATES[selectedCountryPosition - 1];
            double totalCost = powerConsumption * hoursUsed * rate;

            showResultDialog(totalCost);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResultDialog(double totalCost) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Electricity Bill");
        builder.setMessage(String.format("Your total electricity bill is %.2f", totalCost));
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
