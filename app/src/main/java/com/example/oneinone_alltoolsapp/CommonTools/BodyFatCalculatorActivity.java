package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class BodyFatCalculatorActivity extends AppCompatActivity {

    private TextInputEditText etAge, etWeight, etHeight, etWaist, etHip;
    private RadioGroup rgGender;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_fat_calculator);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        rgGender = findViewById(R.id.rg_gender);
        etAge = findViewById(R.id.et_age);
        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        etWaist = findViewById(R.id.et_waist);
        etHip = findViewById(R.id.et_hip);
        btnCalculate = findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBodyFat();
            }
        });
    }

    private void calculateBodyFat() {
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);

        String gender = selectedGender.getText().toString().toLowerCase();
        String ageStr = etAge.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();
        String waistStr = etWaist.getText().toString().trim();
        String hipStr = etHip.getText().toString().trim();

        if (ageStr.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty() || waistStr.isEmpty() || (gender.equals("female") && hipStr.isEmpty())) {
            showDialog("Please fill in all required fields.");
            return;
        }

        int age = Integer.parseInt(ageStr);
        float weight = Float.parseFloat(weightStr);
        float height = Float.parseFloat(heightStr) / 100; // Convert cm to meters
        float waist = Float.parseFloat(waistStr);
        float hip = gender.equals("female") ? Float.parseFloat(hipStr) : 0;

        // BMI Calculation
        float bmi = weight / (height * height);
        double bodyFat = 0.0;

        switch (gender) {
            case "male":
                bodyFat = (1.20 * bmi) + (0.23 * age) - 16.2;
                break;
            case "female":
                bodyFat = (1.20 * bmi) + (0.23 * age) - 5.4;
                break;
            default:
                showDialog("Invalid gender input. Please select 'male' or 'female'.");
                return;
        }

        showDialog("Your body fat percentage is: " + String.format("%.2f", bodyFat) + "%");
    }

    private void showDialog(String message) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Body Fat Calculator")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}