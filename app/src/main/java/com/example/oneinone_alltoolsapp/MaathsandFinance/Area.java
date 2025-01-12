package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class Area extends AppCompatActivity {

    private Spinner shapeSpinner;
    private EditText param1EditText, param2EditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        shapeSpinner = findViewById(R.id.shape_spinner);
        param1EditText = findViewById(R.id.param1);
        param2EditText = findViewById(R.id.param2);
        calculateButton = findViewById(R.id.calculate_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shapes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapter);

        param2EditText.setVisibility(View.GONE);

        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedShape = parent.getItemAtPosition(position).toString();
                if (selectedShape.equals("Circle") || selectedShape.equals("Square")) {
                    param2EditText.setVisibility(View.GONE);
                } else {
                    param2EditText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                param2EditText.setVisibility(View.GONE);
            }
        });

        param1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    Toast.makeText(Area.this, "Input too long! Maximum 5 digits allowed.", Toast.LENGTH_SHORT).show();
                    param1EditText.setText(s.subSequence(0, 5));
                    param1EditText.setSelection(5);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        param2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    Toast.makeText(Area.this, "Input too long! Maximum 5 digits allowed.", Toast.LENGTH_SHORT).show();
                    param2EditText.setText(s.subSequence(0, 5));
                    param2EditText.setSelection(5);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        calculateButton.setOnClickListener(v -> calculateArea());
    }

    private void calculateArea() {
        String selectedShape = shapeSpinner.getSelectedItem().toString();
        String param1 = param1EditText.getText().toString().trim();
        String param2 = param2EditText.getText().toString().trim();
        double area = 0.0;

        try {
            if (param1.isEmpty()) {
                showAlertDialog("Error", "Please enter value for parameter 1.");
                return;
            }

            switch (selectedShape) {
                case "Circle":
                    double radius = Double.parseDouble(param1);
                    area = Math.PI * radius * radius;
                    break;
                case "Square":
                    double side = Double.parseDouble(param1);
                    area = side * side;
                    break;
                case "Rectangle":
                    if (param2.isEmpty()) {
                        showAlertDialog("Error", "Please enter value for parameter 2.");
                        return;
                    }
                    double length = Double.parseDouble(param1);
                    double width = Double.parseDouble(param2);
                    area = length * width;
                    break;
                case "Triangle":
                    if (param2.isEmpty()) {
                        showAlertDialog("Error", "Please enter value for parameter 2.");
                        return;
                    }
                    double base = Double.parseDouble(param1);
                    double height = Double.parseDouble(param2);
                    area = 0.5 * base * height;
                    break;
            }

            showAlertDialog("Result", "Area: " + area);
        } catch (NumberFormatException e) {
            showAlertDialog("Error", "Please enter valid numbers.");
        }
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}