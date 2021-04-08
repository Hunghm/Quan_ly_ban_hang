package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.Model.SanPham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public SanPhamDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham obj) {
        ContentValues values = new ContentValues();
//        values.put(Name.maSanPham, obj.getMaSanPham());
        values.put(Name.tenSanPham, obj.getTenSanPham());
        values.put(Name.giaNhap, obj.getGiaNhap());
        values.put(Name.giaXuat, obj.getGiaXuat());
        values.put(Name.ghiChu, obj.getGhiChu());
        values.put(Name.anh, obj.getAnh());
        values.put(Name.soLuong, obj.getSoLuong());
        values.put(Name.maLoaiSanPham, obj.getMaLoaiSanPham());
        return db.insert("SanPham", null, values);
    }

    public int update(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maSanPham, obj.getMaSanPham());
        values.put(Name.tenSanPham, obj.getTenSanPham());
        values.put(Name.giaNhap, obj.getGiaNhap());
        values.put(Name.giaXuat, obj.getGiaXuat());
        values.put(Name.ghiChu, obj.getGhiChu());
        values.put(Name.anh, obj.getAnh());
        values.put(Name.soLuong, obj.getSoLuong());
        values.put(Name.maLoaiSanPham, obj.getMaLoaiSanPham());
        return db.update("SanPham", values, "maSanPham=?", new String[]{String.valueOf(obj.getMaSanPham())});
    }

    public int delete(String id) {
        return db.delete("SanPham", "maSanPham=?", new String[]{id});
    }

    public List<SanPham> getAll()  {
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

    public SanPham getID(String id)  {
        String sql = "SELECT * FROM SanPham WHERE maSanPham=?";
        List<SanPham> list = getData(sql, id);
        return list.get(0);
    }

    private List<SanPham> getData(String sql, String... selectionArgs)  {
        List<SanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            SanPham obj = new SanPham();
            obj.setMaSanPham(c.getInt(c.getColumnIndex(Name.maSanPham)));
            obj.setTenSanPham(c.getString(c.getColumnIndex(Name.tenSanPham)));
            obj.setGiaNhap(c.getDouble(c.getColumnIndex(Name.giaNhap)));
            obj.setGiaXuat(c.getDouble(c.getColumnIndex(Name.giaXuat)));
            obj.setGhiChu(c.getString(c.getColumnIndex(Name.ghiChu)));
            obj.setAnh(c.getInt(c.getColumnIndex(Name.anh)));
            obj.setSoLuong(c.getInt(c.getColumnIndex(Name.soLuong)));
            obj.setMaLoaiSanPham(c.getInt(c.getColumnIndex(Name.maLoaiSanPham)));
            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String maSanPham = "maSanPham";
        public static String tenSanPham = "tenSanPham";
        public static String giaNhap = "giaNhap";
        public static String giaXuat = "giaXuat";
        public static String ghiChu = "ghiChu";
        public static String anh = "anh";
        public static String soLuong = "soLuong";
        public static String maLoaiSanPham = "maLoaiSanPham";
    }

}
