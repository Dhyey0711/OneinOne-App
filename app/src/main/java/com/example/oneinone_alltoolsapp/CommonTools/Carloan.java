package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import java.text.DecimalFormat;

public class Carloan extends AppCompatActivity {

    private EditText editTextVehiclePrice, editTextInterestRate, editTextMonths;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carloan);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        editTextVehiclePrice = findViewById(R.id.editTextVehiclePrice);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextMonths = findViewById(R.id.editTextMonths);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoan();
            }
        });
    }

    private void calculateLoan() {
        // Get inputs from EditText fields
        String vehiclePriceStr = editTextVehiclePrice.getText().toString().trim();
        String interestRateStr = editTextInterestRate.getText().toString().trim();
        String monthsStr = editTextMonths.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(vehiclePriceStr) || TextUtils.isEmpty(interestRateStr) || TextUtils.isEmpty(monthsStr)) {
            showErrorDialog("Please enter all fields");
            return;
        }

        // Convert inputs to numeric values
        double vehiclePrice = Double.parseDouble(vehiclePriceStr);
        double interestRate = Double.parseDouble(interestRateStr);
        int months = Integer.parseInt(monthsStr);

        // Calculate monthly payment
        double monthlyInterestRate = interestRate / 100 / 12;
        double monthlyPayment = (vehiclePrice * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -months));
        double totalPayment = monthlyPayment * months;
        double totalInterest = totalPayment - vehiclePrice;

        // Format results
        DecimalFormat df = new DecimalFormat("#.##");
        String monthlyPaymentFormatted = df.format(monthlyPayment);
        String totalPaymentFormatted = df.format(totalPayment);
        String totalInterestFormatted = df.format(totalInterest);

        // Display results in a dialog
        showResultDialog(monthlyPaymentFormatted, totalPaymentFormatted, totalInterestFormatted);
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showResultDialog(String monthlyPayment, String totalPayment, String totalInterest) {
        String resultMessage = "Monthly Payment: " + monthlyPayment + "\n"
                + "Total Payment: " + totalPayment + "\n"
                + "Total Interest: " + totalInterest;

        new AlertDialog.Builder(this)
                .setTitle("Loan Calculation Result")
                .setMessage(resultMessage)
                .setPositiveButton("OK", null)
                .show();
    }
}