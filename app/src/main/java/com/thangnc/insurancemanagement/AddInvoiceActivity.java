package com.thangnc.insurancemanagement;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddInvoiceActivity extends AppCompatActivity {

    private EditText edtIDInvoice;
    private EditText edtIDCus;
    private EditText edtName;
    private EditText edtDate;
    private ImageView imgInvoice;
    private Button takePT;
    private Button choosePT;
    private Button btnSave;
    private Button btnClear;
    private final int REQUEST_TAKE_PHOTO = 123;
    private final int REQUEST_CHOOSE_PHOTO = 321;




    String[] listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();
            }
        });
        takePT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        choosePT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private void choosePicture(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgInvoice.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if (resultCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgInvoice.setImageBitmap(bitmap);
            }
        }
    }

//    @Override
//    public void onActivityReenter(int resultCode, Intent data) {
//        if (resultCode == RESULT_OK){
//            if (resultCode == REQUEST_CHOOSE_PHOTO){
//                try {
//                    Uri imageUri = data.getData();
//                    InputStream is = getContentResolver().openInputStream(imageUri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    imgInvoice.setImageBitmap(bitmap);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }else if (resultCode == REQUEST_TAKE_PHOTO){
//                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                imgInvoice.setImageBitmap(bitmap);
//            }
//        }
//        super.onActivityReenter(resultCode, data);
//    }

    private void initView(){
        edtIDInvoice = (EditText) findViewById(R.id.edtIDInvoice);
        edtIDCus = (EditText) findViewById(R.id.edtIDCus);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDate = (EditText) findViewById(R.id.edtDate);
        imgInvoice = (ImageView) findViewById(R.id.imgInvoice);
        takePT = (Button) findViewById(R.id.takePT);
        choosePT = (Button) findViewById(R.id.choosePT);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnClear = (Button) findViewById(R.id.btnClear);
        listType = getResources().getStringArray(R.array.type);
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
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}
