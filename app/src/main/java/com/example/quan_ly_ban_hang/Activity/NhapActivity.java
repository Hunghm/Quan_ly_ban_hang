package com.example.quan_ly_ban_hang.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class NhapActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText edMaSanPham,edSoSanPham,edThanhTien;
    private Spinner spinnerSanPham;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);

        btnAdd = (Button) findViewById(R.id.btn_add);
        edMaSanPham = (EditText) findViewById(R.id.ed_ma_hd);
        edSoSanPham = (EditText) findViewById(R.id.ed_so_san_pham);
        edThanhTien = (EditText) findViewById(R.id.ed_thanh_tien);
        spinnerSanPham = (Spinner) findViewById(R.id.spinner_san_pham);

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
//        hoaDonChiTiet.setMaSanPham(listSanPham.get(0).getMaSanPham());
//        hoaDonChiTiet.setThanhTien();
//        hoaDonChiTiet.setSoLuong();
//        hoaDonChiTiet.setHanLuuTru();
//        HoaDonChiTietDAO dao = new HoaDonChiTietDAO(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        final Bundle bundle = new Bundle();
        final Intent intent = new Intent(this, MainActivity.class);

        bundle.putInt("tab",0);
        bundle.putInt("SWITCH_TAB", R.id.item_2); // Both constants are defined in your code
        intent.putExtra("SWITCH_TAB",bundle);

        return intent;
    }
}