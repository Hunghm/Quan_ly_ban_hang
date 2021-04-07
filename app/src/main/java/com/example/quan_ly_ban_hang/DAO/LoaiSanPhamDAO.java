package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoaiSanPhamDAO {

    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public LoaiSanPhamDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSanPham obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maLoaiSanPham, obj.getMaLoai());
        values.put(Name.tenLoai, obj.getTenLoai());
        return db.insert("LoaiSanPham", null, values);
    }

    public int update(LoaiSanPham obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maLoaiSanPham, obj.getMaLoai());
        values.put(Name.tenLoai, obj.getTenLoai());
        return db.update("LoaiSanPham", values, "maLoaiSanPham=?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public int delete(String id) {
        return db.delete("LoaiSanPham", "maLoaiSanPham=?", new String[]{id});
    }

    public List<LoaiSanPham> getAll()  {
        String sql = "SELECT * FROM LoaiSanPham";
        return getData(sql);
    }

    public LoaiSanPham getID(String id) {
        String sql = "SELECT * FROM LoaiSanPham WHERE maLoaiSanPham=?";
        List<LoaiSanPham> list = getData(sql, id);
        return list.get(0);
    }

    private List<LoaiSanPham> getData(String sql, String... selectionArgs) {
        List<LoaiSanPham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            LoaiSanPham obj = new LoaiSanPham();
            obj.setMaLoai(c.getInt(c.getColumnIndex(Name.maLoaiSanPham)));
            obj.setTenLoai(c.getString(c.getColumnIndex(Name.tenLoai)));
            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String maLoaiSanPham = "maLoaiSanPham";
        public static String tenLoai = "tenLoai";
    }
}
