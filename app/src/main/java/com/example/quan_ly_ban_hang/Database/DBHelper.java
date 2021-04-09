package com.example.quan_ly_ban_hang.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DataBaseName = "nhapXuatdb";
    public Context context;
    private static final int version = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DataBaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableHoaDon = "create table HoaDon(" +
                "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ngayNhapXuat DATE NOT NULL," +
                "loaiHoaDon INTEGER NOT NULL)";
        db.execSQL(createTableHoaDon);

        String createTableHoaDonChiTiet = "create table HoaDonChiTiet(" +
                "maHoaDonChiTiet INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maSanPham INTEGER NOT NULL REFERENCES SanPham(maSanPham)," +
                "maHoaDon INTEGER NOT NULL REFERENCES HoaDon(maHoaDon), " +
                "soLuong INTEGER NOT NULL," +
                "hanLuuTru DATE )";
        db.execSQL(createTableHoaDonChiTiet);

        String createTableSanPham = "create table SanPham(" +
                "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maLoaiSanPham INTEGER NOT NULL REFERENCES LoaiSanPham(maLoaiSanPham)," +
                "tenSanPham TEXT NOT NULL," +
                "soLuong INTEGER, " +
                "giaNhap DOUBLE NOT NULL, " +
                "giaXuat DOUBLE NOT NULL," +
                "ghiChu TEXT, " +
                "anh INTEGER NOT NULL)";
        db.execSQL(createTableSanPham);

        String createTableUser = "create table User(" +
                "User TEXT NOT NULL PRIMARY KEY , " +
                "password TEXT NOT NULL," +
                "hoTen TEXT NOT NULL," +
                "loai INTEGER) ";
        db.execSQL(createTableUser);

        String createTableLoaiSanPham = "create table LoaiSanPham(" +
                "maLoaiSanPham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSanPham);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
