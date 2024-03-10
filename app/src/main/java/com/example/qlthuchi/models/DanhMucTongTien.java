package com.example.qlthuchi.models;

public class DanhMucTongTien {
    private String tendanhmuc;
    private int totalSotien;
    private int color;

    public DanhMucTongTien(String tendanhmuc, int totalSotien, int color) {
        this.tendanhmuc = tendanhmuc;
        this.totalSotien = totalSotien;
        this.color = color;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public int getTotalSotien() {
        return totalSotien;
    }

    public int getColor() {
        return color;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public void setTotalSotien(int totalSotien) {
        this.totalSotien = totalSotien;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public DanhMucTongTien() {
    }
}
