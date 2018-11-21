package com.thangnc.insurancemanagement.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thangnc.insurancemanagement.R;

import org.w3c.dom.Text;


public class CustomerHolder extends RecyclerView.ViewHolder {

    public final TextView tvName;
    public final TextView tvEmail;
    public final TextView tvPhone;
    public final ImageView imgEditUser;
    public final ImageView imgDeleteUser;


    public CustomerHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvEmail = itemView.findViewById(R.id.tvEmail);
        tvPhone = itemView.findViewById(R.id.tvPhone);
        imgEditUser = itemView.findViewById(R.id.imgEditUser);
        imgDeleteUser = itemView.findViewById(R.id.imgDeleteUser);
    }
}
