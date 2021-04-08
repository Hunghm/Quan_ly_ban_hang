package com.example.quan_ly_ban_hang.Model;

public class SanPham {
    private Integer MaSanPham;
    private String TenSanPham;
    private Double GiaNhap;
    private Double GiaXuat;
    private String GhiChu;
    private int Anh;
    private Integer SoLuong;
    private Integer MaLoaiSanPham;

    public SanPham(Integer maSanPham, String tenSanPham, Double giaNhap, Double giaXuat, String ghiChu, int anh, Integer soLuong, Integer maLoaiSanPham) {
        MaSanPham = maSanPham;
        TenSanPham = tenSanPham;
        GiaNhap = giaNhap;
        GiaXuat = giaXuat;
        GhiChu = ghiChu;
        Anh = anh;
        SoLuong = soLuong;
        MaLoaiSanPham = maLoaiSanPham;
    }

    public SanPham() {
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Integer getMaLoaiSanPham() {
        return MaLoaiSanPham;
    }

    public void setMaLoaiSanPham(Integer maLoaiSanPham) {
        MaLoaiSanPham = maLoaiSanPham;
    }

    public Integer getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
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
