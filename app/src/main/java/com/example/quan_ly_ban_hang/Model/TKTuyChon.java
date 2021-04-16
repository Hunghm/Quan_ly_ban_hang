package com.example.quan_ly_ban_hang.Model;

public class TKTuyChon {
    private int tongChi;
    private int tongThu;
    private int lai;

    public TKTuyChon(int tongChi, int tongThu, int lai) {
        this.tongChi = tongChi;
        this.tongThu = tongThu;
        this.lai = lai;
    }

    public TKTuyChon() {
    }

    public int getTongChi() {
        return tongChi;
    }

    public void setTongChi(int tongChi) {
        this.tongChi = tongChi;
    }

    public int getTongThu() {
        return tongThu;
    }

    public void setTongThu(int tongThu) {
        this.tongThu = tongThu;
    }

    public int getLai() {
        return lai;
    }

    public void setLai(int lai) {
        this.lai = lai;
    }
}

