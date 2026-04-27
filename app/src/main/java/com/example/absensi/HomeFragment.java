package com.example.absensi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnMasuk = view.findViewById(R.id.btnMasuk);

        // Logika kalau tombol masuk diklik
        btnMasuk.setOnClickListener(v -> {
            // Nanti lu buat Activity baru namanya ScanActivity buat deteksi wajah
            // Intent intent = new Intent(getActivity(), ScanActivity.class);
            // startActivity(intent);
        });

        return view;
    }
}