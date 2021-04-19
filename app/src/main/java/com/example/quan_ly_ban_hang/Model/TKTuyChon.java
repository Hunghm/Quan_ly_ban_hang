package com.example.quan_ly_ban_hang.Model;

public class TKTuyChon {
    private double tongChi;
    private double tongThu;
//    private double lai;

    public TKTuyChon(double tongChi, double tongThu) {
        this.tongChi = tongChi;
        this.tongThu = tongThu;
//        this.lai = lai;
    }

    public TKTuyChon() {
    }

    public double getTongChi() {
        return tongChi;
    }

    public void setTongChi(double tongChi) {
        this.tongChi = tongChi;
    }

    public double getTongThu() {
        return tongThu;
    }

    public void setTongThu(double tongThu) {
        this.tongThu = tongThu;
    }

//    public double getLai() {
//        return lai;
//    }
//
//    public void setLai(double lai) {
//        this.lai = lai;
//    }
}

