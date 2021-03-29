package com.example.quan_ly_ban_hang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.quan_ly_ban_hang.R;

public class SuaSanPhamActivity extends AppCompatActivity {
    EditText edMaSP, edTenSpEdit,edGiaNhapEdit, edGiaXuatEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        edMaSP = findViewById(R.id.ed_ma_san_pham_edit);
        edTenSpEdit = findViewById(R.id.ed_ten_san_pham_edit);
        edGiaNhapEdit = findViewById(R.id.ed_gia_nhap_edit);
        edGiaXuatEdit = findViewById(R.id.ed_gia_xuat_edit);
    }
}