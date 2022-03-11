package com.example.tradingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DynamicRVModelTransactions> items = new ArrayList<DynamicRVModelTransactions>();
    DynamicRVAdptTransactions dynamicRVAdptTransactions;
    String username;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        recyclerView = findViewById(R.id.trrv);
        Intent i = getIntent();
        username = i.getStringExtra("username");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.transactionsbuy(username);
        String upi, no, date, amt, id;
        while (cursor.moveToNext()){
            upi = cursor.getString(cursor.getColumnIndex("UPIID"));
            no = cursor.getString(cursor.getColumnIndex("NOOFCARDS"));
            date = cursor.getString(cursor.getColumnIndex("TRANSACTIONDATE"));
            id = cursor.getString(cursor.getColumnIndex("PAYMENTID"));
            amt = cursor.getString(cursor.getColumnIndex("Amount"));

            items.add(new DynamicRVModelTransactions(id,no,upi,date,amt,""));
        }

        dynamicRVAdptTransactions = new DynamicRVAdptTransactions(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(dynamicRVAdptTransactions);

    }
}