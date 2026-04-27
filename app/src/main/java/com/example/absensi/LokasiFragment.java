package com.example.absensi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LokasiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lokasi, container, false);

        View btnRefresh = view.findViewById(R.id.btn_refresh);
        Button btnLanjut = view.findViewById(R.id.btn_lanjut_absen);

        btnRefresh.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Lokasi diperbarui ✓", Toast.LENGTH_SHORT).show();
        });

        btnLanjut.setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity)getActivity()).navAbsen.performClick();
            }
        });

        return view;
    }
}