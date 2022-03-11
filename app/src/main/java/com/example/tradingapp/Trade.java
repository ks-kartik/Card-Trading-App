package com.example.tradingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Trade extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<DynamicRVModelTrade> items = new ArrayList<DynamicRVModelTrade>();
    DynamicRVAdptTrade dynamicRVAdptTrade;
    String username = "";
    String playerid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        recyclerView = findViewById(R.id.trade_rv);

        //items.add(new DynamicRVModelTrade("name","rating","rarity","country","team","https://www.transparentpng.com/download/pokemon/IJXG81-pokemon-hd-image.png",1, "fsa"));
        Intent i = getIntent();
        playerid = i.getStringExtra("playerid");
        username = i.getStringExtra("username");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.TradeAbleCards(username,playerid);
        int is = 0;
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("NAME"));
            @SuppressLint("Range") String rating = cursor.getString( cursor.getColumnIndex("RATING"));
            @SuppressLint("Range") String rarity = cursor.getString( cursor.getColumnIndex("RARITY"));
            @SuppressLint("Range") String country = cursor.getString( cursor.getColumnIndex("COUNTRY"));
            @SuppressLint("Range") String team = cursor.getString( cursor.getColumnIndex("TEAM"));
            @SuppressLint("Range") String url = cursor.getString( cursor.getColumnIndex("URL"));
            @SuppressLint("Range") String playerid = cursor.getString( cursor.getColumnIndex("PLAYERID"));
            @SuppressLint("Range") String username = cursor.getString( cursor.getColumnIndex("USERNAME"));
            items.add(new DynamicRVModelTrade(name,rating,rarity,country,team,url,is,username,playerid));
            is++;
        }



        dynamicRVAdptTrade = new DynamicRVAdptTrade(items);
        dynamicRVAdptTrade.setOnIconClickListener(new DynamicRVAdptTrade.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String playername = items.get(position).getName();
                Toast.makeText(getApplicationContext(), items.get(position).getPlayerid(), Toast.LENGTH_SHORT).show();
                //databaseHelper.REQUESTTRADE(username,items.get(position).getUsername(),playerid,items.get(position).getPlayerid());
                if(databaseHelper.REQUESTTRADE(username,items.get(position).getUsername(),playerid,items.get(position).getPlayerid())){
                    AlertDialog.Builder b = new AlertDialog.Builder(Trade.this);
                    b.setTitle("Success").setMessage("Request Created Successfully").setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    b.create();
                    b.show();
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(dynamicRVAdptTrade);

    }
}