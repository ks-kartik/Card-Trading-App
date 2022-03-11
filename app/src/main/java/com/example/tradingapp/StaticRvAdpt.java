package com.example.tradingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StaticRvAdpt extends RecyclerView.Adapter<StaticRvAdpt.viewholder> {
    ArrayList<StaticRv> items;
    int orw = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;

    GetOnClickCard getOnClickCard;

    boolean check = true;
    boolean select = true;
    String username = "";



    public StaticRvAdpt(ArrayList<StaticRv> items, Activity activity, UpdateRecyclerView updateRecyclerView,String username,GetOnClickCard getOnClickCard) {
        this.items = items;
        this.activity=activity;
        this.updateRecyclerView=updateRecyclerView;
        this.username = username;
        this.getOnClickCard = getOnClickCard;
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        viewholder viewhold = new viewholder(view,getOnClickCard);
        return viewhold;
    }

    @SuppressLint("Range")
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        StaticRv currentitem = items.get(position);
        holder.imageView.setImageResource(currentitem.getImage());
        holder.textView.setText(currentitem.getName());
        if(check){
            // Your cards

            ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();

            DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());

            int i = 0;
            Cursor cursor = databaseHelper.MyCards(username);
            while (cursor.moveToNext()){

                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                String rating = cursor.getString( cursor.getColumnIndex("RATING"));
                String rarity = cursor.getString( cursor.getColumnIndex("RARITY"));
                String country = cursor.getString( cursor.getColumnIndex("COUNTRY"));
                String team = cursor.getString( cursor.getColumnIndex("TEAM"));
                String url = cursor.getString( cursor.getColumnIndex("URL"));
                String S = name+rarity+rating+country+team+url+"\n";
                items.add(new DynamicRVModel(name,"Rating - " + rating,"Rarity - " + rarity, country,"Team- "+team,url,i));
                i++;
            }


            updateRecyclerView.callback(position,items);
            check=false;


        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i2 = new Intent(activity.getApplicationContext(),CardClick.class);

                orw = position;
                //i2.putExtra("pois",1);
                notifyDataSetChanged();

                if(position == 0){
                    // Your cards

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());
                    Cursor cursor = databaseHelper.MyCards(username);

                    int i = 0;
                    String S="";
                    while (cursor.moveToNext()){

                        String name = cursor.getString(cursor.getColumnIndex("NAME"));
                        String rating = cursor.getString( cursor.getColumnIndex("RATING"));
                        String rarity = cursor.getString( cursor.getColumnIndex("RARITY"));
                        String country = cursor.getString( cursor.getColumnIndex("COUNTRY"));
                        String team = cursor.getString( cursor.getColumnIndex("TEAM"));
                        String url = cursor.getString( cursor.getColumnIndex("URL"));
                        S = name+rarity+rating+country+team+url+"\n";
                        items.add(new DynamicRVModel(name,"Rating - " + rating,"Rarity - " + rarity,country,"Team- "+team,url,i));
                        i++;
                    }
                    getOnClickCard.onCardBarClick(position);
                    updateRecyclerView.callback(position,items);
                }
                else if(position == 1){
                    // All cards

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();

                    DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());

                    int i = 0;
                    Cursor cursor = databaseHelper.AllCards();
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("NAME"));
                        String rating = cursor.getString( cursor.getColumnIndex("RATING"));
                        String rarity = cursor.getString( cursor.getColumnIndex("RARITY"));
                        String country = cursor.getString( cursor.getColumnIndex("COUNTRY"));
                        String team = cursor.getString( cursor.getColumnIndex("TEAM"));
                        String url = cursor.getString( cursor.getColumnIndex("URL"));
                        String S = name+rarity+rating+country+team+url+"\n";
                        items.add(new DynamicRVModel(name,"Rating - " + rating,"Rarity - " + rarity,country,"Team- "+team,url,i));
                        i++;
                    }

                    getOnClickCard.onCardBarClick(position);
                    updateRecyclerView.callback(position,items);
                }
                else if(position == 2){
                    // Your TRADECARDS

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());
                    Cursor cursor = databaseHelper.MyCards(username);

                    int i = 0;
                    String S="";
                    while (cursor.moveToNext()){

                        String name = cursor.getString(cursor.getColumnIndex("NAME"));
                        String rating = cursor.getString( cursor.getColumnIndex("RATING"));
                        String rarity = cursor.getString( cursor.getColumnIndex("RARITY"));
                        String country = cursor.getString( cursor.getColumnIndex("COUNTRY"));
                        String team = cursor.getString( cursor.getColumnIndex("TEAM"));
                        String url = cursor.getString( cursor.getColumnIndex("URL"));
                        S = name+rarity+rating+country+team+url+"\n";
                        items.add(new DynamicRVModel(name,"Rating - " + rating,"Rarity - " + rarity,country,"Team- "+team,url,i));
                        i++;
                    }

                    getOnClickCard.onCardBarClick(position);
                    updateRecyclerView.callback(position,items);
                }
                else if(position == 3){
                    // REQUEST CARDS

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());
                    Cursor cursor = databaseHelper.TRADEREQUESTS(username);

                    int i = 0;
                    String S="";
                    while (cursor.moveToNext()){

                        String FROMUSERNAME = cursor.getString(cursor.getColumnIndex("FROMUSERNAME"));
                        String PLAYER1NAME = cursor.getString( cursor.getColumnIndex("PLAYER1NAME"));
                        String PLAYER2NAME = cursor.getString( cursor.getColumnIndex("NAME"));
                        i++;
                        items.add(new DynamicRVModel(PLAYER1NAME ,"Request from "  + FROMUSERNAME," From " + PLAYER2NAME,"","CLICK FOR DETAILS AND ACTIONS","https://www.transparentpng.com/download/fog/clouds-transparent-png-9.png",i));
                    }

                    getOnClickCard.onCardBarClick(position);
                    updateRecyclerView.callback(position,items);
                }
                else if(position == 4){
                    // Buy CARDS

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Buy 3 cards","Rs. 999/-","Random Cards","","","https://www.transparentpng.com/download/order-now-button/3Puc4s-order-now-buy-button.png",1));
                    items.add(new DynamicRVModel("Buy 6 cards","Rs. 1999/-","Random Cards","","","https://www.transparentpng.com/download/order-now-button/3Puc4s-order-now-buy-button.png",2));
                    items.add(new DynamicRVModel("Buy 9 cards","Rs. 2999/-","Random Cards","","","https://www.transparentpng.com/download/order-now-button/3Puc4s-order-now-buy-button.png",3));
                    items.add(new DynamicRVModel("Buy 15 cards","Rs. 3999/-","Random Cards","","","https://www.transparentpng.com/download/order-now-button/3Puc4s-order-now-buy-button.png",4));


                    getOnClickCard.onCardBarClick(position);
                    updateRecyclerView.callback(position,items);
                }
                else {

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    getOnClickCard.onCardBarClick(position);
                    updateRecyclerView.callback(position,items);
                }
            }
        });

        if(select == true){
            if (position == 0){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bgselected);
            }
            select=false;
        }else {
            if(orw == position){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bgselected);
            }else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public viewholder(@NonNull View view,GetOnClickCard getOnClickCard) {
            super(view);
            imageView = view.findViewById(R.id.img1);
            textView = view.findViewById(R.id.text);
            linearLayout = view.findViewById(R.id.linearlayout);

        }



    }


}
