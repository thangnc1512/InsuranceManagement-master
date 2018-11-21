package com.thangnc.insurancemanagement.model;

public class Invoice {
    public String name, date;
    public int id, idCus;
    public byte[] img;

    public Invoice(int id, String name, String date, int idCus, byte[] img) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.idCus = idCus;
        this.img = img;
    }

    public Invoice() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdCus() {
        return idCus;
    }

    public void setIdCus(int idCus) {
        this.idCus = idCus;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
