package com.example.oneinone_alltoolsapp.FunwithOneinOne;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

public class TicTacActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    int flag = 0;
    int count = 0;
    int player1Score = 0, player2Score = 0;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    MediaPlayer winSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        init();
        showInstructionsDialog();

        // Initialize MediaPlayer with the winning sound
        winSound = MediaPlayer.create(this, R.raw.win_sound);
    }

    private void init() {
        btn1 = findViewById(R.id.b1);
        btn2 = findViewById(R.id.b2);
        btn3 = findViewById(R.id.b3);
        btn4 = findViewById(R.id.b4);
        btn5 = findViewById(R.id.b5);
        btn6 = findViewById(R.id.b6);
        btn7 = findViewById(R.id.b7);
        btn8 = findViewById(R.id.b8);
        btn9 = findViewById(R.id.b9);

        // Set unique content descriptions for accessibility
        btn1.setContentDescription("Button 1");
        btn2.setContentDescription("Button 2");
        btn3.setContentDescription("Button 3");
        btn4.setContentDescription("Button 4");
        btn5.setContentDescription("Button 5");
        btn6.setContentDescription("Button 6");
        btn7.setContentDescription("Button 7");
        btn8.setContentDescription("Button 8");
        btn9.setContentDescription("Button 9");
    }

    private void showInstructionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TicTacActivity.this);
        builder.setTitle("Instructions")
                .setMessage("Tap a cell to make a move. The game will end when someone wins or the board is full.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void check(View view) {
        Button btnCurrent = (Button) view;

        if (!btnCurrent.getText().toString().equals("")) {
            Toast.makeText(this, "Button already clicked!", Toast.LENGTH_SHORT).show();
            return;
        }

        count++;
        if (flag == 0) {
            btnCurrent.setText("X");
            btnCurrent.setTextColor(Color.WHITE); // Set color to white
            flag = 1;
        } else {
            btnCurrent.setText("O");
            btnCurrent.setTextColor(Color.WHITE); // Set color to white
            flag = 0;
        }

        if (count > 4) {
            b1 = btn1.getText().toString();
            b2 = btn2.getText().toString();
            b3 = btn3.getText().toString();
            b4 = btn4.getText().toString();
            b5 = btn5.getText().toString();
            b6 = btn6.getText().toString();
            b7 = btn7.getText().toString();
            b8 = btn8.getText().toString();
            b9 = btn9.getText().toString();

            // Check for winner
            if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                showDialog("Congratulations! Winner is: " + b1);
            } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                showDialog("Congratulations! Winner is: " + b4);
            } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                showDialog("Congratulations! Winner is: " + b7);
            } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                showDialog("Congratulations! Winner is: " + b1);
            } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                showDialog("Congratulations! Winner is: " + b2);
            } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                showDialog("Congratulations! Winner is: " + b3);
            } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                showDialog("Congratulations! Winner is: " + b1);
            } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                showDialog("Congratulations! Winner is: " + b3);
            } else if (count == 9) {
                // If all buttons are filled and no winner, it's a draw
                showDialog("It's a draw!");
            }
        }
    }

    private void showDialog(final String message) {
        // Update the score before showing the dialog
        updateScore(message);

        String recentScoreMessage = "Recent Score: X - " + player1Score + " | O - " + player2Score;
        AlertDialog.Builder builder = new AlertDialog.Builder(TicTacActivity.this);
        builder.setMessage(message + "\n\n" + recentScoreMessage)
                .setCancelable(false)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        stopSound(); // Stop the sound effect immediately
                        resetBoard();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        // Play the winning sound if there is a winner
        if (message.contains("Congratulations")) {
            winSound.start();
        }
    }

    private void stopSound() {
        if (winSound.isPlaying()) {
            winSound.pause();
            winSound.seekTo(0); // Reset to start
        }
    }

    private void updateScore(String message) {
        if (message.contains("Congratulations")) {
            String winner = message.split(": ")[1];
            if (winner.equals("X")) {
                player1Score++;
            } else if (winner.equals("O")) {
                player2Score++;
            }
        }
    }

    private void resetBoard() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        count = 0;
        flag = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (winSound != null) {
            winSound.release();
        }
    }
}
