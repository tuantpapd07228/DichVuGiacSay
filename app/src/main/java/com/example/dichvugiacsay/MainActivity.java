package com.example.dichvugiacsay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.example.dichvugiacsay.Adapter.ViewPagerNavigationBottomAdapter;
import com.example.dichvugiacsay.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 view_pager2;
    private BottomNavigationView bottom_navigation;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        view_pager2 = findViewById(R.id.view_pager2);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        ViewPagerNavigationBottomAdapter adapter = new ViewPagerNavigationBottomAdapter(this);
        view_pager2.setAdapter(adapter);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.trang_chu) {
                    view_pager2.setCurrentItem(0);
                } else if (id == R.id.yeu_thich) {
                    view_pager2.setCurrentItem(1);
                } else if (id == R.id.lich_su) {
                    view_pager2.setCurrentItem(2);
                } else {
                    view_pager2.setCurrentItem(3);
                }
                return true;
            }
        });

        view_pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottom_navigation.getMenu().findItem(R.id.trang_chu).setChecked(true);
                        break;
                    case 1:
                        bottom_navigation.getMenu().findItem(R.id.yeu_thich).setChecked(true);
                        break;
                    case 2:
                        bottom_navigation.getMenu().findItem(R.id.lich_su).setChecked(true);
                        break;
                    case 3:
                        bottom_navigation.getMenu().findItem(R.id.tai_khoan).setChecked(true);
                        break;
                }
            }
        });

    }
    public User getUser(){
        return user;
    }



}