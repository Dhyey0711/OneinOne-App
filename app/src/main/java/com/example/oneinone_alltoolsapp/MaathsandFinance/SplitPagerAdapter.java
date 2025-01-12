package com.example.oneinone_alltoolsapp.MaathsandFinance;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SplitPagerAdapter extends FragmentPagerAdapter {

    public SplitPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SplitByAmountFragment();
            case 1:
                return new SplitByPercentageFragment();
            default:
                return new SplitByAmountFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Split by Amount";
            case 1:
                return "Split by Percentage";
            default:
                return null;
        }
    }
}
