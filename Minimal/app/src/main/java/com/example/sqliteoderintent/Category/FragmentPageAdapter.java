package com.example.sqliteoderintent.Category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    private int number=3;
    public FragmentPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new LichhocFragment();
            case 1: return new LichLamFragment();
            case 2: return new LichthiFragment();
            default:return  new LichhocFragment();

        }
    }

    @Override
    public int getCount() {
        return number;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      switch (position){
          case 0:return  "Lich hoc";
          case 1:return  "Lich lam";
          case  2:return "Lich thi";
          default: return "Lich hoc";
      }
    }
}
