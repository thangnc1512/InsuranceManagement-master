package com.thangnc.insurancemanagement.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thangnc.insurancemanagement.R;
import com.thangnc.insurancemanagement.adapter.InvoiceAdapter;
import com.thangnc.insurancemanagement.model.Invoice;

import java.util.ArrayList;

public class InvoiceFragment extends Fragment {
    ListView listView;
    ArrayList<Invoice> list;
    InvoiceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
