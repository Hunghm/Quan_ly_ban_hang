package com.example.quan_ly_ban_hang.Model;

public class Top {
    public Integer maSanPham;
    public Integer soLuong;
    public Integer soHoaDon;

    public Top(Integer maSanPham, Integer soLuong, Integer soHoaDon) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.soHoaDon = soHoaDon;
    }

    public Top() {
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(Integer soHoaDon) {
        this.soHoaDon = soHoaDon;
    }
}
