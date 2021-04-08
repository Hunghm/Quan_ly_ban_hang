package com.example.quan_ly_ban_hang.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.Adapter.adapter_spinner_san_pham;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.DAO.HoaDonDAO;
import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NhapActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText edMaSanPham,edSoSanPham,edHanLuuTru;
    private Spinner spinnerSanPham;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    private HoaDonDAO hoaDonDAO;
    private adapter_spinner_san_pham adapter_spinner ;
    private SanPham sanPhamSelectedSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap);

        btnAdd = (Button) findViewById(R.id.btn_add);
        edMaSanPham = (EditText) findViewById(R.id.ed_ma_hd);
        edSoSanPham = (EditText) findViewById(R.id.ed_so_san_pham);
        edHanLuuTru = (EditText) findViewById(R.id.ed_han_luu_tru);
        spinnerSanPham = (Spinner) findViewById(R.id.spinner_san_pham);

        listSanPham.add(new SanPham(1,"abc",5.0,5.0,"abc", R.drawable.book, 10, 0));

        adapter_spinner = new adapter_spinner_san_pham(this, listSanPham);
        spinnerSanPham.setAdapter(adapter_spinner);
        spinnerSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sanPhamSelectedSpinner = (SanPham) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        hoaDonDAO = new HoaDonDAO(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setLoaiHoaDon(1);
                hoaDon.setNgayNhapXuat(Calendar.getInstance().getTime());
                long resultHoaDon = hoaDonDAO.insert(hoaDon);
                HoaDon hoaDon1 = hoaDonDAO.getHoaDonNew();

                Calendar c = Calendar.getInstance();
                c.setTime(Calendar.getInstance().getTime());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(edHanLuuTru.getText().toString()));
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setHanLuuTru(c.getTime());
                hoaDonChiTiet.setMaSanPham(sanPhamSelectedSpinner.getMaSanPham());
                hoaDonChiTiet.setMaHoaDon(hoaDon1.getMaHoaDon());
                hoaDonChiTiet.setSoLuong(Integer.parseInt(edSoSanPham.getText().toString()));

                long resultHoaDonChiTiet =  hoaDonChiTietDAO.insert(hoaDonChiTiet);

                Toast.makeText(NhapActivity.this, String.valueOf(resultHoaDonChiTiet), Toast.LENGTH_SHORT).show();

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