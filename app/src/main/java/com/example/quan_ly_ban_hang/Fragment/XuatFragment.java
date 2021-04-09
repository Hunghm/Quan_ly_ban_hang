package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.Activity.NhapActivity;
import com.example.quan_ly_ban_hang.Activity.XuatActivity;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class XuatFragment extends Fragment {

    FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xuat_fragment,container,false);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), XuatActivity.class));
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.activity_xuat,null);
                Button btn_them = (Button) layout.findViewById(R.id.btn_add);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();
                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        them();
                    }
                });
                alertDialog.show();
            }
        });
        return view;
    }

    private void them() {

    }
}
