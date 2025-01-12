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

public class DecryptFragment extends Fragment {

    private EditText encryptedText, decryptionKey, outputText;
    private Button decryptButton, copyButton, shareButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_decrypt_fragment, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        encryptedText = view.findViewById(R.id.encryptedText);
        decryptionKey = view.findViewById(R.id.decryptionKey);
        outputText = view.findViewById(R.id.outputText);
        decryptButton = view.findViewById(R.id.decryptButton);
        copyButton = view.findViewById(R.id.copyButton);
        shareButton = view.findViewById(R.id.shareButton);

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encrypted = encryptedText.getText().toString();
                String decryptionKeyText = decryptionKey.getText().toString();
                if (!TextUtils.isEmpty(encrypted) && !TextUtils.isEmpty(decryptionKeyText)) {
                    outputText.setText(decrypt(encrypted, decryptionKeyText));
                } else {
                    Toast.makeText(getActivity(), "Please enter the encrypted text and decryption key", Toast.LENGTH_SHORT).show();
                }
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = outputText.getText().toString();
                if (!TextUtils.isEmpty(textToCopy)) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Decrypted Text", textToCopy);
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
                String textToShare = outputText.getText().toString();
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

    private String decrypt(String encryptedText, String key) {
        byte[] data = Base64.decode(encryptedText, Base64.DEFAULT);
        String decryptedText = new String(data);
        return decryptedText.replace(key, "");
    }
}
