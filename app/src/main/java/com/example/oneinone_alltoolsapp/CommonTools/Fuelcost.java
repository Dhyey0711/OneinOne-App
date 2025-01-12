package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.oneinone_alltoolsapp.R;
import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatDelegate;

public class Fuelcost extends AppCompatActivity {

    private EditText etTripDistance, etFuelEfficiency, etGasPrice;
    private Spinner spinnerDistanceUnit, spinnerEfficiencyUnit, spinnerPriceUnit;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuelcost);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        etTripDistance = findViewById(R.id.et_trip_distance);
        etFuelEfficiency = findViewById(R.id.et_fuel_efficiency);
        etGasPrice = findViewById(R.id.et_gas_price);
        spinnerDistanceUnit = findViewById(R.id.spinner_distance_unit);
        spinnerEfficiencyUnit = findViewById(R.id.spinner_efficiency_unit);
        spinnerPriceUnit = findViewById(R.id.spinner_price_unit);
        btnCalculate = findViewById(R.id.btn_calculate);

        // Setup spinners with unit options
        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this, R.array.distance_units, android.R.layout.simple_spinner_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistanceUnit.setAdapter(distanceAdapter);

        ArrayAdapter<CharSequence> efficiencyAdapter = ArrayAdapter.createFromResource(this, R.array.efficiency_units, android.R.layout.simple_spinner_item);
        efficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEfficiencyUnit.setAdapter(efficiencyAdapter);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(this, R.array.price_units, android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriceUnit.setAdapter(priceAdapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFuelCost();
            }
        });
    }

    private void calculateFuelCost() {
        String tripDistanceStr = etTripDistance.getText().toString();
        String fuelEfficiencyStr = etFuelEfficiency.getText().toString();
        String gasPriceStr = etGasPrice.getText().toString();

        if (tripDistanceStr.isEmpty() || fuelEfficiencyStr.isEmpty() || gasPriceStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal tripDistance = new BigDecimal(tripDistanceStr);
        BigDecimal fuelEfficiency = new BigDecimal(fuelEfficiencyStr);
        BigDecimal gasPrice = new BigDecimal(gasPriceStr);

        String distanceUnit = spinnerDistanceUnit.getSelectedItem().toString();
        String efficiencyUnit = spinnerEfficiencyUnit.getSelectedItem().toString();
        String priceUnit = spinnerPriceUnit.getSelectedItem().toString();

        // Convert units to a common base (kilometers and liters)
        if (distanceUnit.equals("Miles")) {
            tripDistance = tripDistance.multiply(new BigDecimal("1.60934")); // Convert miles to kilometers
        }

        if (efficiencyUnit.equals("MPG")) {
            fuelEfficiency = new BigDecimal("235.214583").divide(fuelEfficiency, 10, RoundingMode.HALF_UP); // Convert miles per gallon to liters per 100 km
        } else if (efficiencyUnit.equals("km/l")) {
            fuelEfficiency = new BigDecimal("100").divide(fuelEfficiency, 10, RoundingMode.HALF_UP); // Convert kilometers per liter to liters per 100 km
        }

        if (priceUnit.equals("per gallon")) {
            gasPrice = gasPrice.divide(new BigDecimal("3.78541"), 10, RoundingMode.HALF_UP); // Convert price per gallon to price per liter
        }

        BigDecimal fuelRequired = tripDistance.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP).multiply(fuelEfficiency);
        BigDecimal totalCost = fuelRequired.multiply(gasPrice);

        showResultDialog(totalCost);
    }

    private void showResultDialog(BigDecimal totalCost) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fuel Cost Calculation");
        builder.setMessage(String.format("Total Cost: %,.2f", totalCost));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}