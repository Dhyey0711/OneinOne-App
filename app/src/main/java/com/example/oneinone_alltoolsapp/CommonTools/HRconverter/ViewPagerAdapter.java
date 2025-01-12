package com.example.oneinone_alltoolsapp.CommonTools.HRconverter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new hextorgbfragment();
            case 1:
                return new rgbtohexfragment();
            default:
                return new hextorgbfragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
