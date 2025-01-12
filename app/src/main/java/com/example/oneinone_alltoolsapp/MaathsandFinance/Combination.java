package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.math.BigInteger;
import com.example.oneinone_alltoolsapp.R;

public class Combination extends AppCompatActivity {
    EditText editTextN, editTextR;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination);
        editTextN = findViewById(R.id.editTextN);
        editTextR = findViewById(R.id.editTextR);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nStr = editTextN.getText().toString();
                String rStr = editTextR.getText().toString();

                if (nStr.isEmpty() || rStr.isEmpty()) {
                    showAlertDialog("Input Error", "Please enter both n and r values.");
                    return;
                }

                try {
                    BigInteger n = new BigInteger(nStr);
                    BigInteger r = new BigInteger(rStr);

                    if (n.compareTo(BigInteger.ZERO) < 0 || r.compareTo(BigInteger.ZERO) < 0 || r.compareTo(n) > 0) {
                        showAlertDialog("Input Error", "Invalid values. Ensure n >= r >= 0.");
                        return;
                    }

                    new CalculateTask().execute(n, r);  // Use CalculateTask directly
                } catch (NumberFormatException e) {
                    showAlertDialog("Input Error", "Invalid input. Please enter valid numbers.");
                }
            }
        });
    }

    private class CalculateTask extends AsyncTask<BigInteger, Void, String> {
        @Override
        protected String doInBackground(BigInteger... params) {
            BigInteger n = params[0];
            BigInteger r = params[1];

            try {
                BigInteger permutation = calculatePermutation(n, r);
                BigInteger combination = calculateCombination(n, r);

                return "Combination (C(n,r)): " + combination.toString() + "\nPermutation (P(n,r)): " + permutation.toString();
            } catch (ArithmeticException e) {
                return "Calculation error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            showAlertDialog("Calculation Result", result);
        }
    }

    private BigInteger calculatePermutation(BigInteger n, BigInteger r) {
        return factorial(n).divide(factorial(n.subtract(r)));
    }

    private BigInteger calculateCombination(BigInteger n, BigInteger r) {
        return factorial(n).divide(factorial(r).multiply(factorial(n.subtract(r))));
    }

    private BigInteger factorial(BigInteger num) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
