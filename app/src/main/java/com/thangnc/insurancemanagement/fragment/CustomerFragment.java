package com.thangnc.insurancemanagement.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thangnc.insurancemanagement.R;
import com.thangnc.insurancemanagement.adapter.CustomerAdapter;
import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.model.Customer;
import com.thangnc.insurancemanagement.sqlitedao.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends Fragment {

    private List<Customer> customerList;
    private RecyclerView lvUser;
    private DatabaseHelper databaseHelper;
    private CustomerDAO customerDAO;
    private CustomerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        lvUser = view.findViewById(R.id.recycleViewUser);
        databaseHelper = new DatabaseHelper(getContext());
        customerDAO = new CustomerDAO(databaseHelper);
        customerList =new ArrayList<>();
        customerList = customerDAO.getAllCustomer();
        adapter = new CustomerAdapter(getContext(), customerList, customerDAO);
        lvUser.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        lvUser.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        return view;
    }


}
