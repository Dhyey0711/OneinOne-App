package com.example.oneinone_alltoolsapp.EssentialTools.qRcodeFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.oneinone_alltoolsapp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class FragmentQrWifiGeneratorActivity extends Fragment {

    private EditText editTextSerialId, editTextConfirmPassword;
    private Button buttonGenerateQR, buttonShareQR;
    private ImageView imageViewQRCode;
    private Bitmap qrCodeBitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr_wifi_generator_activity, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Initialize views
        editTextSerialId = view.findViewById(R.id.editTextSerialId);
        editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);
        buttonGenerateQR = view.findViewById(R.id.buttonGenerateQR);
        imageViewQRCode = view.findViewById(R.id.imageViewQRCode);
        buttonShareQR = view.findViewById(R.id.buttonShareQR);

        // Generate QR Code button click
        buttonGenerateQR.setOnClickListener(v -> {
            String serialId = editTextSerialId.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();

            if (!serialId.isEmpty() && !confirmPassword.isEmpty()) {
                String combinedText = serialId + " " + confirmPassword;
                generateQRCode(combinedText);
            } else {
                Toast.makeText(getActivity(), "Please enter both fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Share QR Code button click
        buttonShareQR.setOnClickListener(v -> {
            if (qrCodeBitmap != null) {
                shareQRCode();
            } else {
                Toast.makeText(getActivity(), "Please generate a QR code first", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void generateQRCode(String text) {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            BitMatrix bitMatrix = barcodeEncoder.encode(text, BarcodeFormat.QR_CODE, 400, 400);
            qrCodeBitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageViewQRCode.setImageBitmap(qrCodeBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Failed to generate QR Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareQRCode() {
        if (qrCodeBitmap != null) {
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), qrCodeBitmap, "QRCode", null);
            Uri uri = Uri.parse(path);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra(Intent.EXTRA_TEXT, "Here is my QR Code");
            startActivity(Intent.createChooser(intent, "Share QR Code"));
        } else {
            Toast.makeText(getActivity(), "QR Code is not available", Toast.LENGTH_SHORT).show();
        }
    }
}
