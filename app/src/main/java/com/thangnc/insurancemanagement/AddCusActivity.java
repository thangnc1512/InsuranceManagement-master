package com.thangnc.insurancemanagement;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.fragment.CustomerFragment;
import com.thangnc.insurancemanagement.model.Customer;
import com.thangnc.insurancemanagement.sqlitedao.CustomerDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCusActivity extends AppCompatActivity {

    private List<Customer> customerList;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtGender;
    private EditText edtDOB;
    private EditText edtAddress;
    private EditText edtPhone;
    private Button btnSave;
    private Button btnClear;
    String[] listGen;
    private CustomerDAO customerDAO;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);
        customerDAO = new CustomerDAO(databaseHelper);
        setContentView(R.layout.activity_add_cus);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        edtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGT();
            }
        });

        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                customer.setName(edtName.getText().toString());
                customer.setAddress(edtAddress.getText().toString());
                customer.setEmail(edtEmail.getText().toString());
                customer.setGender(edtGender.getText().toString());
                customer.setDob(edtDOB.getText().toString());
                customer.setPhone(edtPhone.getText().toString());
                customerDAO.insertCustomer(customer);
                Toast.makeText(AddCusActivity.this, "Add", Toast.LENGTH_SHORT).show();

//                String name = edtName.getText().toString();
//                String email = edtEmail.getText().toString();
//                String gender = edtGender.getText().toString();
//                String dob = edtDOB.getText().toString();
//                String address = edtAddress.getText().toString();
//                String phone = edtPhone.getText().toString();
//                if (name.equalsIgnoreCase("")) {
//                    edtName.setError("Import Name");
//                } else if (email.equalsIgnoreCase("")) {
//                    edtEmail.setError("Import Email");
//                } else if (gender.equalsIgnoreCase("")) {
//                    edtGender.setError("Choose gender");
//                } else if (dob.equalsIgnoreCase("")) {
//                    edtDOB.setError("Choose DOB");
//                } else if (address.equalsIgnoreCase("")) {
//                    edtAddress.setError("Import Address");
//                } else if (phone.equalsIgnoreCase("")) {
//                    edtPhone.setError("Import Phone");
//                } else {
//                    Customer customer = new Customer(name, gender, email, address, dob, phone);
//                    long result = customerDAO.insertCustomer(customer);
//                    if (result == -1) {
//                        edtName.setError("Customer existed");
//                    } else {
//                        customerList.add(customer);
//                    }
//                }
                Toast.makeText(AddCusActivity.this, "Add succed", Toast.LENGTH_SHORT).show();
                edtName.setText("");
                edtEmail.setText("");
                edtAddress.setText("");
                edtDOB.setText("");
                edtGender.setText("");
                edtPhone.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
                edtEmail.setText("");
                edtAddress.setText("");
                edtDOB.setText("");
                edtGender.setText("");
                edtPhone.setText("");
            }
        });

    }


    private void chonGT() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Gender");
        builder.setSingleChoiceItems(listGen, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtGender.setText(listGen[which]);
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = builder.create();
        mDialog.show();

    }

    private void chonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDOB.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    public void initView() {
        customerList = new ArrayList<>();
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtGender = (EditText) findViewById(R.id.edtGender);
        edtDOB = (EditText) findViewById(R.id.edtDOB);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnClear = (Button) findViewById(R.id.btnClear);
        listGen = getResources().getStringArray(R.array.gender);
    }
}
