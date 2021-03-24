package com.example.quan_ly_ban_hang.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quan_ly_ban_hang.Activity.MainActivity;
import com.example.quan_ly_ban_hang.R;

public class login extends AppCompatActivity {

    Button btnDangNhap,btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDangNhap = (Button) findViewById(R.id.btn_dang_nhap);
        btnDangXuat= (Button) findViewById(R.id.btn_dang_xuat);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, MainActivity.class));
            }
        });
    }
}