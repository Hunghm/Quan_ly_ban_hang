package com.example.quan_ly_ban_hang.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.quan_ly_ban_hang.DAO.UserDAO;
import com.example.quan_ly_ban_hang.Model.User;
import com.example.quan_ly_ban_hang.R;

import java.util.List;

public class ThemTaiKhoan extends AppCompatActivity {

    EditText edPass, edHoTen, edNhapLai, edUser;
    Button btnLuu, btnHuy;
    UserDAO thuThuDAO;
    String userName;
    User thuThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tk);

        edUser = (EditText) findViewById(R.id.ed_user);
        edPass = (EditText) findViewById(R.id.ed_pass);
        edHoTen = (EditText) findViewById(R.id.ed_ten_dang_nhap);
        edNhapLai = (EditText) findViewById(R.id.ed_pass_nhap_lai);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        btnLuu = (Button) findViewById(R.id.btn_luu);

        thuThuDAO = new UserDAO(this);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapLai.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edUser.setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuThu = new User();
                thuThu.setHoTen(edHoTen.getText().toString());
                thuThu.setPassword(edPass.getText().toString());
                thuThu.setUser(edUser.getText().toString());
                if (validate(thuThu)){
                    long resultInsert = (int) thuThuDAO.insert(thuThu);
                    if (resultInsert >0 ){
                        Toast.makeText(ThemTaiKhoan.this, "Thay đổi mật khẩu thành công ", Toast.LENGTH_SHORT).show();
                        edNhapLai.setText("");
                        edHoTen.setText("");
                        edPass.setText("");
                        edUser.setText("");
                    }else {
                        Toast.makeText(ThemTaiKhoan.this, "thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean validate(User thuThu) {
        String passNhapLai = edNhapLai.getText().toString();
        boolean check = true;
        if (thuThu.getHoTen().isEmpty()||thuThu.getUser().isEmpty()||thuThu.getPassword().isEmpty()||passNhapLai.isEmpty()){
            Toast.makeText(ThemTaiKhoan.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = false;
        }else {
            if (!thuThu.getPassword().equals(passNhapLai)){
                Toast.makeText(ThemTaiKhoan.this, "Mật khẩu nhập lại không trùng", Toast.LENGTH_SHORT).show();
                check = false;
            }
            List<User> listThuThuTrongData = thuThuDAO.checkTonTaiTK(thuThu.getUser());
            if (listThuThuTrongData.size()==0){

            }else {
                if (listThuThuTrongData.get(0).getUser().equals(thuThu.getUser())){
                    check = false;
                    Toast.makeText(ThemTaiKhoan.this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return check;
    }

}