package com.example.quan_ly_ban_hang.Model;

public class ThongKe {

    private Integer soLuongHoaDon;
    private Integer Month;
    private Double tongTien;

    public ThongKe(Integer soLuongHoaDon, Integer month, Double tongTien) {
        this.soLuongHoaDon = soLuongHoaDon;
        Month = month;
        this.tongTien = tongTien;
    }

    public ThongKe() {
    }

    public Integer getSoLuongHoaDon() {
        return soLuongHoaDon;
    }

    public void setSoLuongHoaDon(Integer soLuongHoaDon) {
        this.soLuongHoaDon = soLuongHoaDon;
    }

    public Integer getMonth() {
        return Month;
    }

    public void setMonth(Integer month) {
        Month = month;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
}
