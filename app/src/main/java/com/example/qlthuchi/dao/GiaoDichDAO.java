package com.example.qlthuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import com.example.qlthuchi.database.DBHelper;
import com.example.qlthuchi.models.DanhMucTongTien;
import com.example.qlthuchi.models.GiaoDich;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class GiaoDichDAO {
    DBHelper dbHelper;
    public GiaoDichDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public List<GiaoDich> GetAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<GiaoDich> listProduct = new ArrayList<>();
        String query = "SELECT GiaoDich.*, DanhMuc.tendanhmuc, LoaiTien.tenloaitien " +
                "FROM GiaoDich " +
                "JOIN DanhMuc ON GiaoDich.danhMuc = DanhMuc.id " +
                "JOIN LoaiTien ON GiaoDich.loaitien = LoaiTien.id";
        Cursor c = db.rawQuery(query, null);
        int columnIndexTendanhmuc = c.getColumnIndex("tendanhmuc");
        int columnIndexTenloaitien = c.getColumnIndex("tenloaitien");
        while (c.moveToNext()) {
            GiaoDich temp = new GiaoDich();
            temp.setId(c.getInt(0));
            temp.setSoTien(c.getString(1));
            temp.setThoiGian(c.getString(2));

            // Lấy tendanhmuc từ cột mới trong câu truy vấn
            if (columnIndexTendanhmuc != -1) {
                temp.setTendanhmuc(c.getString(columnIndexTendanhmuc));
            }

            temp.setPhuongTienThanhToan(c.getString(4));
            temp.setGhiChu(c.getString(5));

            // Lấy tenloaitien từ cột mới trong câu truy vấn
            if (columnIndexTenloaitien != -1) {
                temp.setTenloaitien(c.getString(columnIndexTenloaitien));
            }

            listProduct.add(temp);
        }
        return listProduct;
    }

    public void  Insert(GiaoDich p) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //c1: sử dụng contentValues
            ContentValues values = new ContentValues();
            // values.put("id", p.getId());
            values.put("sotien", p.getSoTien());
            values.put("thoigian", p.getThoiGian());
            values.put("danhmuc", p.getDanhMuc());
            values.put("phuongtienthanhtoan", p.getPhuongTienThanhToan());
            values.put("ghichu", p.getGhiChu());
            values.put("loaitien", p.getLoaitien());
            db.insert("giaodich", null, values);

    }

    public void Delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //c1: sử dụng delete
        db.delete("giaodich", "id=?", new String[] { String.valueOf(id) });
        //c2: sử dụng câu queyry thuần
       // String query = String.format("delete * from %s where id = %d", "product", productId);
        //db.execSQL(query);
    }
    public void Update(GiaoDich p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien", p.getSoTien());
        values.put("thoigian", p.getThoiGian());
        values.put("danhmuc", p.getDanhMuc());
        values.put("phuongtienthanhtoan", p.getPhuongTienThanhToan());
        values.put("ghichu", p.getGhiChu());
        values.put("loaitien", p.getLoaitien());

        db.update("giaodich", values, "id=?", new String[]{String.valueOf(p.getId())});
    }
    public List<GiaoDich> GetGiaoDichByMonth(String month) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<GiaoDich> listGiaoDich = new ArrayList<>();


        String query = "SELECT GiaoDich.*, DanhMuc.tendanhmuc, LoaiTien.tenloaitien " +
                "FROM GiaoDich " +
                "JOIN DanhMuc ON GiaoDich.danhMuc = DanhMuc.id " +
                "JOIN LoaiTien ON GiaoDich.loaitien = LoaiTien.id " +
                "WHERE GiaoDich.thoiGian LIKE ?";
        String[] selectionArgs = {"%" + month};
        Cursor c = db.rawQuery(query, selectionArgs);

        int columnIndexTendanhmuc = c.getColumnIndex("tendanhmuc");
        int columnIndexTenloaitien = c.getColumnIndex("tenloaitien");

        while (c.moveToNext()) {
            GiaoDich temp = new GiaoDich();
            temp.setId(c.getInt(0));
            temp.setSoTien(c.getString(1));
            temp.setThoiGian(c.getString(2));

            // Lấy tendanhmuc từ cột mới trong câu truy vấn
            if (columnIndexTendanhmuc != -1) {
                temp.setTendanhmuc(c.getString(columnIndexTendanhmuc));
            }

            temp.setPhuongTienThanhToan(c.getString(4));
            temp.setGhiChu(c.getString(5));

            // Lấy tenloaitien từ cột mới trong câu truy vấn
            if (columnIndexTenloaitien != -1) {
                temp.setTenloaitien(c.getString(columnIndexTenloaitien));
            }

            listGiaoDich.add(temp);
        }
        c.close();
        return listGiaoDich;
    }
    public List<GiaoDich> GetGiaoDichByDate(String month) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<GiaoDich> listGiaoDich = new ArrayList<>();


        String query = "SELECT GiaoDich.*, DanhMuc.tendanhmuc, LoaiTien.tenloaitien " +
                "FROM GiaoDich " +
                "JOIN DanhMuc ON GiaoDich.danhMuc = DanhMuc.id " +
                "JOIN LoaiTien ON GiaoDich.loaitien = LoaiTien.id " +
                "WHERE GiaoDich.thoiGian LIKE ?";
        String[] selectionArgs = {"%"+ month};
        Cursor c = db.rawQuery(query, selectionArgs);

        int columnIndexTendanhmuc = c.getColumnIndex("tendanhmuc");
        int columnIndexTenloaitien = c.getColumnIndex("tenloaitien");

        while (c.moveToNext()) {
            GiaoDich temp = new GiaoDich();
            temp.setId(c.getInt(0));
            temp.setSoTien(c.getString(1));
            temp.setThoiGian(c.getString(2));

            // Lấy tendanhmuc từ cột mới trong câu truy vấn
            if (columnIndexTendanhmuc != -1) {
                temp.setTendanhmuc(c.getString(columnIndexTendanhmuc));
            }

            temp.setPhuongTienThanhToan(c.getString(4));
            temp.setGhiChu(c.getString(5));

            // Lấy tenloaitien từ cột mới trong câu truy vấn
            if (columnIndexTenloaitien != -1) {
                temp.setTenloaitien(c.getString(columnIndexTenloaitien));
            }

            listGiaoDich.add(temp);
        }
        c.close();
        return listGiaoDich;
    }
    public List<DanhMucTongTien> GetTotalSotienThuByDanhmucNgay(String thang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DanhMucTongTien> danhMucTongTienList = new ArrayList<>();

        String query = "SELECT DanhMuc.tendanhmuc, SUM(GiaoDich.sotien) " +
                "FROM GiaoDich " +
                "JOIN DanhMuc ON GiaoDich.danhMuc = DanhMuc.id " +
                "WHERE GiaoDich.thoigian LIKE ? AND GiaoDich.loaitien = 1  " +
                "GROUP BY DanhMuc.tendanhmuc";

        Cursor c = db.rawQuery(query, new String[] { "%"+thang+"%" }); // Tham số ngày

        while (c.moveToNext()) {
            String tendanhmuc = c.getString(0);
            int totalSotien = c.getInt(1);
            DanhMucTongTien danhMucTongTien = new DanhMucTongTien(tendanhmuc, totalSotien, 0);
            danhMucTongTienList.add(danhMucTongTien);
        }

        c.close();
        return danhMucTongTienList;
    }

    public List<DanhMucTongTien> GetTotalSotienChiByDanhmucNgay(String thang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DanhMucTongTien> danhMucTongTienList = new ArrayList<>();

        String query = "SELECT DanhMuc.tendanhmuc, SUM(GiaoDich.sotien) " +
                "FROM GiaoDich " +
                "JOIN DanhMuc ON GiaoDich.danhMuc = DanhMuc.id " +
                "WHERE GiaoDich.thoigian LIKE ? AND GiaoDich.loaitien = 2  " +
                "GROUP BY DanhMuc.tendanhmuc";

        Cursor c = db.rawQuery(query, new String[] { "%"+thang+"%" }); // Tham số ngày

        while (c.moveToNext()) {
            String tendanhmuc = c.getString(0);
            int totalSotien = c.getInt(1);
            DanhMucTongTien danhMucTongTien = new DanhMucTongTien(tendanhmuc, totalSotien, 0);
            danhMucTongTienList.add(danhMucTongTien);
        }

        c.close();
        return danhMucTongTienList;
    }
    public List<DanhMucTongTien> GetTotalSotienThuChi(String thang) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DanhMucTongTien> danhMucTongTienList = new ArrayList<>();

        String query = "SELECT LoaiTien.tenLoaiTien, SUM(GiaoDich.sotien) " +
                "FROM GiaoDich " +
                "JOIN LoaiTien ON GiaoDich.loaitien = LoaiTien.id " +
                "WHERE GiaoDich.thoigian LIKE ? AND (GiaoDich.loaitien = 2 OR GiaoDich.loaitien = 1)  " +
                "GROUP BY GiaoDich.loaitien";


        Cursor c = db.rawQuery(query, new String[] { "%"+thang+"%" }); // Tham số ngày

        while (c.moveToNext()) {
            String tendanhmuc = c.getString(0);
            int totalSotien = c.getInt(1);
            DanhMucTongTien danhMucTongTien = new DanhMucTongTien(tendanhmuc, totalSotien, 0);
            danhMucTongTienList.add(danhMucTongTien);
        }

        c.close();
        return danhMucTongTienList;
    }
}
