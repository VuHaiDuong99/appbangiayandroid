package com.example.appbangiay.Model;

public class ChiTietDonHang {
    private int id;
    private int idDonHang;
    private int idProduct;
    private String nameProduct;
    private int soluong;
    private double giaTien;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int id, int idDonHang, int idProduct, String nameProduct, int soluong, double giaTien) {
        this.id = id;
        this.idDonHang = idDonHang;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.soluong = soluong;
        this.giaTien = giaTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
}
