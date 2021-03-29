package com.example.quan_ly_ban_hang.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quan_ly_ban_hang.R;

public class XuatActivity extends AppCompatActivity {
      EditText edMaHDXuat, edSoSPXuat,edThanhTienXuat;
      Spinner spnSanPhamXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat);
        edMaHDXuat = findViewById(R.id.ed_ma_hd_xuat);
        edSoSPXuat = findViewById(R.id.ed_so_san_pham_xuat);
        edThanhTienXuat = findViewById(R.id.ed_thanh_tien_xuat);
        spnSanPhamXuat = findViewById(R.id.spinner_san_pham_xuat);
    }
    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        final Bundle bundle = new Bundle();
        final Intent intent = new Intent(this, MainActivity.class);

        bundle.putInt("tab",1);
        bundle.putInt("SWITCH_TAB", R.id.item_2); // Both constants are defined in your code
        intent.putExtra("SWITCH_TAB",bundle);

        return intent;
    }
}