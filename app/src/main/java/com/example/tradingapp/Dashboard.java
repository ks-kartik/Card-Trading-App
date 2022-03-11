package com.example.tradingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tradingapp.DRVinterface.LoadMore;
import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity implements UpdateRecyclerView,GetOnClickCard {

    RecyclerView recyclerView, recyclerView2;
    StaticRvAdpt staticRvAdpt;
    int pos,posstatic;
    String u = "";
    ArrayList<StaticRv> item = new ArrayList<>();
    //Dynamic
    String playername = "";
    ImageView icon;
    ArrayList<DynamicRVModel> items;
    DynamicRVAdpt dynamicRVAdpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent i = getIntent();
        u += i.getStringExtra("Username");
        TextView username = findViewById(R.id.nameuser);
        username.setText(" "+u.toUpperCase(Locale.ROOT)+"!");



        item.add(new StaticRv(R.drawable.yourcards,"Your Cards",0));
        item.add(new StaticRv(R.drawable.allcard,"All Cards",1));
        item.add(new StaticRv(R.drawable.tradecards,"Trade",2));
        item.add(new StaticRv(R.drawable.requests,"Trade Requests",3));
        item.add(new StaticRv(R.drawable.buy,"Buy",4));



        recyclerView = findViewById(R.id.rv1);
        staticRvAdpt = new StaticRvAdpt(item, this, this, u,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdpt);





        items = new ArrayList<>();
        recyclerView2 = findViewById(R.id.rv2);
        dynamicRVAdpt = new DynamicRVAdpt(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView2.setAdapter(dynamicRVAdpt);


        icon = (ImageView) findViewById(R.id.icon111);
        icon.setClickable(true);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu dropmenu = new PopupMenu(getApplicationContext(), icon);
                dropmenu.getMenuInflater().inflate(R.menu.dropmenu,dropmenu.getMenu());
                dropmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        if(menuItem.getTitle().equals("Logout!")){
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }if(menuItem.getTitle().equals("Transactions")){
                            Intent i = new Intent(getApplicationContext(),Transactions.class);
                            i.putExtra("username",u);
                            startActivity(i);
                        }

                        return true;
                    }
                });
                dropmenu.show();
            }
        });
    }

    @Override
    public void onCardBarClick(int poss) {
        StaticRv i = item.get(poss);
        posstatic = i.getPos();
    }

    @Override
    public void callback(int position, ArrayList<DynamicRVModel> items) {
        dynamicRVAdpt = new DynamicRVAdpt(items);
        dynamicRVAdpt.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRVAdpt);

        dynamicRVAdpt.setOnIconClickListener(new DynamicRVAdpt.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos = items.get(position).getPos();
                playername = items.get(position).getName();
                //Toast.makeText(getApplicationContext(), Integer.toString(pos), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),CardClick.class);
                i.putExtra("poss",pos);
                i.putExtra("username",u);
                i.putExtra("playername",playername);
                i.putExtra("possitionStatic",posstatic);
                startActivity(i);
            }
        });
    }


}