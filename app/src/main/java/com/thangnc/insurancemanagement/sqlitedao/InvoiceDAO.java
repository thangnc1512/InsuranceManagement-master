package com.thangnc.insurancemanagement.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thangnc.insurancemanagement.Constant;
import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO implements Constant {

    DatabaseHelper databaseHelper;

    public InvoiceDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertInvoice(String name, String date, int idCustomer, byte[] img){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_INVOICE, name);
        contentValues.put(DATE_INVOICE, date);
        contentValues.put(ID_CUSTOMER, idCustomer);
        contentValues.put(IMAGE, img);
        long id = database.insert(TABLE_INVOICE, null, contentValues);
        database.close();
        return id;
    }

    public List<Invoice> getAllInvoice(){
        List<Invoice> invoiceList = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String SELECT_ALL_INVOICE = "SELECT * FROM "+TABLE_INVOICE;
        Cursor cursor = database.rawQuery(SELECT_ALL_INVOICE, null);
        if (cursor.moveToFirst()) {
            do {
                Invoice invoice = new Invoice();
                invoice.setId(cursor.getInt(cursor.getColumnIndex(ID_INVOICE)));
                invoice.setName(cursor.getString(cursor.getColumnIndex(NAME_INVOICE)));
                invoice.setDate(cursor.getString(cursor.getColumnIndex(DATE_INVOICE)));
                invoice.setIdCus(cursor.getInt(cursor.getColumnIndex(ID_CUSTOMER)));
                invoice.setImg(cursor.getBlob(cursor.getColumnIndex(IMAGE)));
                invoiceList.add(invoice);
            } while (cursor.moveToNext());
            cursor.close();
            database.close();
        }
        return invoiceList;
    }
}
