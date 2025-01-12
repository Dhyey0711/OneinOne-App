package com.example.oneinone_alltoolsapp.CommonTools;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CompressImage extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Bitmap originalBitmap;
    private Bitmap compressedBitmap;
    private ImageView ivImagePreview;
    private ImageView ivCompressedImage;
    private TextView tvOriginalSize;
    private TextView tvCompressedSize;
    private File savedFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress_image);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Button btnSelectImage = findViewById(R.id.btn_select_image);
        Button btnCompressImage = findViewById(R.id.btn_compress_image);
        Button btnSaveImage = findViewById(R.id.btn_save_image);
        ivImagePreview = findViewById(R.id.iv_image_preview);
        ivCompressedImage = findViewById(R.id.iv_compressed_image);
        tvOriginalSize = findViewById(R.id.tv_original_size);
        tvCompressedSize = findViewById(R.id.tv_compressed_size);

        btnSelectImage.setOnClickListener(v -> openImageChooser());
        btnCompressImage.setOnClickListener(v -> compressImage());
        btnSaveImage.setOnClickListener(v -> {
            if (compressedBitmap != null) {
                saveImageToStorage();
            } else {
                Toast.makeText(CompressImage.this, "Please compress the image first", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                originalBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivImagePreview.setImageBitmap(originalBitmap);
                tvOriginalSize.setText("Original Size: " + getImageSize(originalBitmap) + " KB");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void compressImage() {
        if (originalBitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            originalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            compressedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ivCompressedImage.setImageBitmap(compressedBitmap);
            tvCompressedSize.setText("Compressed Size: " + getImageSize(compressedBitmap) + " KB");
        } else {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToStorage() {
        try {
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CompressedImages");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            savedFile = new File(directory, "compressed_image.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(savedFile);
            compressedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            // Notify the media scanner to add the image to the gallery
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(savedFile)));

            // Show the confirmation dialog
            showSaveConfirmationDialog();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSaveConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Image Saved")
                .setMessage("The image has been saved to your mobile phone.")
                .setPositiveButton("OK", null)
                .show();
    }

    private String getImageSize(Bitmap bitmap) {
        if (bitmap == null) return "0";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return String.format("%.2f", byteArrayOutputStream.size() / 1024.0);
    }
}
