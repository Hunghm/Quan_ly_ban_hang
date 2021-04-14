package com.example.quan_ly_ban_hang.Model;

import java.util.Date;

public class HoaDonChiTiet {
    private Integer MaHoaDonChiTiet;
    private Integer MaSanPham;
    private Integer MaHoaDon;
    private int SoLuong;
    private Date HanLuuTru;
    private int LoaiHoaDon;

    public HoaDonChiTiet(Integer maHoaDonChiTiet, Integer maSanPham, Integer maHoaDon, int soLuong, Date hanLuuTru, int loaiHoaDon) {
        MaHoaDonChiTiet = maHoaDonChiTiet;
        MaSanPham = maSanPham;
        MaHoaDon = maHoaDon;
        SoLuong = soLuong;
        HanLuuTru = hanLuuTru;
        LoaiHoaDon = loaiHoaDon;
    }

    public HoaDonChiTiet() {
    }

    public int getLoaiHoaDon() {
        return LoaiHoaDon;
    }

    public void setLoaiHoaDon(int loaiHoaDon) {
        LoaiHoaDon = loaiHoaDon;
    }

    public Integer getMaHoaDonChiTiet() {
        return MaHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(Integer maHoaDonChiTiet) {
        MaHoaDonChiTiet = maHoaDonChiTiet;
    }

    public Integer getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        MaSanPham = maSanPham;
    }

    public Integer getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        MaHoaDon = maHoaDon;
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
