package com.example.boilerplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.boilerplate.Fragment.HomeFragment;
import com.example.boilerplate.Fragment.LeaderBoardFragment;
import com.example.boilerplate.Fragment.ProfileFragment;
import com.example.boilerplate.Fragment.SettingFragment;
import com.example.boilerplate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnAddWallpaper;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        btnAddWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddWallPaperActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        btnAddWallpaper = findViewById(R.id.btnAddWallpaper);
    }

    //bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.btnHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.bntLeaderBoard:
                    selectedFragment = new LeaderBoardFragment();
                    break;
                case R.id.btnSetting :
                    selectedFragment = new SettingFragment();
                    break;
                case R.id.btnProfile :
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}
