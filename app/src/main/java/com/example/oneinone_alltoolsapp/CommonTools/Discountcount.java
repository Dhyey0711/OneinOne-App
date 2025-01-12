package com.example.oneinone_alltoolsapp.CommonTools;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class Discountcount extends AppCompatActivity {

    private EditText etPrice, etDiscount;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discountcount);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etPrice = findViewById(R.id.etprice);
        etDiscount = findViewById(R.id.tDiscount);
        btnCalculate = findViewById(R.id.btnCalculate);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDiscount();
            }
        });
    }

    private void calculateDiscount() {
        String priceStr = etPrice.getText().toString().trim();
        String discountStr = etDiscount.getText().toString().trim();

        // Check for empty input
        if (TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(discountStr)) {
            showErrorDialog("Please enter both price and discount percentage.");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            double discount = Double.parseDouble(discountStr);

            // Validate input values
            if (price <= 0 || discount <= 0 || discount >= 100) {
                showErrorDialog("Please enter a valid price and discount percentage (0 < discount < 100).");
                return;
            }

            // Calculate saved amount and final price
            double savedAmount = price * (discount / 100);
            double finalPrice = price - savedAmount;

            // Show result dialog with calculated values
            showResultDialog(savedAmount, finalPrice);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            showErrorDialog("Invalid input. Please enter numerical values.");
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showResultDialog(double savedAmount, double finalPrice) {
        new AlertDialog.Builder(this)
                .setTitle("Calculation Results")
                .setMessage(String.format("You Saved: %.2f\nFinal Price: %.2f", savedAmount, finalPrice))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
