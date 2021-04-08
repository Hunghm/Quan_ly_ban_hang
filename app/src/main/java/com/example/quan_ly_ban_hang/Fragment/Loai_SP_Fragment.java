package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
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

import com.example.quan_ly_ban_hang.Activity.XuatActivity;
import com.example.quan_ly_ban_hang.DAO.LoaiSanPhamDAO;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Loai_SP_Fragment extends Fragment {

    FloatingActionButton btnAdd;
    LoaiSanPhamDAO loaiSanPhamDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loai_sp_fragment,container,false);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.loai_san_pham_add,null);
                EditText edTenSanPham = (EditText) layout.findViewById(R.id.ed_ten_san_pham);
                Button btnAdd = (Button) layout.findViewById(R.id.btn_add);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoaiSanPham loaiSanPham = new LoaiSanPham();
                        loaiSanPham.setTenLoai(edTenSanPham.getText().toString());
                        long result = loaiSanPhamDAO.insert(loaiSanPham);
                        Toast.makeText(getContext(), String.valueOf(result), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });
        return view;
    }
}
