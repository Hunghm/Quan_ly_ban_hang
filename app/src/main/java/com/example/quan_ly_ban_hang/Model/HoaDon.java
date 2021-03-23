package com.example.quan_ly_ban_hang.Model;

import java.util.Date;

public class HoaDon {
    private String MaHoaDon;
    private Date NgayNhapXuat;
    private int LoaiHoaDon;

    public HoaDon(String maHoaDon, Date ngayNhapXuat, int loaiHoaDon) {
        MaHoaDon = maHoaDon;
        NgayNhapXuat = ngayNhapXuat;
        LoaiHoaDon = loaiHoaDon;
    }

    public HoaDon() {
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public Date getNgayNhapXuat() {
        return NgayNhapXuat;
    }

    public void setNgayNhapXuat(Date ngayNhapXuat) {
        NgayNhapXuat = ngayNhapXuat;
    }

    public int getLoaiHoaDon() {
        return LoaiHoaDon;
    }

    public void setLoaiHoaDon(int loaiHoaDon) {
        LoaiHoaDon = loaiHoaDon;
    }
}
