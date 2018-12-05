package com.thangnc.insurancemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thangnc.insurancemanagement.model.Customer;
import com.thangnc.insurancemanagement.model.Invoice;

import java.util.List;

public class Customer_Spinner extends ArrayAdapter<Customer> {

    Context context;
    int resource;
    List<Customer> object;

    public Customer_Spinner(Context context, int resource, List<Customer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_spinner, null);
        TextView tvIdCus = view.findViewById(R.id.tvIdCus);
        TextView tvNameCus = view.findViewById(R.id.tvNameCus);

        Customer customer = object.get(position);
        tvIdCus.setText(customer.getId());
        tvNameCus.setText(customer.getName().toString());
        return super.getView(position, convertView, parent);
    }
}
