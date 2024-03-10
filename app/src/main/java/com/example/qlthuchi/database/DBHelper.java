package com.example.qlthuchi.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "QLThuChi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng "Khoa"
        String createGiaoDichTableQuery = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER, " +
                        "FOREIGN KEY(%s) REFERENCES %s(%s), " +
                        "FOREIGN KEY(%s) REFERENCES %s(%s))",
                "giaodich", "id", "sotien", "thoigian", "danhmuc", "phuongtienthanhtoan", "ghichu", "loaitien", "danhmuc", "danhmuc", "id", "loaitien", "loaitien", "id");
        db.execSQL(createGiaoDichTableQuery);

        // Tạo bảng "DanhMuc"
        String createDanhMucTableQuery = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT)",
                "danhmuc", "id", "tendanhmuc");
        db.execSQL(createDanhMucTableQuery);
        insertDefaultCategories(db);

        // Tạo bảng "LoaiTien"
        String createLoaiTienTableQuery = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT)",
                "loaitien", "id", "tenloaitien");
        db.execSQL(createLoaiTienTableQuery);
        insertDefaultLoaiTien(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            //drop
            String query1 = "DROP TABLE loaitien";
            db.execSQL(query1);
            String query2 = "DROP TABLE danhmuc";
            db.execSQL(query2);
            String query3 = "DROP TABLE giaodich";
            db.execSQL(query3);
            //create again
            onCreate(db);
        }
    }

    private void insertDefaultCategories(SQLiteDatabase db) {
        String[] defaultCategories = {
                "Nhà ở",
                "Giao thông",
                "Thực phẩm",
                "Quần áo",
                "Giải trí",
                "Giáo dục",
                "Y tế",
                "Vay nợ",
                "Tiết kiệm",
                "Khác"
        };

        ContentValues values = new ContentValues();
        for (String category : defaultCategories) {
            values.put("tendanhmuc", category);
            db.insert("danhmuc", null, values);
        }

        //
    }
    private void insertDefaultLoaiTien(SQLiteDatabase db) {
        String[] defaultLoaiTien = {
                "Thu",
                "Chi",
                "Cho vay",
                "Nợ"
        };

        ContentValues values = new ContentValues();
        for (String loaiTien : defaultLoaiTien) {
            values.put("tenloaitien", loaiTien);
            db.insert("loaitien", null, values);
        }
    }
}
