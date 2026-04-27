package com.example.absensi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView tvCurrentTime;
    private Handler handler;
    private Runnable timeUpdater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvCurrentTime = view.findViewById(R.id.tvCurrentTime);
        Button btnMasuk = view.findViewById(R.id.btnMasuk);
        Button btnKeluar = view.findViewById(R.id.btnKeluar);

        // Update Jam Realtime persis seperti updateClock() di JS
        handler = new Handler(Looper.getMainLooper());
        timeUpdater = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm 'WIB'", new Locale("id", "ID"));
                if (tvCurrentTime != null) {
                    tvCurrentTime.setText(sdf.format(new Date()));
                }
                handler.postDelayed(this, 1000); // Jalan tiap 1 detik
            }
        };
        handler.post(timeUpdater);

        // Aksi Klik Tombol
        btnMasuk.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Tombol Masuk Berhasil Ditekan!", Toast.LENGTH_SHORT).show();
        });

        btnKeluar.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Tombol Keluar Berhasil Ditekan!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Hentikan jam saat pindah tab biar HP nggak nge-lag / memori bocor
        if (handler != null && timeUpdater != null) {
            handler.removeCallbacks(timeUpdater);
        }
    }
}