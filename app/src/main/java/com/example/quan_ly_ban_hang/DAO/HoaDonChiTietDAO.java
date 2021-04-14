package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietDAO {

    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HoaDonChiTietDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDonChiTiet obj) {
        ContentValues values = new ContentValues();
//        values.put(Name.maHoaDonChiTiet, obj.getMaHoaDonChiTiet());
        values.put(Name.maSanPham, obj.getMaSanPham());
        values.put(Name.maHoaDon, obj.getMaHoaDon());
        values.put(Name.soLuong, obj.getSoLuong());
        values.put(Name.loaiHoaDon, obj.getLoaiHoaDon());
        values.put(Name.hanLuuTru, sdf.format(obj.getHanLuuTru()));
        return db.insert("HoaDonChiTiet", null, values);
    }

    public int update(HoaDonChiTiet obj) {
        ContentValues values = new ContentValues();
        values.put(Name.maHoaDonChiTiet, obj.getMaHoaDonChiTiet());
        values.put(Name.maSanPham, obj.getMaSanPham());
        values.put(Name.maHoaDon, obj.getMaHoaDon());
        values.put(Name.soLuong, obj.getSoLuong());
        values.put(Name.loaiHoaDon, obj.getLoaiHoaDon());
        values.put(Name.hanLuuTru, sdf.format(obj.getHanLuuTru()));
        return db.update("HoaDonChiTiet", values, "maHoaDonChiTiet=?", new String[]{String.valueOf(obj.getMaHoaDonChiTiet())});
    }

    public int delete(String id) {
        return db.delete("HoaDonChiTiet", "maHoaDonChiTiet=?", new String[]{id});
    }

    public List<HoaDonChiTiet> getAll()  {
        String sql = "SELECT * FROM HoaDonChiTiet";
        return getData(sql);
    }

    public HoaDonChiTiet getID(String id) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE maHoaDonChiTiet=?";
        List<HoaDonChiTiet> list = getData(sql, id);
        return list.get(0);
    }

    private List<HoaDonChiTiet> getData(String sql, String... selectionArgs) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDonChiTiet obj = new HoaDonChiTiet();
            obj.setMaHoaDonChiTiet(c.getInt(c.getColumnIndex(Name.maHoaDonChiTiet)));
            obj.setMaSanPham(c.getInt(c.getColumnIndex(Name.maSanPham)));
            obj.setMaHoaDon(c.getInt(c.getColumnIndex(Name.maHoaDon)));
            obj.setSoLuong(c.getInt(c.getColumnIndex(Name.soLuong)));
            obj.setLoaiHoaDon(c.getInt(c.getColumnIndex(Name.loaiHoaDon)));
            try {
                obj.setHanLuuTru((Date) sdf.parse(c.getString(c.getColumnIndex(Name.hanLuuTru))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }

    public HoaDonChiTiet getByIDHoaDon(String idHoaDon) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE maHoaDon=?";
        List<HoaDonChiTiet> list = getData(sql, idHoaDon);
        return list.get(0);
    }

    public List<Top> getTop(){
//        String sqlTop = "SELECT maSanPham, sum(soLuong) as soLuong, count(maSanPham) as soHoaDon FROM HoaDonChiTiet GROUP BY maSanPham ORDER BY soLuong DESC LIMIT 10";
        String sqlTop = "SELECT maSanPham, sum(soLuong) as soLuong, count(maSanPham) as soHoaDon FROM HoaDonChiTiet \n" +
                        "WHERE loaiHoaDon = 2 \n" +
                        "GROUP BY maSanPham ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlTop,null);
        while (c.moveToNext()) {
            Top obj = new Top();
            obj.setMaSanPham(c.getInt(c.getColumnIndex("maSanPham")));
            obj.setSoHoaDon(c.getInt(c.getColumnIndex("soHoaDon")));
            obj.setSoLuong(c.getInt(c.getColumnIndex("soLuong")));

            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String maHoaDonChiTiet = "maHoaDonChiTiet";
        public static String maSanPham = "maSanPham";
        public static String maHoaDon = "maHoaDon";
        public static String loaiHoaDon = "loaiHoaDon";
        public static String soLuong = "soLuong";
        public static String hanLuuTru = "hanLuuTru";
    }

}
