package com.example.qlthuchi.models;

public class LoaiTien {
    private int id;
    private String tenLoaiTien;

    public LoaiTien(int id, String tenLoaiTien) {
        this.id = id;
        this.tenLoaiTien = tenLoaiTien;
    }

    public LoaiTien() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiTien() {
        return tenLoaiTien;
    }

    public void setTenLoaiTien(String tenLoaiTien) {
        this.tenLoaiTien = tenLoaiTien;
    }
}
