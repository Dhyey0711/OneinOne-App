package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class AspectRatio extends AppCompatActivity {

    private EditText pixelWidthEditText;
    private EditText pixelHeightEditText;
    private Button calculateHeightButton;
    private Button calculateWidthButton;
    private Spinner aspectRatioSpinner;
    private TextView aspectRatioTextView;

    private double aspectWidth = 0;
    private double aspectHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect_ratio);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        aspectRatioSpinner = findViewById(R.id.aspect_ratio_spinner);
        aspectRatioTextView = findViewById(R.id.aspect_ratio_textview);
        pixelWidthEditText = findViewById(R.id.pixel_width);
        pixelHeightEditText = findViewById(R.id.pixel_height);
        calculateHeightButton = findViewById(R.id.calculate_height);
        calculateWidthButton = findViewById(R.id.calculate_width);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.aspect_ratios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aspectRatioSpinner.setAdapter(adapter);

        // Update TextView and set aspect ratios based on selected item
        aspectRatioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAspect = (String) parent.getItemAtPosition(position);
                updateAspectRatio(selectedAspect, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        calculateHeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateHeight();
            }
        });

        calculateWidthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateWidth();
            }
        });
    }

    private void updateAspectRatio(String selectedAspect, int position) {
        switch (position) {
            case 0:
                aspectWidth = 16;
                aspectHeight = 9;
                break;
            case 1:
                aspectWidth = 16;
                aspectHeight = 9;
                break;
            case 2:
                aspectWidth = 16;
                aspectHeight = 9;
                break;
            case 3:
                aspectWidth = 4;
                aspectHeight = 3;
                break;
            case 4:
                aspectWidth = 16;
                aspectHeight = 10;
                break;
            case 5:
                aspectWidth = 16;
                aspectHeight = 9;
                break;
            case 6:
                aspectWidth = 16;
                aspectHeight = 9;
                break;
            case 7:
                aspectWidth = 1.9;
                aspectHeight = 1;
                break;
            case 8:
                aspectWidth = 16;
                aspectHeight = 9;
                break;
            case 9:
                aspectWidth = 4;
                aspectHeight = 3;
                break;
            case 10:
                aspectWidth = 2;
                aspectHeight = 1;
                break;
            case 11:
                aspectWidth = 3;
                aspectHeight = 2;
                break;
            case 12:
                aspectWidth = 4;
                aspectHeight = 3;
                break;
            case 13:
                aspectWidth = 8;
                aspectHeight = 1;
                break;
            case 14:
                aspectWidth = 15;
                aspectHeight = 9;
                break;
            case 15:
                aspectWidth = 4;
                aspectHeight = 3;
                break;
            case 16:
                aspectWidth = 4;
                aspectHeight = 3;
                break;
            case 17:
                aspectWidth = 4;
                aspectHeight = 3;
                break;
            default:
                aspectWidth = 0;
                aspectHeight = 0;
                break;
        }

        if (aspectWidth != 0 && aspectHeight != 0) {
            aspectRatioTextView.setText("Aspect Ratio: " + aspectWidth + ":" + aspectHeight);
        } else {
            aspectRatioTextView.setText("Invalid Aspect Ratio");
        }
    }

    private void calculateHeight() {
        String widthStr = pixelWidthEditText.getText().toString();
        if (TextUtils.isEmpty(widthStr)) {
            showAlert("Please enter a valid width.");
            return;
        }

        try {
            int width = Integer.parseInt(widthStr);
            if (aspectWidth == 0 || aspectHeight == 0) {
                showAlert("Invalid aspect ratio.");
                return;
            }

            double calculatedHeight = (width * aspectHeight) / aspectWidth;
            showAlert("Calculated Height: " + calculatedHeight + " pixels");
        } catch (NumberFormatException e) {
            showAlert("Invalid input.");
        } catch (Exception e) {
            showAlert("An error occurred: " + e.getMessage());
        }
    }

    private void calculateWidth() {
        String heightStr = pixelHeightEditText.getText().toString();
        if (TextUtils.isEmpty(heightStr)) {
            showAlert("Please enter a valid height.");
            return;
        }

        try {
            int height = Integer.parseInt(heightStr);
            if (aspectWidth == 0 || aspectHeight == 0) {
                showAlert("Invalid aspect ratio.");
                return;
            }

            double calculatedWidth = (height * aspectWidth) / aspectHeight;
            showAlert("Calculated Width: " + calculatedWidth + " pixels");
        } catch (NumberFormatException e) {
            showAlert("Invalid input.");
        } catch (Exception e) {
            showAlert("An error occurred: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
