package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.oneinone_alltoolsapp.R;
import java.text.DecimalFormat;

public class Cubic extends AppCompatActivity {
    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextC;
    private EditText editTextD;
    private Button buttonSolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubic);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);
        editTextD = findViewById(R.id.editTextD);
        buttonSolve = findViewById(R.id.buttonSolve);

        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveCubicEquation();
            }
        });
    }

    private void solveCubicEquation() {
        String aStr = editTextA.getText().toString();
        String bStr = editTextB.getText().toString();
        String cStr = editTextC.getText().toString();
        String dStr = editTextD.getText().toString();

        if (aStr.isEmpty() || bStr.isEmpty() || cStr.isEmpty() || dStr.isEmpty()) {
            showAlert("Error", "Please enter all coefficients.");
            return;
        }

        double a = Double.parseDouble(aStr);
        double b = Double.parseDouble(bStr);
        double c = Double.parseDouble(cStr);
        double d = Double.parseDouble(dStr);

        double[] roots = solveCubic(a, b, c, d);

        String root1 = formatComplexRoot(roots[0], roots[1]);
        String root2 = formatComplexRoot(roots[2], roots[3]);
        String root3 = formatComplexRoot(roots[4], roots[5]);

        showAlert("Cubic Equation Roots", "Root 1: " + root1 + "\nRoot 2: " + root2 + "\nRoot 3: " + root3);
    }

    private String formatComplexRoot(double real, double imaginary) {
        DecimalFormat df = new DecimalFormat("#.##########");  // Adjusted for more decimal places
        if (imaginary == 0) {
            return df.format(real);
        } else if (imaginary > 0) {
            return df.format(real) + " + " + df.format(imaginary) + "i";
        } else {
            return df.format(real) + " - " + df.format(Math.abs(imaginary)) + "i";
        }
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private double[] solveCubic(double a, double b, double c, double d) {
        double f = ((3.0 * c / a) - ((b * b) / (a * a))) / 3.0;
        double g = ((2.0 * b * b * b) / (a * a * a) - (9.0 * b * c) / (a * a) + (27.0 * d) / a) / 27.0;
        double h = (g * g / 4.0) + (f * f * f / 27.0);

        double[] roots = new double[6];  // [real1, imag1, real2, imag2, real3, imag3]

        if (h > 0) {  // One real root and two complex conjugate roots
            double r = -(g / 2.0) + Math.sqrt(h);
            double s = Math.cbrt(r);
            double t = -(g / 2.0) - Math.sqrt(h);
            double u = Math.cbrt(t);

            roots[0] = (s + u) - (b / (3.0 * a));
            roots[1] = 0;

            double realPart = -(s + u) / 2.0 - (b / (3.0 * a));
            double imaginaryPart = Math.sqrt(3) * (s - u) / 2.0;

            roots[2] = realPart;
            roots[3] = imaginaryPart;
            roots[4] = realPart;
            roots[5] = -imaginaryPart;

        } else if (f == 0 && g == 0 && h == 0) {  // All roots real and equal
            double root = -Math.cbrt(d / a);
            roots[0] = root;
            roots[1] = 0;
            roots[2] = root;
            roots[3] = 0;
            roots[4] = root;
            roots[5] = 0;

        } else {  // All roots real
            double i = Math.sqrt((g * g / 4.0) - h);
            double j = Math.cbrt(i);
            double k = Math.acos(-(g / (2.0 * i)));
            double l = -j;

            roots[0] = 2.0 * j * Math.cos(k / 3.0) - (b / (3.0 * a));
            roots[1] = 0;

            roots[2] = l * (Math.cos(k / 3.0) + Math.sqrt(3.0) * Math.sin(k / 3.0)) - (b / (3.0 * a));
            roots[3] = 0;

            roots[4] = l * (Math.cos(k / 3.0) - Math.sqrt(3.0) * Math.sin(k / 3.0)) - (b / (3.0 * a));
            roots[5] = 0;
        }

        return roots;
    }
}
