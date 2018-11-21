package com.thangnc.insurancemanagement.adapter;

import android.content.Context;
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
        txtDate.setText(invoice.date);

        Bitmap bmAvatar = BitmapFactory.decodeByteArray(invoice.img, 0, invoice.img.length);
        imgAvatar.setImageBitmap(bmAvatar);
        return row;
    }
}
