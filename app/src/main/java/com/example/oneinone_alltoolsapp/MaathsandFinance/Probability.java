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

public class Probability extends AppCompatActivity {

    private EditText numberOfEventsEditText, totalOutcomesEditText;
    private Button calculateProbabilityButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probability);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        numberOfEventsEditText = findViewById(R.id.number_of_events);
        totalOutcomesEditText = findViewById(R.id.total_outcomes);
        calculateProbabilityButton = findViewById(R.id.calculate_probability_button);

        numberOfEventsEditText.addTextChangedListener(createTextWatcher(numberOfEventsEditText));
        totalOutcomesEditText.addTextChangedListener(createTextWatcher(totalOutcomesEditText));

        calculateProbabilityButton.setOnClickListener(v -> calculateProbability());
    }

    private TextWatcher createTextWatcher(EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    showToast("Input too long! Maximum 5 digits allowed.");
                    editText.setText(s.subSequence(0, 5));
                    editText.setSelection(5);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private void calculateProbability() {
        String numberOfEventsStr = numberOfEventsEditText.getText().toString();
        String totalOutcomesStr = totalOutcomesEditText.getText().toString();

        try {
            int m = Integer.parseInt(numberOfEventsStr);
            int n = Integer.parseInt(totalOutcomesStr);

            if (n == 0) {
                showErrorDialog("Total outcomes cannot be zero.");
                return;
            }

            if (m > n) {
                showErrorDialog("Number of events cannot be greater than total outcomes.");
                return;
            }

            double probability = (double) m / n;
            showResultDialog(probability);
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers.");
        }
    }

    private void showResultDialog(double probability) {
        new AlertDialog.Builder(this)
                .setTitle("Probability Result")
                .setMessage(String.format("Probability: %.5f", probability))
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