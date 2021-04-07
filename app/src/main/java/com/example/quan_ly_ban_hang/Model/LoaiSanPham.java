package com.example.quan_ly_ban_hang.Model;

import java.util.Date;

public class LoaiSanPham {
    private Integer MaLoai;
    private String TenLoai;

    public LoaiSanPham(Integer maLoai, String tenLoai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }

    public LoaiSanPham() {
    }

    public Integer getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Integer maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
