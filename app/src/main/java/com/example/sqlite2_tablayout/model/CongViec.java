package com.example.sqlite2_tablayout.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String tenCV,noidungCV,ngayHT,tinhtrang,congtac, loaiCV;

    public CongViec() {
    }

    public CongViec(String tenCV, String noidungCV, String ngayHT, String tinhtrang, String congtac, String loaiCV) {
        this.tenCV = tenCV;
        this.noidungCV = noidungCV;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
        this.loaiCV = loaiCV;
    }

    public CongViec(int id, String tenCV, String noidungCV, String ngayHT, String tinhtrang, String congtac, String loaiCV) {
        this.id = id;
        this.tenCV = tenCV;
        this.noidungCV = noidungCV;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
        this.loaiCV = loaiCV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getNoidungCV() {
        return noidungCV;
    }

    public void setNoidungCV(String noidungCV) {
        this.noidungCV = noidungCV;
    }

    public String getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(String ngayHT) {
        this.ngayHT = ngayHT;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getCongtac() {
        return congtac;
    }

    public void setCongtac(String congtac) {
        this.congtac = congtac;
    }

    public String getLoaiCV() {
        return loaiCV;
    }

    public void setLoaiCV(String loaiCV) {
        this.loaiCV = loaiCV;
    }
}
