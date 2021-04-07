package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.Activity.NhapActivity;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

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
//                startActivity(new Intent(getContext(), NhapActivity.class));
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.activity_nhap,null);
                EditText edNhap = (EditText) layout.findViewById(R.id.ed_ma_hd);
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

    public void them(){
        Toast.makeText(getContext(), "bam vao add", Toast.LENGTH_SHORT).show();
    }

}
