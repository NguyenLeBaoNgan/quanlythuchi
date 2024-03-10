package com.example.qlthuchi.models;

import java.io.Serializable;
import java.util.List;

public class GiaoDich implements Serializable {
    private int id;
    private String soTien;
    private String thoiGian;
    private Integer danhMuc;
    private String phuongTienThanhToan;
    private String ghiChu;
    private Integer loaitien;
    private String tenloaitien;
    private String tendanhmuc;

    public GiaoDich(int id, String soTien, String thoiGian, Integer danhMuc, String phuongTienThanhToan, String ghiChu, Integer loaitien, String tenloaitien, String tendanhmuc) {
        this.id = id;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
        this.danhMuc = danhMuc;
        this.phuongTienThanhToan = phuongTienThanhToan;
        this.ghiChu = ghiChu;
        this.loaitien = loaitien;
        this.tenloaitien = tenloaitien;
        this.tendanhmuc = tendanhmuc;
    }

    public GiaoDich() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Integer getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(Integer danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getPhuongTienThanhToan() {
        return phuongTienThanhToan;
    }

    public void setPhuongTienThanhToan(String phuongTienThanhToan) {
        this.phuongTienThanhToan = phuongTienThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getLoaitien() {
        return loaitien;
    }

    public void setLoaitien(Integer loaitien) {
        this.loaitien = loaitien;
    }

    public String getTenloaitien() {
        return tenloaitien;
    }

    public void setTenloaitien(String tenloaitien) {
        this.tenloaitien = tenloaitien;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

}
