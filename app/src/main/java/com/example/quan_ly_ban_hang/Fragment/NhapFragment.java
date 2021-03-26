package com.example.quan_ly_ban_hang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.Activity.NhapActivity;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NhapFragment extends Fragment {

    FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nhap_fragment,container,false);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "ABCD", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), NhapActivity.class));
            }
        });
        return view;
    }
}
