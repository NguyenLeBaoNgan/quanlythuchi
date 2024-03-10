package com.example.qlthuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.qlthuchi.database.DBHelper;
import com.example.qlthuchi.models.DanhMuc;
import com.example.qlthuchi.models.LoaiTien;

import java.util.ArrayList;
import java.util.List;

public class LoaiTienDAO {
    DBHelper dbHelper;
    public LoaiTienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public List<LoaiTien> GetAll()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<LoaiTien> listProduct = new ArrayList<>();
        String query = "SELECT * FROM LoaiTien";
        Cursor c =  db.rawQuery(query, null);
        while (c.moveToNext())
        {
            LoaiTien temp = new LoaiTien();
            temp.setId(c.getInt(0));
            temp.setTenLoaiTien(c.getString(1));
            listProduct.add(temp);
        }
        return listProduct;
    }
    public void Insert(LoaiTien p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //c1: sử dụng contentValues
        ContentValues values = new ContentValues();
        // values.put("id", p.getId());
        values.put("tenloaitien", p.getTenLoaiTien());
        db.insert("loaitien", null, values);
        //c2: sử dụng câu queyry thuần
        //String query = String.format("INSERT INTO %s VALUES('%s','%s',%f, '%s')", "product", p.getId(),p.getName(), p.getImage(), p.getPrice());
        //db.execSQL(query);
    }

    public void Delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //c1: sử dụng delete
        db.delete("DanhMuc", "id=?", new String[] { String.valueOf(id) });
        //c2: sử dụng câu queyry thuần
        // String query = String.format("delete * from %s where id = %d", "product", productId);
        //db.execSQL(query);
    }
    public void Update(LoaiTien p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloaitien", p.getTenLoaiTien());
        db.update("loaitien", values, "id=?", new String[]{String.valueOf(p.getId())});
    }
}
