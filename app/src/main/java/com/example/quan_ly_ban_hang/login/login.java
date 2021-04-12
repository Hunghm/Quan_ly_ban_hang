package com.example.quan_ly_ban_hang.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.Activity.MainActivity;
import com.example.quan_ly_ban_hang.DAO.UserDAO;
import com.example.quan_ly_ban_hang.Model.User;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class login extends AppCompatActivity {

    EditText edUser,edPassword;
    Button btnLogin, btnTaoTaiKhoan;
    UserDAO userDAO;
    CheckBox checkBox;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDAO = new UserDAO(this);

        edUser = (EditText) findViewById(R.id.ed_user_name);
        edPassword= (EditText) findViewById(R.id.ed_password);
        btnLogin= (Button) findViewById(R.id.btn_login);
        checkBox = (CheckBox) findViewById(R.id.chk_remember_pass);
        btnTaoTaiKhoan = (Button) findViewById(R.id.btn_tao_tai_khoan);

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUser.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        checkBox.setChecked(pref.getBoolean("REMEMBER",false));


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, ThemTaiKhoan.class));
            }
        });

    }

    private void checkLogin() {
        strUser = edUser.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu bị bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            List<User> listuserCheckLogin = userDAO.checkTonTaiTK(strUser);
            if (listuserCheckLogin.size() == 0){
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }else {
                User userCheckLogin = listuserCheckLogin.get(0);
                if (strPass.equals(userCheckLogin.getPassword())){
                    rememberUser(strUser,strPass,checkBox.isChecked());
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("user",strUser);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!checked){
            edit.clear();
        }else {
            edit.putString("USERNAME", strUser);
            edit.putString("PASSWORD", strPass);
            edit.putBoolean("REMEMBER", checked);
        }
        edit.commit();
    }
}