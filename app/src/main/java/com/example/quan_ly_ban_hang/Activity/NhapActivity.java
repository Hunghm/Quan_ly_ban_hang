package com.example.quan_ly_ban_hang.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.Adapter.adapter_spinner_san_pham;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NhapActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText edMaSanPham,edSoSanPham,edThanhTien;
    private Spinner spinnerSanPham;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private HoaDonChiTietDAO dao;
    private adapter_spinner_san_pham adapter_spinner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);

        btnAdd = (Button) findViewById(R.id.btn_add);
        edMaSanPham = (EditText) findViewById(R.id.ed_ma_hd);
        edSoSanPham = (EditText) findViewById(R.id.ed_so_san_pham);
        edThanhTien = (EditText) findViewById(R.id.ed_thanh_tien);
        spinnerSanPham = (Spinner) findViewById(R.id.spinner_san_pham);

        listSanPham.add(new SanPham("abc","abc",5.0,5.0,"abc", R.drawable.book));

        adapter_spinner = new adapter_spinner_san_pham(this, listSanPham);
        spinnerSanPham.setAdapter(adapter_spinner);

        dao = new HoaDonChiTietDAO(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setHanLuuTru(Calendar.getInstance().getTime());
                hoaDonChiTiet.setMaSanPham(edMaSanPham.getText().toString());
                hoaDonChiTiet.setMaHoaDon("3");
                hoaDonChiTiet.setSoLuong(10);

                long sanPhams =  dao.insert(hoaDonChiTiet);

//                List<HoaDonChiTiet> sanPhams = dao.getAll();
                Toast.makeText(NhapActivity.this, String.valueOf(sanPhams), Toast.LENGTH_SHORT).show();
//                finish();
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