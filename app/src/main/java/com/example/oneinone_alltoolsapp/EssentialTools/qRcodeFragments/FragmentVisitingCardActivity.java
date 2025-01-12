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
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class FragmentVisitingCardActivity extends Fragment {

    private EditText editTextName, editTextFullName, editTextCompanyName, editTextTitle, editTextTelephone, editTextEmail, editTextAddress, editTextURL, editTextNote;
    private ImageView imageViewVisitingCardQRCode;
    private Bitmap qrCodeBitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_visiting_card_activity, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        editTextName = rootView.findViewById(R.id.editTextName);
        editTextFullName = rootView.findViewById(R.id.editTextFullName);
        editTextCompanyName = rootView.findViewById(R.id.editTextCompanyName);
        editTextTitle = rootView.findViewById(R.id.editTextTitle);
        editTextTelephone = rootView.findViewById(R.id.editTextTelephone);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextAddress = rootView.findViewById(R.id.editTextAddress);
        editTextURL = rootView.findViewById(R.id.editTextURL);
        editTextNote = rootView.findViewById(R.id.editTextNote);
        imageViewVisitingCardQRCode = rootView.findViewById(R.id.imageViewVisitingCardQRCode);

        Button buttonGenerateVisitingCardQR = rootView.findViewById(R.id.buttonGenerateVisitingCardQR);
        Button buttonShareVisitingCardQR = rootView.findViewById(R.id.buttonShareVisitingCardQR);

        buttonGenerateVisitingCardQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrData = getQRData();
                if (!qrData.isEmpty()) {
                    try {
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        qrCodeBitmap = barcodeEncoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400);
                        imageViewVisitingCardQRCode.setImageBitmap(qrCodeBitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed to generate QR code", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonShareVisitingCardQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qrCodeBitmap != null) {
                    try {
                        String bitmapPath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), qrCodeBitmap, "QR Code", null);
                        Uri bitmapUri = Uri.parse(bitmapPath);
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("image/png");
                        shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                        startActivity(Intent.createChooser(shareIntent, "Share QR Code"));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed to share QR code", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please generate a QR code first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private String getQRData() {
        return editTextName.getText().toString() + "\n" +
                editTextFullName.getText().toString() + "\n" +
                editTextCompanyName.getText().toString() + "\n" +
                editTextTitle.getText().toString() + "\n" +
                editTextTelephone.getText().toString() + "\n" +
                editTextEmail.getText().toString() + "\n" +
                editTextAddress.getText().toString() + "\n" +
                editTextURL.getText().toString() + "\n" +
                editTextNote.getText().toString();
    }
}
