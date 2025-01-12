package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.oneinone_alltoolsapp.R;

public class Linear extends AppCompatActivity {

    private EditText editTextA1, editTextB1, editTextC1, editTextA2, editTextB2, editTextC2;
    private Button buttonSolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        editTextA1 = findViewById(R.id.editTextA1);
        editTextB1 = findViewById(R.id.editTextB1);
        editTextC1 = findViewById(R.id.editTextC1);
        editTextA2 = findViewById(R.id.editTextA2);
        editTextB2 = findViewById(R.id.editTextB2);
        editTextC2 = findViewById(R.id.editTextC2);
        buttonSolve = findViewById(R.id.buttonSolve);

        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveEquations();
            }
        });
    }

    private void solveEquations() {
        try {
            double a1 = Double.parseDouble(editTextA1.getText().toString());
            double b1 = Double.parseDouble(editTextB1.getText().toString());
            double c1 = Double.parseDouble(editTextC1.getText().toString());
            double a2 = Double.parseDouble(editTextA2.getText().toString());
            double b2 = Double.parseDouble(editTextB2.getText().toString());
            double c2 = Double.parseDouble(editTextC2.getText().toString());

            double determinant = a1 * b2 - a2 * b1;

            String result;
            if (determinant == 0) {
                if (a1 * c2 == a2 * c1 && b1 * c2 == b2 * c1) {
                    result = "The lines are coincident (infinitely many solutions).";
                } else {
                    result = "The lines are parallel (no unique solution).";
                }
            } else {
                double x = (b2 * c1 - b1 * c2) / determinant;
                double y = (a1 * c2 - a2 * c1) / determinant;
                result = "The lines intersect at: x = " + x + ", y = " + y;

                // Check for perpendicularity
                double slope1 = -a1 / b1;
                double slope2 = -a2 / b2;
                if (Math.abs(slope1 * slope2 + 1) < 1e-9) { // Near zero check for perpendicularity
                    result += "\nThe lines are perpendicular.";
                }
            }

            showAlert(result);

        } catch (NumberFormatException e) {
            showAlert("Please enter valid coefficients and constants.");
        }
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
