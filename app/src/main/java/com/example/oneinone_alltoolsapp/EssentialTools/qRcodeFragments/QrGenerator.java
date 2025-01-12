package com.example.oneinone_alltoolsapp.EssentialTools.qRcodeFragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;
import com.example.oneinone_alltoolsapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QrGenerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ViewPager2 viewPager = findViewById(R.id.viewPager1);
        TabLayout tabLayout = findViewById(R.id.tabLayout1);

        // Instantiate your custom adapter
        qrcode_ViewPagerAdapter adapter = new qrcode_ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Ensure user input is enabled for horizontal scrolling
        viewPager.setUserInputEnabled(true);

        // Set up TabLayout with ViewPager2 using TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("QR Card Generator");
                            break;
                        case 1:
                            tab.setText("QR Event Card");
                            break;
                        case 2:
                            tab.setText("Visiting Card");
                            break;
                    }
                }).attach();
    }
}
