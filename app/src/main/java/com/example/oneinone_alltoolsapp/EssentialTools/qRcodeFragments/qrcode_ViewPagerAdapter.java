package com.example.oneinone_alltoolsapp.EssentialTools.qRcodeFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class qrcode_ViewPagerAdapter extends FragmentStateAdapter {

    public qrcode_ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentQrWifiGeneratorActivity();
            case 1:
                return new FragmentQrEventCardActivity();
            case 2:
                return new FragmentVisitingCardActivity(); // This should now be working
            default:
                return new FragmentQrWifiGeneratorActivity();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
