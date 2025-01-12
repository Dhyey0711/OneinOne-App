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

public class Exponent extends AppCompatActivity {
    private EditText baseNumberEditText, exponentNumberEditText;
    private Button calculateExponentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exponent);
        baseNumberEditText = findViewById(R.id.base_number);
        exponentNumberEditText = findViewById(R.id.exponent_number);
        calculateExponentButton = findViewById(R.id.btn_calculate);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        baseNumberEditText.addTextChangedListener(createTextWatcher(baseNumberEditText));
        exponentNumberEditText.addTextChangedListener(createTextWatcher(exponentNumberEditText));

        calculateExponentButton.setOnClickListener(v -> calculateExponent());
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

    private void calculateExponent() {
        String baseNumberStr = baseNumberEditText.getText().toString();
        String exponentNumberStr = exponentNumberEditText.getText().toString();

        try {
            int base = Integer.parseInt(baseNumberStr);
            int exponent = Integer.parseInt(exponentNumberStr);

            double result = Math.pow(base, exponent);
            showResultDialog(result);
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter valid numbers.");
        }
    }

    private void showResultDialog(double result) {
        new AlertDialog.Builder(this)
                .setTitle("Exponent Result")
                .setMessage(String.format("Result: %.5f", result))
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