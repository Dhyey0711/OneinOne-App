package com.example.oneinone_alltoolsapp.EssentialTools;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.oneinone_alltoolsapp.R;

public class BMI extends AppCompatActivity {

    private EditText editTextAge, editTextHeight, editTextWeight;
    private Spinner spinnerHeightUnit, spinnerWeightUnit;
    private Button buttonCalculateBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);

        spinnerHeightUnit = findViewById(R.id.spinnerHeightUnit);
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(this,
                R.array.height_units, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHeightUnit.setAdapter(heightAdapter);

        spinnerWeightUnit = findViewById(R.id.spinnerWeightUnit);
        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeightUnit.setAdapter(weightAdapter);

        buttonCalculateBMI = findViewById(R.id.buttonCalculateBMI);

        buttonCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightText = editTextHeight.getText().toString();
        String weightText = editTextWeight.getText().toString();

        // Check if input fields are not empty
        if (heightText.isEmpty() || weightText.isEmpty()) {
            showDialog("Error", "Please enter both height and weight.");
            return;
        }

        try {
            double height = Double.parseDouble(heightText);
            double weight = Double.parseDouble(weightText);
            String heightUnit = spinnerHeightUnit.getSelectedItem().toString();
            String weightUnit = spinnerWeightUnit.getSelectedItem().toString();

            // Convert height to meters
            if (heightUnit.equals("cm")) {
                height /= 100; // Convert cm to meters
            } else if (heightUnit.equals("Inches")) {
                height *= 0.0254; // Convert inches to meters
            }

            // Convert weight to kilograms
            if (weightUnit.equals("lbs")) {
                weight *= 0.453592; // Convert pounds to kilograms
            }

            // Calculate BMI
            double bmi = weight / (height * height);

            // Determine the BMI category
            String category;
            if (bmi < 18.5) {
                category = "Underweight";
                category += "\n\nBMI < 18.5\n" +
                        "Interpretation: Indicates that the person may be underweight. It could be due to various factors including malnutrition or underlying health conditions.";
            } else if (bmi < 25) {
                category = "Normal Weight";
                category += "\n\nBMI 18.5 - 24.9\n" +
                        "Interpretation: Indicates a healthy weight range. People in this category are considered to have a normal, healthy weight for their height.";
            } else if (bmi < 30) {
                category = "Overweight";
                category += "\n\nBMI 25 - 29.9\n" +
                        "Interpretation: Indicates that the person is overweight. This can increase the risk of various health conditions, including cardiovascular diseases and type 2 diabetes.";
            } else {
                category = "Obesity";
                category += "\n\nBMI ≥ 30\n" +
                        "Interpretation: Indicates obesity. Obesity is associated with a higher risk of several health issues, including heart disease, hypertension, diabetes, and certain cancers.";
            }

            showDialog("BMI Result", String.format("Your BMI: %.2f\n\n%s", bmi, category));
        } catch (NumberFormatException e) {
            showDialog("Error", "Invalid number format. Please enter valid numbers.");
        } catch (ArithmeticException e) {
            showDialog("Error", "Error in BMI calculation. Check your inputs.");
        }
    }

    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}