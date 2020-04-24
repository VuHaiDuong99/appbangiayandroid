package com.example.appbangiay.Model;

public class Cart {
    private int id;
    private String name;
    private Integer price;
    private byte[] image;
    private int countProduct;

    public Cart() {
    }

    public Cart(int id, String name, Integer price, byte[] image, int countProduct) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.countProduct = countProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
}
