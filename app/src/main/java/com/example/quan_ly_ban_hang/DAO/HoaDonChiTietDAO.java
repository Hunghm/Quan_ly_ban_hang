package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {

    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonChiTietDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDonChiTiet obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maHoaDonChiTiet, obj.getMaHoaDonChiTiet());
        values.put(Name.maSanPham, obj.getMaSanPham());
        values.put(Name.maHoaDon, obj.getMaHoaDon());
        values.put(Name.thanhTien, obj.getThanhTien());
        values.put(Name.soLuong, obj.getSoLuong());
        values.put(Name.hanLuuTru, sdf.format(obj.getHanLuuTru()));
        return db.insert("HoaDonChiTiet", null, values);
    }

    public int update(HoaDonChiTiet obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maHoaDonChiTiet, obj.getMaHoaDonChiTiet());
        values.put(Name.maSanPham, obj.getMaSanPham());
        values.put(Name.maHoaDon, obj.getMaHoaDon());
        values.put(Name.thanhTien, obj.getThanhTien());
        values.put(Name.soLuong, obj.getSoLuong());
        values.put(Name.hanLuuTru, sdf.format(obj.getHanLuuTru()));
        return db.update("HoaDonChiTiet", values, "maHoaDonChiTiet=?", new String[]{String.valueOf(obj.getMaHoaDonChiTiet())});
    }

    public int delete(String id) {
        return db.delete("HoaDonChiTiet", "maHoaDonChiTiet=?", new String[]{id});
    }

    public List<HoaDonChiTiet> getAll() throws ParseException {
        String sql = "SELECT * FROM HoaDonChiTiet";
        return getData(sql);
    }

    public HoaDonChiTiet getID(String id) throws ParseException {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE maHoaDonChiTiet=?";
        List<HoaDonChiTiet> list = getData(sql, id);
        return list.get(0);
    }

    private List<HoaDonChiTiet> getData(String sql, String... selectionArgs) throws ParseException {
        List<HoaDonChiTiet> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDonChiTiet obj = new HoaDonChiTiet();
            obj.setMaHoaDonChiTiet(c.getString(c.getColumnIndex(Name.maHoaDonChiTiet)));
            obj.setMaSanPham(c.getString(c.getColumnIndex(Name.maSanPham)));
            obj.setMaHoaDon(c.getString(c.getColumnIndex(Name.maHoaDon)));
            obj.setThanhTien(c.getDouble(c.getColumnIndex(Name.thanhTien)));
            obj.setSoLuong(c.getInt(c.getColumnIndex(Name.soLuong)));
            obj.setHanLuuTru((Date) sdf.parse(c.getString(c.getColumnIndex(Name.hanLuuTru))));
            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String maHoaDonChiTiet = "maHoaDonChiTiet";
        public static String maSanPham = "maSanPham";
        public static String maHoaDon = "maHoaDon";
        public static String thanhTien = "thanhTien";
        public static String soLuong = "soLuong";
        public static String hanLuuTru = "hanLuuTru";
    }

}
