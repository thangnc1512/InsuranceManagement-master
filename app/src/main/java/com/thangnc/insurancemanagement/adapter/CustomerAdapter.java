package com.thangnc.insurancemanagement.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thangnc.insurancemanagement.DetailCustomerActivity;
import com.thangnc.insurancemanagement.HomeActivity;
import com.thangnc.insurancemanagement.R;
import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.holder.CustomerHolder;
import com.thangnc.insurancemanagement.model.Customer;
import com.thangnc.insurancemanagement.sqlitedao.CustomerDAO;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerHolder> {

    private TextView tvName;
    private TextView tvID;
    private TextView tvGender;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvDOB;
    private TextView tvAddress;

    private DatabaseHelper databaseHelper;
    private Context context;
    private List<Customer> customerList;
    private CustomerDAO customerDAO;

    public CustomerAdapter(Context context, List<Customer> customerList, CustomerDAO customerDAO) {
        this.context = context;
        this.customerList = customerList;
        this.customerDAO = customerDAO;
    }

    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recustomer, viewGroup, false);
        return new CustomerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerHolder customerHolder, final int position) {
        final Customer customer = customerList.get(position);
        customerHolder.tvName.setText(customer.getName());
        customerHolder.tvEmail.setText(customer.getEmail());
        customerHolder.tvPhone.setText(customer.getPhone());
        customerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.item_customer, null);
                tvName = (TextView) view.findViewById(R.id.tvName);
                tvID = (TextView) view.findViewById(R.id.tvID);
                tvGender = (TextView) view.findViewById(R.id.tvGender);
                tvEmail = (TextView)view.findViewById(R.id.tvEmail);
                tvPhone = (TextView) view.findViewById(R.id.tvPhone);
                tvDOB = (TextView) view.findViewById(R.id.tvDOB);
                tvAddress = (TextView) view.findViewById(R.id.tvAddress);

                tvName.setText(customerList.get(position).getName());
                tvID.setText(customerList.get(position).getId()+"");
                tvGender.setText(customerList.get(position).getGender());
                tvEmail.setText(customerList.get(position).getEmail());
                tvDOB.setText(customerList.get(position).getDob());
                tvAddress.setText(customerList.get(position).getAddress());


                builder.setView(view);

                builder.show();

            }
        });
        customerHolder.imgDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Do U want to delete?????");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        customerDAO.deleteCustomer(customerList.get(position).getName());
                        customerList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });

        customerHolder.imgEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(customerList.get(position).getName());
                dialog.setContentView(R.layout.update_info);
                final EditText name = dialog.findViewById(R.id.edtName);
                final EditText email = dialog.findViewById(R.id.edtEmail);
                final EditText dob = dialog.findViewById(R.id.edtDOB);
                final EditText address = dialog.findViewById(R.id.edtAddress);
                final EditText phone = dialog.findViewById(R.id.edtPhone);

                name.setText(customer.getName());
                email.setText(customer.getEmail());
                dob.setText(customer.getDob());
                address.setText(customer.getAddress());
                phone.setText(customer.getPhone());


                dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Customer customer1 = new Customer();
                        customer1.setName(customerList.get(position).getName());
                        customer1.setEmail(email.getText().toString().trim());
                        customer1.setAddress(address.getText().toString().trim());
                        customer1.setPhone(phone.getText().toString().trim());
                        customer1.setPhone(dob.getText().toString().trim());
                        customerDAO.updateCustomer(customer1);
                        customerList.get(position).setName(name.getText().toString());
                        customerList.get(position).setEmail(email.getText().toString());
                        customerList.get(position).setAddress(dob.getText().toString());
                        customerList.get(position).setPhone(address.getText().toString());
                        customerList.get(position).setPhone(phone.getText().toString());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (customerList == null) return 0;
        return customerList.size();
    }
}
