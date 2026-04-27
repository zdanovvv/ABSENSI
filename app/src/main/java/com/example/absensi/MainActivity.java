package com.example.absensi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fab = findViewById(R.id.fab_absen);

        // Halaman awal
        loadFragment(new HomeFragment());

        nav.setOnItemSelectedListener(item -> {
            Fragment f = null;
            int id = item.getItemId();
            if (id == R.id.nav_home) f = new HomeFragment();
            else if (id == R.id.nav_history) f = new RiwayatFragment();
            else if (id == R.id.nav_location) f = new LokasiFragment();
            else if (id == R.id.nav_profile) f = new ProfilFragment();

            if (f != null) loadFragment(f);
            return true;
        });

        // Klik Tombol Scan/Absen Tengah
        fab.setOnClickListener(v -> {
            // Nanti lu arahin ke ScanActivity
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Animasi perpindahan (Fade In & Out)
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}