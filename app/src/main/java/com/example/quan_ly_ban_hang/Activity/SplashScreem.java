package com.example.quan_ly_ban_hang.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_ban_hang.R;
import com.example.quan_ly_ban_hang.login.login;

public class SplashScreem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screem);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new
                        Intent(SplashScreem.this, login.class));
                finish();
            }
        }, 2000);
    }
}