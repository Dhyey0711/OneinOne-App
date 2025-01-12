package com.example.oneinone_alltoolsapp.CommonTools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.oneinone_alltoolsapp.R;

import java.security.SecureRandom;

public class EncryptFragment extends Fragment {

    private EditText inputText, encryptedText;
    private Button encryptButton, generateKeyButton, copyButton, shareButton;
    private String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_encrypt_fragment, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        inputText = view.findViewById(R.id.inputText);
        encryptedText = view.findViewById(R.id.encryptedText);
        encryptButton = view.findViewById(R.id.encryptButton);
        generateKeyButton = view.findViewById(R.id.generateKeyButton);
        copyButton = view.findViewById(R.id.copyButton);
        shareButton = view.findViewById(R.id.shareButton);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    key = generateKey();
                    encryptedText.setText(encrypt(text, key));
                } else {
                    Toast.makeText(getActivity(), "Please enter text to encrypt", Toast.LENGTH_SHORT).show();
                }
            }
        });

        generateKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = generateKey();
                encryptedText.setText(key);
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = encryptedText.getText().toString();
                if (!TextUtils.isEmpty(textToCopy)) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Encrypted Text", textToCopy);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getActivity(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Nothing to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = encryptedText.getText().toString();
                if (!TextUtils.isEmpty(textToShare)) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                    startActivity(Intent.createChooser(shareIntent, "Share via"));
                } else {
                    Toast.makeText(getActivity(), "Nothing to share", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private String encrypt(String text, String key) {
        byte[] data = (text + key).getBytes();
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    private String generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[16];
        random.nextBytes(keyBytes);
        return Base64.encodeToString(keyBytes, Base64.DEFAULT);
    }
}
