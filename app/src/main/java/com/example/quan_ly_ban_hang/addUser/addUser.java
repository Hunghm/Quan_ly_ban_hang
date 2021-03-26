package com.example.quan_ly_ban_hang.addUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quan_ly_ban_hang.R;
import com.example.quan_ly_ban_hang.login.login;

public class addUser extends AppCompatActivity {
    Button btnLogin, btnAddUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        btnAddUser= (Button) findViewById(R.id.btn_add_user);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addUser.this, login.class);
                startActivity(intent);
            }
        });
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addUser.this, "Oke", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
