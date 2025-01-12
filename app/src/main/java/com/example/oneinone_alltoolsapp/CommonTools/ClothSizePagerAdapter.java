package com.example.oneinone_alltoolsapp.CommonTools;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ClothSizePagerAdapter extends FragmentPagerAdapter {

    public ClothSizePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MensClothSizeFragment();
            case 1:
                return new WomensClothSizeFragment();
            default:
                return new MensClothSizeFragment();
        }
    }

    @Override
    public int getCount() {
        return 2; // Number of tabs
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Men";
            case 1:
                return "Women";
            default:
                return null;
        }
    }
}
