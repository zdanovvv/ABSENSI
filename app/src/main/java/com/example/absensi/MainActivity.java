package com.example.absensi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public LinearLayout navHome, navRiwayat, navAbsen, navLokasi, navProfil;
    private ImageView icHome, icRiwayat, icAbsen, icLokasi, icProfil;
    private TextView tvHome, tvRiwayat, tvAbsen, tvLokasi, tvProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupNavigation();

        // Load initial fragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            updateNavUI(0);
        }
    }

    private void initViews() {
        navHome = findViewById(R.id.nav_home);
        navRiwayat = findViewById(R.id.nav_riwayat);
        navAbsen = findViewById(R.id.nav_absen);
        navLokasi = findViewById(R.id.nav_lokasi);
        navProfil = findViewById(R.id.nav_profil);

        icHome = findViewById(R.id.ic_nav_home);
        icRiwayat = findViewById(R.id.ic_nav_riwayat);
        icAbsen = findViewById(R.id.ic_nav_absen);
        icLokasi = findViewById(R.id.ic_nav_lokasi);
        icProfil = findViewById(R.id.ic_nav_profil);

        tvHome = findViewById(R.id.tv_nav_home);
        tvRiwayat = findViewById(R.id.tv_nav_riwayat);
        tvAbsen = findViewById(R.id.nav_absen).findViewById(android.R.id.text1); // Placeholder or direct ID
        // Note: nav_absen has a TextView but no ID in previous write, let's fix that or access manually
        tvAbsen = (TextView) ((LinearLayout)findViewById(R.id.nav_absen)).getChildAt(2);

        tvLokasi = findViewById(R.id.tv_nav_lokasi);
        tvProfil = findViewById(R.id.tv_nav_profil);
    }

    private void setupNavigation() {
        navHome.setOnClickListener(v -> {
            loadFragment(new HomeFragment());
            updateNavUI(0);
        });

        navRiwayat.setOnClickListener(v -> {
            loadFragment(new RiwayatFragment());
            updateNavUI(1);
        });

        navAbsen.setOnClickListener(v -> {
            loadFragment(new AbsenFragment());
            updateNavUI(2);
        });

        navLokasi.setOnClickListener(v -> {
            loadFragment(new LokasiFragment());
            updateNavUI(3);
        });

        navProfil.setOnClickListener(v -> {
            loadFragment(new ProfilFragment());
            updateNavUI(4);
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void updateNavUI(int position) {
        // Reset all
        icHome.setColorFilter(ContextCompat.getColor(this, R.color.gray_400));
        icRiwayat.setColorFilter(ContextCompat.getColor(this, R.color.gray_400));
        icLokasi.setColorFilter(ContextCompat.getColor(this, R.color.gray_400));
        icProfil.setColorFilter(ContextCompat.getColor(this, R.color.gray_400));

        tvHome.setTextColor(ContextCompat.getColor(this, R.color.gray_400));
        tvRiwayat.setTextColor(ContextCompat.getColor(this, R.color.gray_400));
        tvLokasi.setTextColor(ContextCompat.getColor(this, R.color.gray_400));
        tvProfil.setTextColor(ContextCompat.getColor(this, R.color.gray_400));

        tvHome.setTypeface(null, android.graphics.Typeface.NORMAL);
        tvRiwayat.setTypeface(null, android.graphics.Typeface.NORMAL);
        tvLokasi.setTypeface(null, android.graphics.Typeface.NORMAL);
        tvProfil.setTypeface(null, android.graphics.Typeface.NORMAL);

        // Highlight selected
        switch (position) {
            case 0:
                icHome.setColorFilter(ContextCompat.getColor(this, R.color.blue));
                tvHome.setTextColor(ContextCompat.getColor(this, R.color.blue));
                tvHome.setTypeface(null, android.graphics.Typeface.BOLD);
                break;
            case 1:
                icRiwayat.setColorFilter(ContextCompat.getColor(this, R.color.blue));
                tvRiwayat.setTextColor(ContextCompat.getColor(this, R.color.blue));
                tvRiwayat.setTypeface(null, android.graphics.Typeface.BOLD);
                break;
            case 2:
                // Absen is special
                break;
            case 3:
                icLokasi.setColorFilter(ContextCompat.getColor(this, R.color.blue));
                tvLokasi.setTextColor(ContextCompat.getColor(this, R.color.blue));
                tvLokasi.setTypeface(null, android.graphics.Typeface.BOLD);
                break;
            case 4:
                icProfil.setColorFilter(ContextCompat.getColor(this, R.color.blue));
                tvProfil.setTextColor(ContextCompat.getColor(this, R.color.blue));
                tvProfil.setTypeface(null, android.graphics.Typeface.BOLD);
                break;
        }
    }
}