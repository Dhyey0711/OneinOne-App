package com.example.oneinone_alltoolsapp.EssentialTools;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.oneinone_alltoolsapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scanner extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Button scanBtn;
    private TextView messageText, messageFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        scanBtn = findViewById(R.id.scanBtn);
        messageText = findViewById(R.id.textContent);
        messageFormat = findViewById(R.id.textFormat);

        scanBtn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
    }

    @Override
    public void onClick(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                String scannedData = intentResult.getContents();
                String scannedFormat = intentResult.getFormatName();

                messageText.setText(scannedData);
                messageFormat.setText(scannedFormat);

                if (scannedFormat.equals("QR_CODE")) {
                    showQRDialog(scannedData);
                } else {
                    showBarcodeDialog(scannedData);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showQRDialog(String data) {
        // Create a LinearLayout and set its properties
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        // Create and configure the TextViews
        TextView textContent = new TextView(this);
        textContent.setText("Content: " + data);
        textContent.setPadding(0, 0, 0, 16);

        TextView textFormat = new TextView(this);
        textFormat.setText("Format: QR_CODE");

        // Add TextViews to the LinearLayout
        layout.addView(textContent);
        layout.addView(textFormat);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("QR Code")
                .setView(layout)  // Set the LinearLayout as the dialog view
                .setItems(new CharSequence[]{"Copy", "Share", "Cancel"}, (dialog, which) -> {
                    switch (which) {
                        case 0: // Copy
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Scanned QR Code", data);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(this, "Data copied", Toast.LENGTH_SHORT).show();
                            break;
                        case 1: // Share
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, data);
                            startActivity(Intent.createChooser(shareIntent, "Share data using"));
                            break;
                        case 2: // Cancel
                            dialog.dismiss();
                            break;
                    }
                });
        builder.create().show();
    }

    private void showBarcodeDialog(String barcodeData) {
        // Create a LinearLayout and set its properties
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        // Create and configure the TextViews
        TextView textContent = new TextView(this);
        textContent.setText("Content: " + barcodeData);
        textContent.setPadding(0, 0, 0, 16);

        TextView textFormat = new TextView(this);
        textFormat.setText("Format: BARCODE");

        // Add TextViews to the LinearLayout
        layout.addView(textContent);
        layout.addView(textFormat);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Barcode")
                .setView(layout)  // Set the LinearLayout as the dialog view
                .setItems(new CharSequence[]{"Copy", "Share", "Cancel"}, (dialog, which) -> {
                    switch (which) {
                        case 0: // Copy
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Scanned Barcode", barcodeData);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(this, "Barcode copied", Toast.LENGTH_SHORT).show();
                            break;
                        case 1: // Share
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, barcodeData);
                            startActivity(Intent.createChooser(shareIntent, "Share barcode using"));
                            break;
                        case 2: // Cancel
                            dialog.dismiss();
                            break;
                    }
                });
        builder.create().show();
    }


    private void handleWiFiConnection(String data) {
        // Parse the QR code data for WiFi connection
        String[] parts = data.split(";");
        String ssid = parts[1].split(":")[1];
        String password = parts[3].split(":")[1];
        String encryption = parts[2].split(":")[1];

        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", password);

        if (encryption.equals("WPA") || encryption.equals("WPA2")) {
            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        } else {
            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int networkId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(networkId, true);
        wifiManager.reconnect();

        Toast.makeText(this, "Connecting to WiFi network...", Toast.LENGTH_SHORT).show();
    }

    private void handleAddToContactsOrCalendar(String data) {
        if (data.startsWith("BEGIN:VCARD")) {
            // Adding contact
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, data);
            startActivity(intent);
        } else if (data.startsWith("BEGIN:VEVENT")) {
            // Adding event
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType("vnd.android.cursor.item/event");
            // Set event details here (e.g., date, title)
            startActivity(intent);
        }
    }
}
