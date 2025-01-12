package com.example.oneinone_alltoolsapp.CommonTools.HRconverter;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.oneinone_alltoolsapp.R;

public class rgbtohexfragment extends Fragment {

    private EditText redInput, greenInput, blueInput;
    private TextView hexOutput;
    private ImageView colorPreview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rgbtohexfragment, container, false);

        redInput = view.findViewById(R.id.redInput);
        greenInput = view.findViewById(R.id.greenInput);
        blueInput = view.findViewById(R.id.blueInput);
        hexOutput = view.findViewById(R.id.hexOutput);
        colorPreview = view.findViewById(R.id.colorPreviewRgb);

        Button convertButton = view.findViewById(R.id.convertRgbToHex);
        Button copyButton = view.findViewById(R.id.copyHexCode);
        Button shareButton = view.findViewById(R.id.shareColorRgb);
        Button resetButton = view.findViewById(R.id.resetRgb);

        convertButton.setOnClickListener(v -> convertRgbToHex());
        copyButton.setOnClickListener(v -> copyHexCode());
        shareButton.setOnClickListener(v -> shareColor());
        resetButton.setOnClickListener(v -> resetFields());

        return view;
    }

    private void convertRgbToHex() {
        try {
            int r = Integer.parseInt(redInput.getText().toString());
            int g = Integer.parseInt(greenInput.getText().toString());
            int b = Integer.parseInt(blueInput.getText().toString());

            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                Toast.makeText(getActivity(), "Invalid RGB values", Toast.LENGTH_SHORT).show();
                return;
            }

            String hex = String.format("#%02X%02X%02X", r, g, b);
            hexOutput.setText(hex);
            colorPreview.setBackgroundColor(Color.rgb(r, g, b));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Invalid RGB values", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyHexCode() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Hex Code", hexOutput.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity(), "Hex Code Copied", Toast.LENGTH_SHORT).show();
    }

    private void shareColor() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Hex Code: " + hexOutput.getText().toString());
        startActivity(Intent.createChooser(intent, "Share Color"));
    }

    private void resetFields() {
        redInput.setText("");
        greenInput.setText("");
        blueInput.setText("");
        hexOutput.setText("");
        colorPreview.setBackgroundColor(Color.WHITE);
    }
}
