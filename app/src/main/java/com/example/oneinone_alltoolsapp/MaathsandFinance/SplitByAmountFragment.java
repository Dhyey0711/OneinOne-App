package com.example.oneinone_alltoolsapp.MaathsandFinance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import com.example.oneinone_alltoolsapp.R;

public class SplitByAmountFragment extends Fragment {

    private EditText etTotalAmount, etSplitAmount;
    private Button btnSplitAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_split_by_amount, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etTotalAmount = view.findViewById(R.id.et_total_amount);
        etSplitAmount = view.findViewById(R.id.et_split_amount);
        btnSplitAmount = view.findViewById(R.id.btn_split_amount);

        btnSplitAmount.setOnClickListener(v -> splitByAmount());
    }

    private void splitByAmount() {
        try {
            double totalAmount = Double.parseDouble(etTotalAmount.getText().toString());
            double splitAmount = Double.parseDouble(etSplitAmount.getText().toString());

            if (splitAmount <= 0 || totalAmount <= 0) {
                showDialog("Error", "Amounts must be positive numbers.");
                return;
            }

            int numberOfSplits = (int) Math.floor(totalAmount / splitAmount);
            double remainder = totalAmount % splitAmount;

            showDialog("Result", "Number of splits: " + numberOfSplits + "\nRemainder: " + remainder);
        } catch (NumberFormatException e) {
            showDialog("Error", "Please enter valid numbers.");
        }
    }

    private void showDialog(String title, String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
