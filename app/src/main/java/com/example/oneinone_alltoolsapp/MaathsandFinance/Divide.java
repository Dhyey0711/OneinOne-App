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

public class Divide extends AppCompatActivity {
    private EditText numberInputEditText;
    private Button checkDivisibilityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        numberInputEditText = findViewById(R.id.number_input);
        checkDivisibilityButton = findViewById(R.id.check_divisibility_button);

        numberInputEditText.addTextChangedListener(createTextWatcher(numberInputEditText));

        checkDivisibilityButton.setOnClickListener(v -> checkDivisibilityAndPrime());
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

    private void checkDivisibilityAndPrime() {
        String numberStr = numberInputEditText.getText().toString();

        try {
            int number = Integer.parseInt(numberStr);
            StringBuilder divisibilityResult = new StringBuilder("Divisible by: ");
            boolean isPrime = true;

            for (int i = 1; i <= 9; i++) {
                if (number % i == 0) {
                    divisibilityResult.append(i).append(" ");
                }
            }

            if (number <= 1) {
                isPrime = false;
            } else {
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }

            showResultDialog(divisibilityResult.toString().trim(), isPrime);
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter a valid number.");
        }
    }

    private void showResultDialog(String divisibilityResult, boolean isPrime) {
        new AlertDialog.Builder(this)
                .setTitle("Divisibility and Prime Check")
                .setMessage(String.format("%s\n%s", divisibilityResult, isPrime ? "The number is prime" : "The number is not prime"))
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