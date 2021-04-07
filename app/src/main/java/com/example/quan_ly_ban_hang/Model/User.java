package com.example.quan_ly_ban_hang.Model;

import java.util.Date;

public class User {
    private String user;
    private String password;
    private Integer Loai;
    private String HoTen;

    public User(String user, String password, Integer loai, String hoTen) {
        this.user = user;
        this.password = password;
        Loai = loai;
        HoTen = hoTen;
    }

    public User() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoai() {
        return Loai;
    }

    public void setLoai(Integer loai) {
        Loai = loai;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }
}
