package com.example.quan_ly_ban_hang.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    public static final String DataBaseName = "nhapXuatdb";
    public Context context;
    private static final int version = 1;

    public dbHelper(@Nullable Context context) {
        super(context, DataBaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableHoaDon = "create table HoaDon(" +
                "maHoaDon TEXT PRIMARY KEY, " +
                "ngayNhapXuat DATE NOT NULL," +
                "loaiHoaDon INTEGER NOT NULL)";
        db.execSQL(createTableHoaDon);

        String createTableHoaDonChiTiet = "create table HoaDonChiTiet(" +
                "maHoaDonChiTiet TEXT PRIMARY KEY, " +
                "maSanPham TEXT NOT NULL," +
                "maHoaDon TEXT PRIMARY KEY, " +
                "thanhTien Double NOT NULL," +
                "soLuong INTEGER NOT NULL," +
                "hanLuuTru DATE NOT NULL)";
        db.execSQL(createTableHoaDonChiTiet);

        String createTableSanPham = "create table SanPham(" +
                "maSanPham TEXT PRIMARY KEY, " +
                "tenSanPham TEXT NOT NULL," +
                "giaNhap DOUBLE PRIMARY KEY, " +
                "giaXuat DOUBLE NOT NULL," +
                "ghiChu TEXT PRIMARY KEY, " +
                "anh INTEGER NOT NULL)";
        db.execSQL(createTableSanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
