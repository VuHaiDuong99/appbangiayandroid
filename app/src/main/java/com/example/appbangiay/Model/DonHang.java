package com.example.appbangiay.Model;

public class DonHang {
    private int id;
    private int idCustomer;
    private Double tongTien;

    public DonHang() {
    }

    public DonHang(int id, int idCustomer, Double tongTien) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
}
