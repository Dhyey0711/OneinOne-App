package com.example.oneinone_alltoolsapp.EssentialTools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ShareCompat;

import com.example.oneinone_alltoolsapp.R;

import java.security.SecureRandom;

public class Password extends AppCompatActivity {

    private EditText editTextPasswordLength;
    private CheckBox checkBoxIncludeNumber;
    private Button buttonGeneratePassword, buttonCopyPassword, buttonSharePassword, buttonReset;
    private TextView textViewGeneratedPassword;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String ALL_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        editTextPasswordLength = findViewById(R.id.editTextPasswordLength);
        checkBoxIncludeNumber = findViewById(R.id.checkBoxIncludeNumber);
        buttonGeneratePassword = findViewById(R.id.buttonGeneratePassword);
        buttonCopyPassword = findViewById(R.id.buttonCopyPassword);
        buttonSharePassword = findViewById(R.id.buttonSharePassword);
        buttonReset = findViewById(R.id.buttonReset);
        textViewGeneratedPassword = findViewById(R.id.textViewGeneratedPassword);

        buttonGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePassword();
            }
        });

        buttonCopyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyPasswordToClipboard();
            }
        });

        buttonSharePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePassword();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    private void generatePassword() {
        try {
            int passwordLength = Integer.parseInt(editTextPasswordLength.getText().toString());
            if (passwordLength <= 0) {
                throw new NumberFormatException("Password length must be greater than 0");
            }
            boolean includeNumber = checkBoxIncludeNumber.isChecked();
            String generatedPassword = generateRandomPassword(passwordLength, includeNumber);

            textViewGeneratedPassword.setText(generatedPassword);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a valid password length", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateRandomPassword(int length, boolean includeNumber) {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        String chars = includeNumber ? ALL_CHARS : CHAR_LOWER + CHAR_UPPER;

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            password.append(chars.charAt(randomIndex));
        }

        return password.toString();
    }

    private void copyPasswordToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Generated Password", textViewGeneratedPassword.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Password copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void sharePassword() {
        String password = textViewGeneratedPassword.getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "No password to share", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setText(password)
                .setType("text/plain")
                .getIntent()
                .setAction(Intent.ACTION_SEND);
        startActivity(Intent.createChooser(shareIntent, "Share Password"));
    }

    private void resetFields() {
        editTextPasswordLength.setText("");
        checkBoxIncludeNumber.setChecked(false);
        textViewGeneratedPassword.setText("");
    }
}