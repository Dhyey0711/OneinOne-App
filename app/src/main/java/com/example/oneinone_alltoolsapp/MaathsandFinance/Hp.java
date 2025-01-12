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

public class Hp extends AppCompatActivity {

    private EditText firstTermEditText, commonDifferenceEditText, numberOfTermsEditText;
    private Button calculateHpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        firstTermEditText = findViewById(R.id.first_term);
        commonDifferenceEditText = findViewById(R.id.common_difference);
        numberOfTermsEditText = findViewById(R.id.number_of_terms);
        calculateHpButton = findViewById(R.id.calculate_hp_button);

        // Apply text watchers to each EditText
        firstTermEditText.addTextChangedListener(createTextWatcher(firstTermEditText));
        commonDifferenceEditText.addTextChangedListener(createTextWatcher(commonDifferenceEditText));
        numberOfTermsEditText.addTextChangedListener(createTextWatcher(numberOfTermsEditText));

        calculateHpButton.setOnClickListener(v -> calculateHP());
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

    private void calculateHP() {
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

            StringBuilder hp = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                hp.append(1.0 / (a + (i - 1) * d)).append(" ");
            }

            showResultDialog(hp.toString().trim());
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers.");
        }
    }

    private void showResultDialog(String result) {
        new AlertDialog.Builder(this)
                .setTitle("Harmonic Progression")
                .setMessage("HP: " + result)
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
