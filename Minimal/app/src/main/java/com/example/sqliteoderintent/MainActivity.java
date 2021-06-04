package com.example.sqliteoderintent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sqliteoderintent.Fragment.FragmentPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    /*private FloatingActionButton add;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Oder> list;
    SQLiteOderHelper sqlite;*/
    FragmentPage fragmentPageAdapter;
    ViewPager viewPager;
    BottomNavigationView bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.navigationBottom);
        viewPager = findViewById(R.id.viewpage);
        fragmentPageAdapter = new FragmentPage(getSupportFragmentManager(),
                FragmentPage.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentPageAdapter);
        bt.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.thongbao:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.thongtin:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.caidat:
                        viewPager.setCurrentItem(2);
                        break;



                }
                return true;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bt.getMenu().findItem(R.id.thongbao).setChecked(true);
                        break;
                    case 1:
                        bt.getMenu().findItem(R.id.thongtin).setChecked(true);
                        break;
                    case 2:
                        bt.getMenu().findItem(R.id.caidat).setChecked(true);
                        break;

                    default:
                        bt.getMenu().findItem(R.id.thongbao).setChecked(true);


                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }}
