package com.example.oneinone_alltoolsapp.CommonTools;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Morse extends AppCompatActivity {

    private EditText editText;
    private TextView textViewResult;

    private final HashMap<Character, String> textToMorseMap = new HashMap<>();
    private final HashMap<String, Character> morseToTextMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        editText = findViewById(R.id.editText);
        textViewResult = findViewById(R.id.textViewResult);
        Button convert = findViewById(R.id.convert);
        Button copyResult = findViewById(R.id.copyResult);
        Button shareResult = findViewById(R.id.shareResult);

        initializeMaps();

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(Morse.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (isValidMorseCode(input)) {
                        String result = convertMorseToText(input);
                        if (result.isEmpty()) {
                            Toast.makeText(Morse.this, "Invalid Morse code", Toast.LENGTH_SHORT).show();
                        } else {
                            textViewResult.setText(result);
                        }
                    } else if (isValidText(input)) {
                        textViewResult.setText(convertTextToMorse(input));
                    } else {
                        Toast.makeText(Morse.this, "Invalid input. Please enter valid text or Morse code.", Toast.LENGTH_SHORT).show();
                        textViewResult.setText(""); // Clear result
                    }
                } catch (Exception e) {
                    // Log the exception and show a user-friendly message
                    e.printStackTrace();
                    Toast.makeText(Morse.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                    textViewResult.setText(""); // Clear result
                }
            }
        });

        copyResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipboard(textViewResult.getText().toString());
            }
        });

        shareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(textViewResult.getText().toString());
            }
        });
    }

    private void initializeMaps() {
        // Initialize text to Morse map
        textToMorseMap.put('A', ".-");
        textToMorseMap.put('B', "-...");
        textToMorseMap.put('C', "-.-.");
        textToMorseMap.put('D', "-..");
        textToMorseMap.put('E', ".");
        textToMorseMap.put('F', "..-.");
        textToMorseMap.put('G', "--.");
        textToMorseMap.put('H', "....");
        textToMorseMap.put('I', "..");
        textToMorseMap.put('J', ".---");
        textToMorseMap.put('K', "-.-");
        textToMorseMap.put('L', ".-..");
        textToMorseMap.put('M', "--");
        textToMorseMap.put('N', "-.");
        textToMorseMap.put('O', "---");
        textToMorseMap.put('P', ".--.");
        textToMorseMap.put('Q', "--.-");
        textToMorseMap.put('R', ".-.");
        textToMorseMap.put('S', "...");
        textToMorseMap.put('T', "-");
        textToMorseMap.put('U', "..-");
        textToMorseMap.put('V', "...-");
        textToMorseMap.put('W', ".--");
        textToMorseMap.put('X', "-..-");
        textToMorseMap.put('Y', "-.--");
        textToMorseMap.put('Z', "--..");

        // Initialize Morse to text map
        for (HashMap.Entry<Character, String> entry : textToMorseMap.entrySet()) {
            morseToTextMap.put(entry.getValue(), entry.getKey());
        }
    }

    private String convertTextToMorse(String text) {
        StringBuilder morseCode = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morseCode.append(" / ");
            } else if (textToMorseMap.containsKey(c)) {
                morseCode.append(textToMorseMap.get(c)).append(" ");
            }
        }
        return morseCode.toString().trim();
    }

    private String convertMorseToText(String morse) {
        StringBuilder text = new StringBuilder();
        String[] words = morse.trim().split(" / "); // Split Morse code into words
        for (String word : words) {
            String[] codes = word.split(" "); // Split Morse code into letters
            for (String code : codes) {
                if (morseToTextMap.containsKey(code)) {
                    text.append(morseToTextMap.get(code));
                } else {
                    // Handle invalid Morse code
                    text.append("?"); // Append a placeholder for unknown Morse code
                }
            }
            text.append(" "); // Separate words with space
        }
        return text.toString().trim();
    }

    private boolean isValidMorseCode(String morse) {
        // Morse code should only contain dots, dashes, spaces, and slashes
        return Pattern.matches("([.-]+ ?)+(/ ?)*", morse.trim());
    }

    private boolean isValidText(String text) {
        // Simple check for non-empty text with alphanumeric characters and spaces
        return Pattern.matches("[a-zA-Z0-9 ]+", text.trim());
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Result", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void shareText(String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}