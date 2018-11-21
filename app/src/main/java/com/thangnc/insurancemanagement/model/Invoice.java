package com.thangnc.insurancemanagement.model;

public class Invoice {
    public String id, name, type;
    public int idCus;
    public byte[] img;

    public Invoice(String id, String name, String type, int idCus, byte[] img) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.idCus = idCus;
        this.img = img;
    }
}
