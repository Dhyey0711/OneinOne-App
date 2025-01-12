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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.oneinone_alltoolsapp.R;

public class hextorgbfragment extends Fragment {

    private EditText hexInput, redOutput, greenOutput, blueOutput, cssCodeOutput;
    private ImageView colorPreview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hextorgbfragment, container, false);

        hexInput = view.findViewById(R.id.hexInput);
        redOutput = view.findViewById(R.id.redOutput);
        greenOutput = view.findViewById(R.id.greenOutput);
        blueOutput = view.findViewById(R.id.blueOutput);
        cssCodeOutput = view.findViewById(R.id.cssCodeOutput);
        colorPreview = view.findViewById(R.id.colorPreview);

        Button convertButton = view.findViewById(R.id.convertHexToRgb);
        Button copyButton = view.findViewById(R.id.copyCssCode);
        Button shareButton = view.findViewById(R.id.shareColor);
        Button resetButton = view.findViewById(R.id.reset);

        convertButton.setOnClickListener(v -> convertHexToRgb());
        copyButton.setOnClickListener(v -> copyCssCode());
        shareButton.setOnClickListener(v -> shareColor());
        resetButton.setOnClickListener(v -> resetFields());

        return view;
    }

    private void convertHexToRgb() {
        String hex = hexInput.getText().toString();
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }

        if (hex.length() != 6) {
            Toast.makeText(getActivity(), "Invalid Hex Code", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int r = Integer.parseInt(hex.substring(0, 2), 16);
            int g = Integer.parseInt(hex.substring(2, 4), 16);
            int b = Integer.parseInt(hex.substring(4, 6), 16);

            redOutput.setText(String.valueOf(r));
            greenOutput.setText(String.valueOf(g));
            blueOutput.setText(String.valueOf(b));
            cssCodeOutput.setText(String.format("rgb(%d, %d, %d)", r, g, b));
            colorPreview.setBackgroundColor(Color.rgb(r, g, b));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Invalid Hex Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyCssCode() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("CSS Code", cssCodeOutput.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity(), "CSS Code Copied", Toast.LENGTH_SHORT).show();
    }

    private void shareColor() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "CSS Code: " + cssCodeOutput.getText().toString());
        startActivity(Intent.createChooser(intent, "Share Color"));
    }

    private void resetFields() {
        hexInput.setText("");
        redOutput.setText("");
        greenOutput.setText("");
        blueOutput.setText("");
        cssCodeOutput.setText("");
        colorPreview.setBackgroundColor(Color.WHITE);
    }
}
