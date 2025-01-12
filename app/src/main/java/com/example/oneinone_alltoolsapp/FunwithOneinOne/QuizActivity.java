package com.example.oneinone_alltoolsapp.FunwithOneinOne;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class QuizActivity extends AppCompatActivity {

        private TextView tvQuestion, tvTimer, tvScore;
        private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4, btnStart;
        private List<Question> questionsList;
        private Question currentQuestion;
        private int score = 0;
        private int questionIndex = 0;
        private CountDownTimer timer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                tvQuestion = findViewById(R.id.tvQuestion);
                tvTimer = findViewById(R.id.tvTimer);
                tvScore = findViewById(R.id.tvScore);
                btnAnswer1 = findViewById(R.id.btnAnswer1);
                btnAnswer2 = findViewById(R.id.btnAnswer2);
                btnAnswer3 = findViewById(R.id.btnAnswer3);
                btnAnswer4 = findViewById(R.id.btnAnswer4);

                btnAnswer1.setOnClickListener(answerClickListener);
                btnAnswer2.setOnClickListener(answerClickListener);
                btnAnswer3.setOnClickListener(answerClickListener);
                btnAnswer4.setOnClickListener(answerClickListener);

                loadQuestions();
                startGame();
            }

            private void loadQuestions() {
                questionsList = new ArrayList<>();

                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                questionsList.add(new Question("37 - 16 =", "21", "20", "22", "19"));
                questionsList.add(new Question("6 x 4 =", "24", "23", "25", "22"));
                questionsList.add(new Question("63 ÷ 7 =", "9", "8", "10", "11"));
                questionsList.add(new Question("18 + 14 =", "32", "31", "33", "30"));
                questionsList.add(new Question("54 - 23 =", "31", "30", "32", "29"));
                questionsList.add(new Question("5 x 6 =", "30", "29", "31", "32"));
                questionsList.add(new Question("72 ÷ 8 =", "9", "8", "10", "11"));
                questionsList.add(new Question("22 + 19 =", "41", "40", "42", "39"));
                questionsList.add(new Question("45 - 16 =", "29", "28", "30", "31"));
                questionsList.add(new Question("8 x 9 =", "72", "71", "73", "70"));
                questionsList.add(new Question("56 ÷ 8 =", "7", "6", "8", "9"));
                questionsList.add(new Question("27 + 13 =", "40", "39", "41", "38"));
                questionsList.add(new Question("33 - 17 =", "16", "15", "17", "18"));
                questionsList.add(new Question("9 x 7 =", "63", "62", "64", "61"));
                questionsList.add(new Question("81 ÷ 9 =", "9", "8", "10", "11"));
                questionsList.add(new Question("20 + 25 =", "45", "44", "46", "43"));
                questionsList.add(new Question("40 - 18 =", "22", "21", "23", "20"));
                questionsList.add(new Question("7 x 4 =", "28", "27", "29", "26"));
                questionsList.add(new Question("72 ÷ 9 =", "8", "7", "9", "10"));
                questionsList.add(new Question("15 + 16 =", "31", "30", "32", "29"));
                questionsList.add(new Question("50 - 22 =", "28", "27", "29", "30"));
                questionsList.add(new Question("6 x 5 =", "30", "29", "31", "32"));
                questionsList.add(new Question("54 ÷ 6 =", "9", "8", "10", "11"));
                questionsList.add(new Question("23 + 17 =", "40", "39", "41", "38"));
                questionsList.add(new Question("48 - 29 =", "19", "18", "20", "21"));
                questionsList.add(new Question("8 x 6 =", "48", "47", "49", "50"));
                questionsList.add(new Question("96 ÷ 12 =", "8", "7", "9", "10"));
                questionsList.add(new Question("11 + 24 =", "35", "34", "36", "33"));
                questionsList.add(new Question("29 - 14 =", "15", "14", "16", "13"));
                questionsList.add(new Question("7 x 6 =", "42", "41", "43", "40"));
                questionsList.add(new Question("84 ÷ 12 =", "7", "6", "8", "9"));
                questionsList.add(new Question("30 + 22 =", "52", "51", "53", "50"));
                questionsList.add(new Question("40 - 29 =", "11", "10", "12", "9"));
                questionsList.add(new Question("7 x 5 =", "35", "34", "36", "33"));
                questionsList.add(new Question("90 ÷ 10 =", "9", "8", "10", "11"));
                questionsList.add(new Question("29 + 11 =", "40", "39", "41", "38"));
                Collections.shuffle(questionsList);
            }

            private void startGame() {
                score = 0;
                questionIndex = 0;
                tvScore.setText("Score: " + score);

                startTimer();
                loadNextQuestion();
            }

            private void startTimer() {
                timer = new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvTimer.setText("Time: " + millisUntilFinished / 1000 + "s");
                    }

                    @Override
                    public void onFinish() {
                        endGame();
                    }
                }.start();
            }

            private void loadNextQuestion() {
                if (questionIndex < 10) { // Limit to 10 questions
                    currentQuestion = questionsList.get(questionIndex);
                    tvQuestion.setText(currentQuestion.getQuestion());
                    List<String> answers = currentQuestion.getShuffledAnswers();
                    btnAnswer1.setText(answers.get(0));
                    btnAnswer2.setText(answers.get(1));
                    btnAnswer3.setText(answers.get(2));
                    btnAnswer4.setText(answers.get(3));
                    questionIndex++;
                } else {
                    endGame();
                }
            }

            private void endGame() {
                timer.cancel();
                provideFeedback();  // Provide feedback based on the score
            }

            private View.OnClickListener answerClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    if (clickedButton.getText().equals(currentQuestion.getCorrectAnswer())) {
                        score++;
                        tvScore.setText("Score: " + score);
                    }
                    loadNextQuestion();
                }
            };

        private void provideFeedback() {
            String feedbackMessage;

            if (score >= 8) {
                feedbackMessage = "Excellent! Your score is " + score + ". You're doing great in math!";
            } else if (score >= 5) {
                feedbackMessage = "Good job! Your score is " + score + ". You have a good grasp, but there's room for improvement.";
            } else {
                feedbackMessage = "Your score is " + score + ". Don't worry, keep practicing, and you'll get better!";
            }

            new AlertDialog.Builder(this)
                    .setTitle("Game Over")
                    .setMessage(feedbackMessage)
                    .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startGame(); // Restart the game
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();  // Close the activity or navigate to another screen
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

            private static class Question {
                private String question;
                private String correctAnswer;
                private String[] wrongAnswers;

                public Question(String question, String correctAnswer, String... wrongAnswers) {
                    this.question = question;
                    this.correctAnswer = correctAnswer;
                    this.wrongAnswers = wrongAnswers;
                }

                public String getQuestion() {
                    return question;
                }

                public String getCorrectAnswer() {
                    return correctAnswer;
                }

                public List<String> getShuffledAnswers() {
                    List<String> answers = new ArrayList<>();
                    answers.add(correctAnswer);
                    Collections.addAll(answers, wrongAnswers);
                    Collections.shuffle(answers);
                    return answers;
                }
            }
        }