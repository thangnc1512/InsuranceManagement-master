package com.thangnc.insurancemanagement;

public interface Constant {

    String TABLE_CUSTOMER = "CUSTOMER";

    String COLUMN_ID = "ID";

    String COLUMN_NAME = "Name";

    String COLUMN_GENDER = "Gender";

    String COLUMN_PHONE = "Phone";

    String COLUMN_EMAIL = "Email";

    String COLUMN_ADDRESS = "Address";

    String COLUMN_DOB = "DOB";



    String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_NAME + " VARCHAR(50) NOT NULL,"
            + COLUMN_GENDER + " VARCHAR(20),"
            + COLUMN_EMAIL + " VARCHAR(20),"
            + COLUMN_PHONE + " VARCHAR(20),"
            + COLUMN_ADDRESS + " VARCHAR(20),"
            + COLUMN_DOB + " VARCHAR(20)"
            + ")";


    String TABLE_INVOICE = "Invoice";
    String ID_INVOICE = "IdIV";
    String NAME_INVOICE = "NameIV";
    String DATE_INVOICE = "Date";
    String ID_CUSTOMER = "IdCustomer";
    String IMAGE = "Image";


    String CREATE_TABLE_INVOICE = "CREATE TABLE " + TABLE_INVOICE + " ("
            + ID_INVOICE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_INVOICE + " VARCHAR(30),"
            + DATE_INVOICE + " LONG,"
            + IMAGE + " BLOB,"
            + ID_CUSTOMER + " VARCHAR(20)"
            + ")";
}
