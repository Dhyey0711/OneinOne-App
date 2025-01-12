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

public class Gp extends AppCompatActivity {
    private EditText firstTermEditText, commonRatioEditText, numberOfTermsEditText;
    private Button calculateGpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        firstTermEditText = findViewById(R.id.first_term);
        commonRatioEditText = findViewById(R.id.common_difference);
        numberOfTermsEditText = findViewById(R.id.number_of_terms);
        calculateGpButton = findViewById(R.id.calculate_gp_button);

        // Apply the text watcher to each EditText
        firstTermEditText.addTextChangedListener(createTextWatcher(firstTermEditText));
        commonRatioEditText.addTextChangedListener(createTextWatcher(commonRatioEditText));
        numberOfTermsEditText.addTextChangedListener(createTextWatcher(numberOfTermsEditText));

        calculateGpButton.setOnClickListener(v -> calculateGP());
    }

    private TextWatcher createTextWatcher(EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    // Show a Toast message if the length exceeds 5 digits
                    Toast.makeText(Gp.this, "Number cannot exceed 5 digits", Toast.LENGTH_SHORT).show();

                    // Limit the length to 5 digits
                    editText.setText(s.subSequence(0, 5));
                    editText.setSelection(5); // Move cursor to end
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private void calculateGP() {
        String firstTermStr = firstTermEditText.getText().toString();
        String commonRatioStr = commonRatioEditText.getText().toString();
        String numberOfTermsStr = numberOfTermsEditText.getText().toString();

        if (firstTermStr.isEmpty() || commonRatioStr.isEmpty() || numberOfTermsStr.isEmpty()) {
            showErrorDialog("All fields must be filled.");
            return;
        }

        try {
            int a = Integer.parseInt(firstTermStr);
            int r = Integer.parseInt(commonRatioStr);
            int n = Integer.parseInt(numberOfTermsStr);

            if (n <= 0) {
                showErrorDialog("Number of terms must be greater than 0.");
                return;
            }

            StringBuilder gp = new StringBuilder();
            for (int i = 0; i < n; i++) {
                gp.append(a * Math.pow(r, i)).append(" ");
            }

            showResultDialog(gp.toString().trim());
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers.");
        }
    }

    private void showResultDialog(String result) {
        new AlertDialog.Builder(this)
                .setTitle("Geometric Progression")
                .setMessage("GP: " + result)
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
}
