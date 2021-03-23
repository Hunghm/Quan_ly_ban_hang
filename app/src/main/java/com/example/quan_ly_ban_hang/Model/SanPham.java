package com.example.quan_ly_ban_hang.Model;

public class SanPham {
    private String MaSanPham;
    private String TenSanPham;
    private Double GiaNhap;
    private Double GiaXuat;
    private String GhiChu;
    private int Anh;

    public SanPham(String maSanPham, String tenSanPham, Double giaNhap, Double giaXuat, String ghiChu, int anh) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        GiaNhap = giaNhap;
        GiaXuat = giaXuat;
        GhiChu = ghiChu;
        Anh = anh;
    }

    public SanPham() {
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        GiaNhap = giaNhap;
    }

    public Double getGiaXuat() {
        return GiaXuat;
    }

    public void setGiaXuat(Double giaXuat) {
        GiaXuat = giaXuat;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getAnh() {
        return Anh;
    }

    public void setAnh(int anh) {
        Anh = anh;
    }
}
