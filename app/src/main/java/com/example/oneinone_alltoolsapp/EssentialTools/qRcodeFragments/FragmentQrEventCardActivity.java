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

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.oneinone_alltoolsapp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class FragmentQrEventCardActivity extends Fragment {

    private EditText editTextLocation, editTextSummary, editTextStartDate, editTextEndDate, editTextURL;
    private Button buttonGenerateEventQR, buttonShareEventQR;
    private ImageView imageViewEventQRCode;
    private Bitmap qrCodeBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr_event_card_activity, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Initialize views
        editTextLocation = view.findViewById(R.id.editTextLocation);
        editTextSummary = view.findViewById(R.id.editTextSummary);
        editTextStartDate = view.findViewById(R.id.editTextStartDate);
        editTextEndDate = view.findViewById(R.id.editTextEndDate);
        editTextURL = view.findViewById(R.id.editTextURL);
        buttonGenerateEventQR = view.findViewById(R.id.buttonGenerateEventQR);
        imageViewEventQRCode = view.findViewById(R.id.imageViewEventQRCode);
        buttonShareEventQR = view.findViewById(R.id.buttonShareEventQR);

        // Generate QR Code button click
        buttonGenerateEventQR.setOnClickListener(v -> {
            String location = editTextLocation.getText().toString();
            String summary = editTextSummary.getText().toString();
            String startDate = editTextStartDate.getText().toString();
            String endDate = editTextEndDate.getText().toString();
            String url = editTextURL.getText().toString();

            if (!location.isEmpty() && !summary.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() && !url.isEmpty()) {
                String eventDetails = "Location: " + location + "\nSummary: " + summary + "\nStart Date: " + startDate + "\nEnd Date: " + endDate + "\nURL: " + url;
                generateQRCode(eventDetails);
            } else {
                Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Share QR Code button click
        buttonShareEventQR.setOnClickListener(v -> {
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
            imageViewEventQRCode.setImageBitmap(qrCodeBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Failed to generate QR Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareQRCode() {
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), qrCodeBitmap, "EventQRCode", null);
        Uri uri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT, "Here is the event QR Code");
        startActivity(Intent.createChooser(intent, "Share QR Code"));
    }
}
