package com.thangnc.insurancemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.thangnc.insurancemanagement.adapter.CustomerAdapter;
import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.model.Customer;
import com.thangnc.insurancemanagement.sqlitedao.CustomerDAO;

public class DetailCustomerActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvID;
    private TextView tvGender;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvDOB;
    private TextView tvAddress;
    private CustomerDAO customerDAO;
    private CustomerAdapter adapter;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_customer);

        tvName = (TextView) findViewById(R.id.tvName);
        tvID = (TextView) findViewById(R.id.tvID);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvDOB = (TextView) findViewById(R.id.tvDOB);
        tvAddress = (TextView) findViewById(R.id.tvAddress);

        databaseHelper = new DatabaseHelper(getBaseContext());
        customerDAO = new CustomerDAO(databaseHelper);
//        int id = getIntent().getExtras().getInt("id");
//        Customer customer = new Customer();
//       customer = customerDAO.getCustomer();
    }

    private void initUI(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", -1);
    }
}
