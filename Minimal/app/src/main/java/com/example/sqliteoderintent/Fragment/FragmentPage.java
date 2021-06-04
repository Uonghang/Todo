package com.example.sqliteoderintent.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class FragmentPage extends FragmentStatePagerAdapter {
    private int number=3;
    public FragmentPage(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LenlichFragemt();
            case 1:
                return new UserFragment();
            case 2:
                return new CaidatFragment();

            default: return new LenlichFragemt();
        }
    }

    @Override
    public int getCount() {
        return number;
    }
}
