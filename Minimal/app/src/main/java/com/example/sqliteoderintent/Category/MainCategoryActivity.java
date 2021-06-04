package com.example.sqliteoderintent.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.android.material.tabs.TabLayout;
import com.example.sqliteoderintent.R;
public class MainCategoryActivity extends AppCompatActivity {

    FragmentPageAdapter fragmentPageAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
        tabLayout = findViewById(R.id.tablayout12);
        viewPager = findViewById(R.id.viewpagedanhba);
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), FragmentPageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}