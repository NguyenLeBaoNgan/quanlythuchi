package com.example.qlthuchi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.qlthuchi.R;
import com.example.qlthuchi.adapter.ViewPager2Adapter;
import com.example.qlthuchi.dao.GiaoDichDAO;
import com.example.qlthuchi.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private GiaoDichDAO giaoDichDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giaoDichDAO = new GiaoDichDAO(this);

        ViewPager2 viewPager2= findViewById(R.id.viewPager2);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        ViewPager2Adapter adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);


        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_home) {
                    viewPager2.setCurrentItem(0);
                } else if (itemId == R.id.menu_history) {
                    viewPager2.setCurrentItem(1);
                } else if (itemId == R.id.menu_notification) {
                    viewPager2.setCurrentItem(2);
                } else {
                    viewPager2.setCurrentItem(0);
                }
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomNav.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.menu_history).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.menu_notification).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });
    }
}