package com.example.absensi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AbsenFragment extends Fragment {

    private TextView tvAbsenTime;
    private View scanLine;
    private Handler handler;
    private Runnable timeUpdater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_absen, container, false);

        tvAbsenTime = view.findViewById(R.id.tv_absen_time);
        scanLine = view.findViewById(R.id.scan_line);
        Button btnSubmit = view.findViewById(R.id.btn_absen_submit);
        View btnBack = view.findViewById(R.id.btn_back);

        // Time updater
        handler = new Handler(Looper.getMainLooper());
        timeUpdater = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm 'WIB'", Locale.getDefault());
                if (tvAbsenTime != null) tvAbsenTime.setText(sdf.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(timeUpdater);

        // Simple Scan Animation
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 500);
        animation.setDuration(2000);
        animation.setRepeatCount(TranslateAnimation.INFINITE);
        animation.setRepeatMode(TranslateAnimation.REVERSE);
        scanLine.startAnimation(animation);

        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "✓ Presensi masuk berhasil dicatat!", Toast.LENGTH_SHORT).show();
        });

        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity)getActivity()).navHome.performClick();
            }
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