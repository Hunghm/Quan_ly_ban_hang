package com.example.quan_ly_ban_hang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quan_ly_ban_hang.Database.DBHelper;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {

    private SQLiteDatabase db;

    public UserDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(User obj) {
        ContentValues values = new ContentValues();
        values.put(Name.hoTen, obj.getHoTen());
        values.put(Name.loai, obj.getLoai());
        values.put(Name.user, obj.getUser());
        values.put(Name.password, obj.getPassword());
        return db.insert("User", null, values);
    }

    public int update(User obj) {
        ContentValues values = new ContentValues();
        values.put(Name.hoTen, obj.getHoTen());
        values.put(Name.loai, obj.getLoai());
        values.put(Name.user, obj.getUser());
        values.put(Name.password, obj.getPassword());
        return db.update("User", values, "User=?", new String[]{String.valueOf(obj.getUser())});
    }

    public int delete(String id) {
        return db.delete("User", "User=?", new String[]{id});
    }

    public List<User> getAll()  {
        String sql = "SELECT * FROM User";
        return getData(sql);
    }

    public User getID(String id) {
        String sql = "SELECT * FROM User WHERE User=?";
        List<User> list = getData(sql, id);
        return list.get(0);
    }

    private List<User> getData(String sql, String... selectionArgs) {
        List<User> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            User obj = new User();
            obj.setHoTen(c.getString(c.getColumnIndex(Name.hoTen)));
            obj.setLoai(c.getInt(c.getColumnIndex(Name.loai)));
            obj.setPassword(c.getString(c.getColumnIndex(Name.password)));
            obj.setUser(c.getString(c.getColumnIndex(Name.user)));
            list.add(obj);
        }
        return list;
    }

    public static class Name {
        public static String user = "User";
        public static String password = "password";
        public static String hoTen = "hoTen";
        public static String loai = "loai";
    }

}
