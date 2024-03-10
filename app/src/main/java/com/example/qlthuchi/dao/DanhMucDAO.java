package com.example.qlthuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.qlthuchi.database.DBHelper;
import com.example.qlthuchi.models.DanhMuc;

import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    DBHelper dbHelper;
    public DanhMucDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public List<DanhMuc> GetAll()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<DanhMuc> listProduct = new ArrayList<>();
        String query = "SELECT * FROM DanhMuc";
        Cursor c =  db.rawQuery(query, null);
        while (c.moveToNext())
        {
            DanhMuc temp = new DanhMuc();
            temp.setId(c.getInt(0));
            temp.setTenDanhMuc(c.getString(1));
            listProduct.add(temp);
        }
        return listProduct;
    }
    public void Insert(DanhMuc p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendanhmuc", p.getTenDanhMuc());
        db.insert("danhmuc", null, values);
    }

    public void Delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("DanhMuc", "id=?", new String[] { String.valueOf(id) });

    }
    public void Update(DanhMuc p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendanhmuc", p.getTenDanhMuc());

        db.update("danhmuc", values, "id=?", new String[]{String.valueOf(p.getId())});
    }

}
