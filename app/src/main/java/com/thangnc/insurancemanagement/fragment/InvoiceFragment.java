package com.thangnc.insurancemanagement.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thangnc.insurancemanagement.R;
import com.thangnc.insurancemanagement.adapter.InvoiceAdapter;
import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.model.Invoice;
import com.thangnc.insurancemanagement.sqlitedao.InvoiceDAO;

import java.util.ArrayList;

public class InvoiceFragment extends Fragment {
    ListView listView;
    ArrayList<Invoice> list;
    InvoiceAdapter adapter;
    DatabaseHelper databaseHelper;

    InvoiceDAO invoiceDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);
        listView = view.findViewById(R.id.lvInvoice);
        list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getContext());
        invoiceDAO = new InvoiceDAO(databaseHelper);
        list.addAll(invoiceDAO.getAllInvoice());
        adapter = new InvoiceAdapter(getContext(), list);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_function, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}
