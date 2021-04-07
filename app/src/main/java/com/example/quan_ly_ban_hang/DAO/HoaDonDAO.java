package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maHoaDon, obj.getMaHoaDon());
        values.put(Name.ngayNhapXuat, sdf.format(obj.getNgayNhapXuat()));
        values.put(Name.loaiHoaDon, obj.getLoaiHoaDon());
        return db.insert("HoaDon", null, values);
    }

    public int update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maHoaDon, obj.getMaHoaDon());
        values.put(Name.ngayNhapXuat, sdf.format(obj.getNgayNhapXuat()));
        values.put(Name.loaiHoaDon, obj.getLoaiHoaDon());
        return db.update("HoaDon", values, "maHoaDon=?", new String[]{String.valueOf(obj.getMaHoaDon())});
    }

    public int delete(String id) {
        return db.delete("HoaDon", "maHoaDon=?", new String[]{id});
    }

    public List<HoaDon> getAll() throws ParseException {
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id) throws ParseException {
        String sql = "SELECT * FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    private List<HoaDon> getData(String sql, String... selectionArgs) throws ParseException {
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(c.getString(c.getColumnIndex(Name.maHoaDon)));
            obj.setNgayNhapXuat(sdf.parse(c.getString(c.getColumnIndex(Name.ngayNhapXuat))));
            obj.setLoaiHoaDon(c.getInt(c.getColumnIndex(Name.loaiHoaDon)));
            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String maHoaDon = "maHoaDon";
        public static String ngayNhapXuat = "ngayNhapXuat";
        public static String loaiHoaDon = "loaiHoaDon";
    }

}
