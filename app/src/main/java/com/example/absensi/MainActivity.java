package com.example.absensi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);

        // Halaman awal
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment()).commit();

        nav.setOnItemSelectedListener(item -> {
            Fragment f = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) f = new HomeFragment();
            else if (id == R.id.nav_history) f = new Fragment(); // Ganti ntar
            else if (id == R.id.nav_location) f = new Fragment();
            else if (id == R.id.nav_profile) f = new Fragment();

            if (f != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, f).commit();
            }
            return true;
        });
    }
}