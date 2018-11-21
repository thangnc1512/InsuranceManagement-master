package com.thangnc.insurancemanagement.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thangnc.insurancemanagement.Constant;
import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements Constant {
    DatabaseHelper databaseHelper;

    public CustomerDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertCustomer(Customer customer){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, customer.getName());
        contentValues.put(COLUMN_GENDER, customer.getGender());
        contentValues.put(COLUMN_PHONE, customer.getPhone());
        contentValues.put(COLUMN_EMAIL, customer.getEmail());
        contentValues.put(COLUMN_ADDRESS, customer.getAddress());
        contentValues.put(COLUMN_DOB, customer.getDob());

        long result = sqLiteDatabase.insert(TABLE_CUSTOMER, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateCustomer(Customer customer){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, customer.getName());
        contentValues.put(COLUMN_GENDER, customer.getGender());
        contentValues.put(COLUMN_PHONE, customer.getPhone());
        contentValues.put(COLUMN_EMAIL, customer.getEmail());
        contentValues.put(COLUMN_ADDRESS, customer.getAddress());
        contentValues.put(COLUMN_DOB, customer.getDob());
        long result = sqLiteDatabase.update(TABLE_CUSTOMER, contentValues, COLUMN_NAME+"=?",
                new String[]{String.valueOf(customer.getName())});
        return result;
    }

    public long deleteCustomer(String name){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database.delete(TABLE_CUSTOMER, COLUMN_NAME+"=?",
                new String[]{String.valueOf(name)});
    }

    public List<Customer> getAllCustomer(){
        List<Customer> userList = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String SELECT_ALL_USERS = "SELECT * FROM "+TABLE_CUSTOMER;
        Cursor cursor = database.rawQuery(SELECT_ALL_USERS, null);
        if (cursor.moveToFirst()){
            do {
                Customer customer1 = new Customer();
                customer1.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                customer1.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                customer1.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));;
                customer1.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));;
                customer1.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                customer1.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB)));
                customer1.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                userList.add(customer1);
            } while (cursor.moveToNext());
            cursor.close();
            database.close();
        }
        return userList;
    }

    public Customer getCustomer(int id){
        Customer customer = null;
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query(TABLE_CUSTOMER,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_EMAIL, COLUMN_PHONE, COLUMN_ADDRESS, COLUMN_DOB},
                COLUMN_ID+"=?",new String[]{String.valueOf(id)},null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            Customer customer1 = new Customer();
            customer1.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
            customer1.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            customer1.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));;
            customer1.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));;
            customer1.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            customer1.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB)));
            customer1.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));

        }
        return customer;
    }
}