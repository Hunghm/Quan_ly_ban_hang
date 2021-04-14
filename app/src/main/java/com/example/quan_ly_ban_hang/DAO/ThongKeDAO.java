package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.Model.ThongKe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ThongKeDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public List<ThongKe> getThongKe() {
        String sql = "SELECT count(HoaDon.maHoaDon) as so_hoa_don ,strftime('%m',HoaDon.ngayNhapXuat) as Month, sum(HoaDonChiTiet.soLuong*SanPham.giaXuat) as thanh_tien  " +
                "FROM(( HoaDonChiTiet INNER JOIN HoaDon on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon)INNER JOIN SanPham on SanPham.maSanPham = HoaDonChiTiet.maSanPham) " +
                "WHERE HoaDon.loaiHoaDon = 2 GROUP by Month";
        List<ThongKe> list = getData(sql);
        return list;
    }

    private List<ThongKe> getData(String sql, String... selectionArgs) {
        List<ThongKe> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ThongKe obj = new ThongKe();
            obj.setSoLuongHoaDon(c.getInt(c.getColumnIndex(Name.so_hoa_don)));
            obj.setMonth(c.getInt(c.getColumnIndex(Name.Month)));
            obj.setTongTien(c.getDouble(c.getColumnIndex(Name.thanh_tien)));
            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String so_hoa_don = "so_hoa_don";
        public static String Month = "Month";
        public static String thanh_tien = "thanh_tien";
    }

}
