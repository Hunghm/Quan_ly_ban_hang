package com.example.quan_ly_ban_hang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.Adapter.AdapterSpinnerLoaiSP;
import com.example.quan_ly_ban_hang.DAO.LoaiSanPhamDAO;
import com.example.quan_ly_ban_hang.DAO.SanPhamDAO;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;

public class SuaSanPhamActivity extends AppCompatActivity {
    EditText edMaSP, edTenSpEdit,edGiaNhapEdit, edGiaXuatEdit, edtGhiChu;
    Spinner loaiSp;
    Button btnSua;
    LoaiSanPham loaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        edMaSP = findViewById(R.id.ed_ma_sp_edit);
        edTenSpEdit = findViewById(R.id.ed_ten_sp_edit);
        loaiSp = findViewById(R.id.spn_loai_sp_edit);
        edtGhiChu = findViewById(R.id.ed_ghi_chu_edit);
        edGiaNhapEdit = findViewById(R.id.ed_gia_nhap_edit);
        edGiaXuatEdit = findViewById(R.id.ed_gia_xuat_edit);
        btnSua = (Button) findViewById(R.id.btn_edit);

        Intent intent = getIntent();
        Integer idsp= intent.getIntExtra("idsp", -1);
        SanPhamDAO sanPhamDAO = new SanPhamDAO(this);
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        ArrayList<LoaiSanPham> listLoaiSp = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();

        AdapterSpinnerLoaiSP adapter_spinner = new AdapterSpinnerLoaiSP(this,listLoaiSp);
        loaiSp.setAdapter(adapter_spinner);
        loaiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaiSanPham = (LoaiSanPham) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SanPham sanPham = sanPhamDAO.getID(String.valueOf(idsp));
        edMaSP.setText(String.valueOf(sanPham.getMaSanPham()));
        edTenSpEdit.setText(sanPham.getTenSanPham());
        edtGhiChu.setText(sanPham.getGhiChu());
        edGiaNhapEdit.setText(String.valueOf(sanPham.getGiaNhap()));
        edGiaXuatEdit.setText(String.valueOf(sanPham.getGiaXuat()));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham.setTenSanPham(edTenSpEdit.getText().toString());
                sanPham.setGhiChu(edtGhiChu.getText().toString());
                sanPham.setGiaNhap(Double.parseDouble(edGiaNhapEdit.getText().toString()));
                sanPham.setGiaXuat(Double.parseDouble(edGiaXuatEdit.getText().toString()));
                sanPham.setMaLoaiSanPham(loaiSanPham.getMaLoai());
                int result  = sanPhamDAO.update(sanPham);
                if (result > 0){
                    Toast.makeText(SuaSanPhamActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}