package com.thangnc.insurancemanagement.model;

public class Invoice {
    public String name;
    public long date;
    public int id, idCus;
    public byte[] img;

    public Invoice(String name, long date, int id, int idCus, byte[] img) {
        this.name = name;
        this.date = date;
        this.id = id;
        this.idCus = idCus;
        this.img = img;
    }

    public Invoice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
