package com.example.quan_ly_ban_hang.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.Activity.MainActivity;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    Button btnDangNhap,btnDangXuat;
    TextInputEditText edtUser, edtPass;
    boolean isNameValid, isPasswordValid;
    TextInputLayout userError, passError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDangNhap = (Button) findViewById(R.id.btn_dang_nhap);
        btnDangXuat= (Button) findViewById(R.id.btn_dang_xuat);
        edtUser = (TextInputEditText) findViewById(R.id.ed_user_name);
        edtPass = (TextInputEditText) findViewById(R.id.ed_password);
        userError = (TextInputLayout) findViewById(R.id.addUserError);
        passError = (TextInputLayout) findViewById(R.id.addPassError);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "Just Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void SetValidation() {
        //check for a valid username address
        if (edtUser.getText().toString().isEmpty()) {
            userError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            userError.setErrorEnabled(false);
        }
        // Check for a valid password.
        if (edtPass.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (edtPass.getText().length() < 8) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }
        if (isNameValid && isPasswordValid) {
            startActivity(new Intent(login.this, MainActivity.class));
        }
    }
}