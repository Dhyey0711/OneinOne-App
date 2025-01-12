package com.example.oneinone_alltoolsapp.EssentialTools;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.widget.CalendarView;
import android.widget.TextView;

import com.example.oneinone_alltoolsapp.R;

public class Calender extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textViewSelectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        calendarView = findViewById(R.id.calendarView);
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Display the selected date
                String selectedDate = String.format("%d/%d/%d", dayOfMonth, month + 1, year);
                textViewSelectedDate.setText("Selected Date: " + selectedDate);
            }
        });
    }
}
