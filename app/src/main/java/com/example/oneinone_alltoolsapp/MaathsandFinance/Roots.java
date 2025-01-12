package com.example.oneinone_alltoolsapp.MaathsandFinance;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class Roots extends AppCompatActivity {

    private EditText numberInput;
    private Button calculateRootsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roots);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        numberInput = findViewById(R.id.number_input);
        calculateRootsButton = findViewById(R.id.calculate_roots_button);

        numberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Optional: Add validation for the input if needed
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        calculateRootsButton.setOnClickListener(v -> calculateRoots());
    }

    private void calculateRoots() {
        String numberStr = numberInput.getText().toString().trim();

        if (numberStr.isEmpty()) {
            showAlertDialog("Error", "Please enter a number.");
            return;
        }

        double number;
        try {
            number = Double.parseDouble(numberStr);
        } catch (NumberFormatException e) {
            showAlertDialog("Error", "Please enter a valid number.");
            return;
        }

        if (number < 0) {
            showAlertDialog("Error", "Please enter a non-negative number.");
            return;
        }

        StringBuilder result = new StringBuilder(String.format("Roots of %.2f:\n", number));

        for (int i = 2; i <= 10; i++) {
            double root = Math.pow(number, 1.0 / i);
            result.append(String.format("%dth Root: %.5f\n", i, root));
        }

        showAlertDialog("Result", result.toString());
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
