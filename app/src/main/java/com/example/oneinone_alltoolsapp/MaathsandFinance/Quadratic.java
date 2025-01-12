package com.example.oneinone_alltoolsapp.MaathsandFinance;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.DecimalFormat;

import com.example.oneinone_alltoolsapp.R;

public class Quadratic extends AppCompatActivity {
    private EditText editTextA, editTextB, editTextC;
    private Button buttonSolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);
        buttonSolve = findViewById(R.id.buttonSolve);

        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveQuadraticEquation();
            }
        });
    }

    private void solveQuadraticEquation() {
        String strA = editTextA.getText().toString().trim();
        String strB = editTextB.getText().toString().trim();
        String strC = editTextC.getText().toString().trim();

        if (strA.isEmpty() || strB.isEmpty() || strC.isEmpty()) {
            Toast.makeText(this, "Please enter all coefficients", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double a = Double.parseDouble(strA);
            double b = Double.parseDouble(strB);
            double c = Double.parseDouble(strC);

            double discriminant = b * b - 4 * a * c;
            String firstRoot, secondRoot;

            DecimalFormat df = new DecimalFormat("#.###############"); // High accuracy format

            if (discriminant > 0) {
                // Two real roots
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                firstRoot = df.format(root1);
                secondRoot = df.format(root2);
            } else if (discriminant == 0) {
                // One real root
                double root = -b / (2 * a);
                firstRoot = secondRoot = df.format(root);
            } else {
                // Two complex roots
                double realPart = -b / (2 * a);
                double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
                firstRoot = df.format(realPart) + " + " + df.format(imaginaryPart) + "i";
                secondRoot = df.format(realPart) + " - " + df.format(imaginaryPart) + "i";
            }

            // Calculate vertex
            double vertexX = -b / (2 * a);
            double vertexY = c - (b * b) / (4 * a);
            String vertex = "(" + df.format(vertexX) + ", " + df.format(vertexY) + ")";

            // Show results in AlertDialog
            showAlert("Quadratic Equation Solutions", "Root 1: " + firstRoot + "\nRoot 2: " + secondRoot + "\nVertex: " + vertex);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}