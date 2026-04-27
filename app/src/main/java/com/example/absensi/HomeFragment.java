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

    private TextView tvHomeTime;
    private Handler handler;
    private Runnable timeUpdater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvHomeTime = view.findViewById(R.id.tv_home_time);
        Button btnMasuk = view.findViewById(R.id.btn_masuk);
        Button btnKeluar = view.findViewById(R.id.btn_keluar);
        Button btnIstirahat = view.findViewById(R.id.btn_istirahat);

        // Update Jam Realtime
        handler = new Handler(Looper.getMainLooper());
        timeUpdater = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                if (tvHomeTime != null) {
                    tvHomeTime.setText(sdf.format(new Date()));
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(timeUpdater);

        btnMasuk.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Silakan buka tab Absen untuk Check-in", Toast.LENGTH_SHORT).show();
        });

        btnKeluar.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Check-out berhasil!", Toast.LENGTH_SHORT).show();
        });

        btnIstirahat.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Mode istirahat aktif ☕", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null && timeUpdater != null) {
            handler.removeCallbacks(timeUpdater);
        }
    }
}