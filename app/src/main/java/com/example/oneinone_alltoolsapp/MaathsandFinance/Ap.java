package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class Ap extends AppCompatActivity {
    private EditText firstTermEditText, commonDifferenceEditText, numberOfTermsEditText;
    private Button calculateApButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ap);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        firstTermEditText = findViewById(R.id.first_term);
        commonDifferenceEditText = findViewById(R.id.common_difference);
        numberOfTermsEditText = findViewById(R.id.number_of_terms);
        calculateApButton = findViewById(R.id.calculate_ap_button);

        // Apply text watchers to each EditText
        firstTermEditText.addTextChangedListener(createTextWatcher(firstTermEditText));
        commonDifferenceEditText.addTextChangedListener(createTextWatcher(commonDifferenceEditText));
        numberOfTermsEditText.addTextChangedListener(createTextWatcher(numberOfTermsEditText));

        calculateApButton.setOnClickListener(v -> calculateAP());
    }

    private TextWatcher createTextWatcher(EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    // Show a Toast message if the length exceeds 5 digits
                    showToast("Input too long! Maximum 5 digits allowed.");

                    // Limit the length to 5 digits
                    editText.setText(s.subSequence(0, 5));
                    editText.setSelection(5); // Move cursor to end
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private void calculateAP() {
        String firstTermStr = firstTermEditText.getText().toString();
        String commonDifferenceStr = commonDifferenceEditText.getText().toString();
        String numberOfTermsStr = numberOfTermsEditText.getText().toString();

        if (firstTermStr.isEmpty() || commonDifferenceStr.isEmpty() || numberOfTermsStr.isEmpty()) {
            showErrorDialog("All fields must be filled.");
            return;
        }

        try {
            int a = Integer.parseInt(firstTermStr);
            int d = Integer.parseInt(commonDifferenceStr);
            int n = Integer.parseInt(numberOfTermsStr);

            if (n <= 0) {
                showErrorDialog("Number of terms must be greater than 0.");
                return;
            }

            StringBuilder ap = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ap.append(a + i * d).append(" ");
            }

            showResultDialog(ap.toString().trim());
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers.");
        }
    }

    private void showResultDialog(String result) {
        new AlertDialog.Builder(this)
                .setTitle("Arithmetic Progression")
                .setMessage("AP: " + result)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
