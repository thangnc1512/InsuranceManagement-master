package com.thangnc.insurancemanagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thangnc.insurancemanagement.R;
import com.thangnc.insurancemanagement.model.Invoice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InvoiceAdapter extends BaseAdapter {
    Context context;
    ArrayList<Invoice> list;

    public InvoiceAdapter(Context context, ArrayList<Invoice> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String simpleDateFormat(long date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = simpleDateFormat.format(date);
        return dateFormat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_invoicelist, null);
        ImageView imgAvatar = row.findViewById(R.id.imgAvatar);
        ImageView imgEdit = row.findViewById(R.id.imgEdit);
        ImageView imgDelete = row.findViewById(R.id.imgDelete);
        TextView txtID = row.findViewById(R.id.txtID);
        TextView txtIdCus = row.findViewById(R.id.txtIDCus);
        TextView txtName = row.findViewById(R.id.txtNameCus);
        TextView txtDate = row.findViewById(R.id.txtDate);

        Invoice invoice = list.get(position);
        txtID.setText(invoice.id+"");
        txtName.setText(invoice.name);
        txtIdCus.setText(invoice.idCus+"");
        txtDate.setText(simpleDateFormat(invoice.getDate()));

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update Invoice");
                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater1.inflate(R.layout.update_invoice, null);

            }
        });
        Bitmap bmAvatar = BitmapFactory.decodeByteArray(invoice.img, 0, invoice.img.length);
        imgAvatar.setImageBitmap(bmAvatar);
        return row;
    }
}
