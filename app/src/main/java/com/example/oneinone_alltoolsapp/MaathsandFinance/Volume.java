package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.example.oneinone_alltoolsapp.R;

public class Volume extends AppCompatActivity {

    private Spinner shapeSpinner;
    private TextInputLayout tilDimension1Input, tilDimension2Input, tilDimension3Input;
    private TextInputEditText dimension1Input, dimension2Input, dimension3Input;
    private Button calculateVolumeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        shapeSpinner = findViewById(R.id.shape_spinner);
        tilDimension1Input = findViewById(R.id.til_dimension1_input);
        tilDimension2Input = findViewById(R.id.til_dimension2_input);
        tilDimension3Input = findViewById(R.id.til_dimension3_input);
        dimension1Input = findViewById(R.id.dimension1_input);
        dimension2Input = findViewById(R.id.dimension2_input);
        dimension3Input = findViewById(R.id.dimension3_input);
        calculateVolumeButton = findViewById(R.id.calculate_volume_button);

        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateInputFields(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle no selection if needed
            }
        });

        calculateVolumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVolume();
            }
        });
    }

    private void updateInputFields(int shapeIndex) {
        switch (shapeIndex) {
            case 0: // Cube
                tilDimension1Input.setHint("Side Length (s)");
                tilDimension2Input.setVisibility(View.GONE);
                tilDimension3Input.setVisibility(View.GONE);
                break;
            case 1: // Rectangular Prism
                tilDimension1Input.setHint("Length");
                tilDimension2Input.setHint("Width");
                tilDimension3Input.setHint("Height");
                tilDimension2Input.setVisibility(View.VISIBLE);
                tilDimension3Input.setVisibility(View.VISIBLE);
                break;
            case 2: // Sphere
                tilDimension1Input.setHint("Radius (r)");
                tilDimension2Input.setVisibility(View.GONE);
                tilDimension3Input.setVisibility(View.GONE);
                break;
            case 3: // Cylinder
                tilDimension1Input.setHint("Radius (r)");
                tilDimension2Input.setHint("Height (h)");
                tilDimension2Input.setVisibility(View.VISIBLE);
                tilDimension3Input.setVisibility(View.GONE);
                break;
            case 4: // Cone
                tilDimension1Input.setHint("Radius (r)");
                tilDimension2Input.setHint("Height (h)");
                tilDimension2Input.setVisibility(View.VISIBLE);
                tilDimension3Input.setVisibility(View.GONE);
                break;
            case 5: // Pyramid
                tilDimension1Input.setHint("Base Area (B)");
                tilDimension2Input.setHint("Height (h)");
                tilDimension2Input.setVisibility(View.VISIBLE);
                tilDimension3Input.setVisibility(View.GONE);
                break;
            // Add more cases for additional shapes if needed
        }
    }

    private void calculateVolume() {
        int shapeIndex = shapeSpinner.getSelectedItemPosition();
        double volume = 0;

        try {
            switch (shapeIndex) {
                case 0: // Cube
                    double side = Double.parseDouble(dimension1Input.getText().toString());
                    volume = VolumeCalculator.calculateCubeVolume(side);
                    break;
                case 1: // Rectangular Prism
                    double length = Double.parseDouble(dimension1Input.getText().toString());
                    double width = Double.parseDouble(dimension2Input.getText().toString());
                    double height = Double.parseDouble(dimension3Input.getText().toString());
                    volume = VolumeCalculator.calculateRectangularPrismVolume(length, width, height);
                    break;
                case 2: // Sphere
                    double radius = Double.parseDouble(dimension1Input.getText().toString());
                    volume = VolumeCalculator.calculateSphereVolume(radius);
                    break;
                case 3: // Cylinder
                    double cylRadius = Double.parseDouble(dimension1Input.getText().toString());
                    double cylHeight = Double.parseDouble(dimension2Input.getText().toString());
                    volume = VolumeCalculator.calculateCylinderVolume(cylRadius, cylHeight);
                    break;
                case 4: // Cone
                    double coneRadius = Double.parseDouble(dimension1Input.getText().toString());
                    double coneHeight = Double.parseDouble(dimension2Input.getText().toString());
                    volume = VolumeCalculator.calculateConeVolume(coneRadius, coneHeight);
                    break;
                case 5: // Pyramid
                    double baseArea = Double.parseDouble(dimension1Input.getText().toString());
                    double pyrHeight = Double.parseDouble(dimension2Input.getText().toString());
                    volume = VolumeCalculator.calculatePyramidVolume(baseArea, pyrHeight);
                    break;
                // Add more cases for additional shapes if needed
            }
            showVolumeDialog(volume);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showVolumeDialog(double volume) {
        new AlertDialog.Builder(this)
                .setTitle("Volume Result")
                .setMessage("The volume is: " + volume)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
