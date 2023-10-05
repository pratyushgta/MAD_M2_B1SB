package com.example.mad_m2_b1sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mad_m2_b1sb.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                if(item.getItemId() == R.id.nav_home){
                    selected = new HomeFragment();
                } else if(item.getItemId() == R.id.nav_cam){
                    selected = new CameraFragment();
                }
                if(selected!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_framelayout,selected).commit();
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelayout,new HomeFragment()).commit();
    }
}