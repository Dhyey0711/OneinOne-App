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

public class SplitByPercentageFragment extends Fragment {

    private EditText etTotalAmountPercentage, etSplitPercentage;
    private Button btnSplitPercentage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_split_by_percentage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etTotalAmountPercentage = view.findViewById(R.id.et_total_amount_percentage);
        etSplitPercentage = view.findViewById(R.id.et_split_percentage);
        btnSplitPercentage = view.findViewById(R.id.btn_split_percentage);

        btnSplitPercentage.setOnClickListener(v -> splitByPercentage());
    }

    private void splitByPercentage() {
        try {
            double totalAmount = Double.parseDouble(etTotalAmountPercentage.getText().toString());
            double splitPercentage = Double.parseDouble(etSplitPercentage.getText().toString());

            if (splitPercentage <= 0 || totalAmount <= 0 || splitPercentage > 100) {
                showDialog("Error", "Percentage must be between 0 and 100.");
                return;
            }

            double splitAmount = (totalAmount * splitPercentage) / 100;
            showDialog("Result", "Split Amount: " + splitAmount);
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
