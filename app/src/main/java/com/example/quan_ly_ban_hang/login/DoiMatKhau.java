package com.example.quan_ly_ban_hang.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.DAO.UserDAO;
import com.example.quan_ly_ban_hang.Model.User;
import com.example.quan_ly_ban_hang.R;

public class DoiMatKhau extends AppCompatActivity {

    EditText edPassCu, edpassMoi, edNhapLai;
    Button btnLuu, btnHuy;
    UserDAO thuThuDAO;
    String userName;
    User thuThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edPassCu = (EditText) findViewById(R.id.ed_pass_cu);
        edpassMoi = (EditText) findViewById(R.id.ed_pass_moi);
        edNhapLai = (EditText) findViewById(R.id.ed_pass_nhap_lai);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        btnLuu = (Button) findViewById(R.id.btn_luu);

        thuThuDAO = new UserDAO(this);
        Intent intent = getIntent();
        userName = intent.getStringExtra("user");
        thuThu = thuThuDAO.getID(userName);
        Toast.makeText(this, userName, Toast.LENGTH_SHORT).show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapLai.setText("");
                edpassMoi.setText("");
                edPassCu.setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    thuThu.setPassword(edpassMoi.getText().toString());
                    int resultUpdate = thuThuDAO.update(thuThu);
                    if (resultUpdate >0 ){
                        Toast.makeText(DoiMatKhau.this, "Thay đổi mật khẩu thành công ", Toast.LENGTH_SHORT).show();
                        edNhapLai.setText("");
                        edpassMoi.setText("");
                        edPassCu.setText("");
                    }else {
                        Toast.makeText(DoiMatKhau.this, "thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    private boolean validate() {
        String passCu = edPassCu.getText().toString();
        String passMoi = edpassMoi.getText().toString();
        String passNhapLai = edNhapLai.getText().toString();
        boolean check = true;
        if (passCu.isEmpty()||passMoi.isEmpty()||passNhapLai.isEmpty()){
            Toast.makeText(DoiMatKhau.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = false;
        }else {
            if (!thuThu.getPassword().equals(passCu)){
                Toast.makeText(DoiMatKhau.this, "Mật khẩu sai", Toast.LENGTH_SHORT).show();
                check = false;
            }
            if (!passMoi.equals(passNhapLai)){
                Toast.makeText(DoiMatKhau.this, "Nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = false;
            }
        }
        return check;
    }
}