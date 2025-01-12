package com.example.oneinone_alltoolsapp.CommonTools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.oneinone_alltoolsapp.R;

public class MensClothSizeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mens_cloth_size, container, false);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Reference the ImageView and set the image resource if needed
        ImageView mensClothImageView = view.findViewById(R.id.mensClothImageView);
        // Optionally, you can dynamically set the image resource here if needed
        // mensClothImageView.setImageResource(R.drawable.your_image_resource);

        return view;
    }
}
