package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;


import com.example.oneinone_alltoolsapp.R;

public class Jewellery extends AppCompatActivity {

    private EditText pricePerGramEditText;
    private EditText totalGramsEditText;
    private EditText makingChargesEditText;
    private EditText gstEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewellery);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        pricePerGramEditText = findViewById(R.id.price_per_gram);
        totalGramsEditText = findViewById(R.id.total_grams);
        makingChargesEditText = findViewById(R.id.making_charges);
        gstEditText = findViewById(R.id.gst);
        calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPrice();
            }
        });
    }

    private void calculateTotalPrice() {
        // Get the values from EditText fields
        String pricePerGramStr = pricePerGramEditText.getText().toString().trim();
        String totalGramsStr = totalGramsEditText.getText().toString().trim();
        String makingChargesStr = makingChargesEditText.getText().toString().trim();
        String gstStr = gstEditText.getText().toString().trim();

        // Check if any field is empty
        if (TextUtils.isEmpty(pricePerGramStr) || TextUtils.isEmpty(totalGramsStr) ||
                TextUtils.isEmpty(makingChargesStr) || TextUtils.isEmpty(gstStr)) {
            showErrorDialog("Please fill all fields");
            return;
        }

        // Parse the values to double
        double pricePerGram = Double.parseDouble(pricePerGramStr);
        double totalGrams = Double.parseDouble(totalGramsStr);
        double makingChargesPercent = Double.parseDouble(makingChargesStr);
        double gstPercent = Double.parseDouble(gstStr);

        // Calculate the total price of gold
        double totalPriceOfGold = pricePerGram * totalGrams;

        // Calculate the making charges
        double makingCharges = (makingChargesPercent / 100) * totalPriceOfGold;

        // Calculate the GST/further taxes
        double gst = (gstPercent / 100) * (totalPriceOfGold + makingCharges);

        // Calculate the final total price
        double finalTotalPrice = totalPriceOfGold + makingCharges + gst;

        // Display the result in a dialog
        showResultDialog(finalTotalPrice);
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showResultDialog(double finalTotalPrice) {
        String resultMessage = "Total Price: " + finalTotalPrice;

        new AlertDialog.Builder(this)
                .setTitle("Calculation Result")
                .setMessage(resultMessage)
                .setPositiveButton("OK", null)
                .show();
    }
}