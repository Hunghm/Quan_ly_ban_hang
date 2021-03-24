package com.example.quan_ly_ban_hang.Model;

import java.sql.Date;

public class HoaDonChiTiet {
    private String MaHoaDonChiTiet;
    private String MaSanPham;
    private String MaHoaDon;
    private Double ThanhTien;
    private int SoLuong;
    private Date HanLuuTru;

    public HoaDonChiTiet(String maHoaDonChiTiet, String maSanPham, String maHoaDon, Double thanhTien, int soLuong, Date hanLuuTru) {
        MaHoaDonChiTiet = maHoaDonChiTiet;
        MaSanPham = maSanPham;
        MaHoaDon = maHoaDon;
        ThanhTien = thanhTien;
        SoLuong = soLuong;
        HanLuuTru = hanLuuTru;
    }

    public HoaDonChiTiet() {
    }

    public String getMaHoaDonChiTiet() {
        return MaHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
        MaHoaDonChiTiet = maHoaDonChiTiet;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public Double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        ThanhTien = thanhTien;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public Date getHanLuuTru() {
        return HanLuuTru;
    }

    public void setHanLuuTru(Date hanLuuTru) {
        HanLuuTru = hanLuuTru;
    }
}
