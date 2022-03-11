package com.example.tradingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardClick extends AppCompatActivity{
    int pos;
    String Username= "";
    String Playername= "";
    int poistion;
    String playerid="";


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        pos = i.getIntExtra("poss",0);

        poistion = i.getIntExtra("possitionStatic",0);


        Username = i.getStringExtra("username");
        Playername = i.getStringExtra("playername");
//        Toast.makeText(this,"BELOWCARD"+ Integer.toString(pos), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,"ABOVECARD:"+ Integer.toString(poistion), Toast.LENGTH_SHORT).show();
        DatabaseHelper db = new DatabaseHelper(this);

        if(poistion ==  0 || poistion == 2) {
            setContentView(R.layout.activity_card_click);
            Cursor cursor = db.ClickCards(Username, Playername);
            cursor.moveToNext();
            Toast.makeText(this, Integer.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();
            TextView t = (TextView) findViewById(R.id.nameplayer);
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("NAME"));
            t.setText(name);
            TextView t1 = (TextView) findViewById(R.id.Team);
            @SuppressLint("Range") String TEAM = cursor.getString(cursor.getColumnIndex("TEAM"));
            t1.setText(TEAM);
            TextView t2 = (TextView) findViewById(R.id.Country);
            @SuppressLint("Range") String COUNTRY = cursor.getString(cursor.getColumnIndex("COUNTRY"));
            t2.setText(COUNTRY);
            TextView T3 = (TextView) findViewById(R.id.PAC);
            @SuppressLint("Range") String PAC = cursor.getString(cursor.getColumnIndex("PAC"));
            T3.setText("PAC: " + PAC);
            TextView t4 = (TextView) findViewById(R.id.SHO);
            @SuppressLint("Range") String SHO = cursor.getString(cursor.getColumnIndex("SHO"));
            t4.setText("SHO: " + SHO);
            TextView t5 = (TextView) findViewById(R.id.PAS);
            @SuppressLint("Range") String PAS = cursor.getString(cursor.getColumnIndex("PAS"));
            t5.setText("PAS: " + PAS);
            TextView t6 = (TextView) findViewById(R.id.DRI);
            @SuppressLint("Range") String DRI = cursor.getString(cursor.getColumnIndex("DRI"));
            t6.setText("DRI: " + DRI);
            TextView t7 = (TextView) findViewById(R.id.DEF);
            @SuppressLint("Range") String DEF = cursor.getString(cursor.getColumnIndex("DEF"));
            t7.setText("DEF: " + DEF);
            TextView t8 = (TextView) findViewById(R.id.PHY);
            @SuppressLint("Range") String PHY = cursor.getString(cursor.getColumnIndex("PHY"));
            t8.setText("PHY: " + PHY);
            TextView t9 = (TextView) findViewById(R.id.playerid);
            playerid = cursor.getString(cursor.getColumnIndex("PLAYERID"));
            t9.setText("playerid- #" + playerid);


            ImageView s = (ImageView) findViewById(R.id.picture);
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("URL"));
            Picasso.get().load(url).into(s);

            Button button = (Button) findViewById(R.id.trade);
            button.setVisibility(View.VISIBLE);


            String rarity = "3";
            ConstraintLayout c = (ConstraintLayout) findViewById(R.id.cardexpandlayout);
            rarity = cursor.getString(cursor.getColumnIndex("RARITY"));

            TextView t10 = (TextView) findViewById(R.id.Rarity);
            int r = Integer.valueOf(rarity);
            if (r == 16) {
                c.setBackgroundColor(Color.parseColor("#FFDF00"));
                t10.setText("Rare!");
            } else if (r == 21) {
                c.setBackgroundColor(Color.parseColor("#C0C0C0"));
                t10.setText("Legendary!");
            } else {
                t10.setText("Common!");
            }
        }
        else if(poistion == 1){
            setContentView(R.layout.activity_card_click);
            Cursor cursor2 = db.ClickCardsAll((pos+1));
            cursor2.moveToNext();
            TextView t = (TextView) findViewById(R.id.nameplayer);
            @SuppressLint("Range") String name = cursor2.getString(cursor2.getColumnIndex("NAME"));
            t.setText(name);
            TextView t1 = (TextView) findViewById(R.id.Team);
            @SuppressLint("Range") String TEAM = cursor2.getString(cursor2.getColumnIndex("TEAM"));
            t1.setText(TEAM);
            TextView t2 = (TextView) findViewById(R.id.Country);
            @SuppressLint("Range") String COUNTRY = cursor2.getString(cursor2.getColumnIndex("COUNTRY"));
            t2.setText(COUNTRY);
            TextView T3 = (TextView) findViewById(R.id.PAC);
            @SuppressLint("Range") String PAC = cursor2.getString(cursor2.getColumnIndex("PAC"));
            T3.setText("PAC: " + PAC);
            TextView t4 = (TextView) findViewById(R.id.SHO);
            @SuppressLint("Range") String SHO = cursor2.getString(cursor2.getColumnIndex("SHO"));
            t4.setText("SHO: " + SHO);
            TextView t5 = (TextView) findViewById(R.id.PAS);
            @SuppressLint("Range") String PAS = cursor2.getString(cursor2.getColumnIndex("PAS"));
            t5.setText("PAS: " + PAS);
            TextView t6 = (TextView) findViewById(R.id.DRI);
            @SuppressLint("Range") String DRI = cursor2.getString(cursor2.getColumnIndex("DRI"));
            t6.setText("DRI: " + DRI);
            TextView t7 = (TextView) findViewById(R.id.DEF);
            @SuppressLint("Range") String DEF = cursor2.getString(cursor2.getColumnIndex("DEF"));
            t7.setText("DEF: " + DEF);
            TextView t8 = (TextView) findViewById(R.id.PHY);
            @SuppressLint("Range") String PHY = cursor2.getString(cursor2.getColumnIndex("PHY"));
            t8.setText("PHY: " + PHY);
            TextView t9 = (TextView) findViewById(R.id.playerid);
            @SuppressLint("Range") String playerid = cursor2.getString(cursor2.getColumnIndex("PLAYERID"));
            t9.setText("playerid- #" + playerid);

            ImageView s = (ImageView) findViewById(R.id.picture);
            @SuppressLint("Range") String url = cursor2.getString(cursor2.getColumnIndex("URL"));
            Picasso.get().load(url).into(s);

            String rarity = "3";
            ConstraintLayout c = (ConstraintLayout) findViewById(R.id.cardexpandlayout);
            rarity = cursor2.getString(cursor2.getColumnIndex("RARITY"));

            TextView t10 = (TextView) findViewById(R.id.Rarity);
            int r = Integer.valueOf(rarity);
            if (r == 16) {
                c.setBackgroundColor(Color.parseColor("#FFDF00"));
                t10.setText("Rare!");
            } else if (r == 21) {
                c.setBackgroundColor(Color.parseColor("#C0C0C0"));
                t10.setText("Legendary!");
            } else {
                t10.setText("Common!");
            }
        }
        else if (poistion == 3){
            setContentView(R.layout.cardclickdetailstrade);
            Cursor cursor3 = db.TRADEREQUESTSexpand(Username,Playername);
            cursor3.moveToNext();
            TextView t = (TextView) findViewById(R.id.nameplayer2);
            @SuppressLint("Range") String name = cursor3.getString(cursor3.getColumnIndex("NAME"));
            t.setText(name);
            TextView t1 = (TextView) findViewById(R.id.Team2);
            @SuppressLint("Range") String TEAM = cursor3.getString(cursor3.getColumnIndex("TEAM"));
            t1.setText(TEAM);
            TextView t2 = (TextView) findViewById(R.id.Country2);
            @SuppressLint("Range") String COUNTRY = cursor3.getString(cursor3.getColumnIndex("COUNTRY"));
            t2.setText(COUNTRY);
            TextView T3 = (TextView) findViewById(R.id.PAC2);
            @SuppressLint("Range") String PAC = cursor3.getString(cursor3.getColumnIndex("PAC"));
            T3.setText("PAC: " + PAC);
            TextView t4 = (TextView) findViewById(R.id.SHO2);
            @SuppressLint("Range") String SHO = cursor3.getString(cursor3.getColumnIndex("SHO"));
            t4.setText("SHO: " + SHO);
            TextView t5 = (TextView) findViewById(R.id.PAS2);
            @SuppressLint("Range") String PAS = cursor3.getString(cursor3.getColumnIndex("PAS"));
            t5.setText("PAS: " + PAS);
            TextView t6 = (TextView) findViewById(R.id.DRI2);
            @SuppressLint("Range") String DRI = cursor3.getString(cursor3.getColumnIndex("DRI"));
            t6.setText("DRI: " + DRI);
            TextView t7 = (TextView) findViewById(R.id.DEF2);
            @SuppressLint("Range") String DEF = cursor3.getString(cursor3.getColumnIndex("DEF"));
            t7.setText("DEF: " + DEF);
            TextView t8 = (TextView) findViewById(R.id.PHY2);
            @SuppressLint("Range") String PHY = cursor3.getString(cursor3.getColumnIndex("PHY"));
            t8.setText("PHY: " + PHY);
            TextView t9 = (TextView) findViewById(R.id.playerid2);
            @SuppressLint("Range") String playerid = cursor3.getString(cursor3.getColumnIndex("PLAYERID2"));
            t9.setText("playerid- #" + playerid);

            ImageView s = (ImageView) findViewById(R.id.picture2);
            @SuppressLint("Range") String url = cursor3.getString(cursor3.getColumnIndex("URL"));
            Picasso.get().load(url).into(s);

            String rarity = "3";
            ConstraintLayout c = (ConstraintLayout) findViewById(R.id.cardexpandlayout2);
            rarity = cursor3.getString(cursor3.getColumnIndex("RARITY"));

            TextView t10 = (TextView) findViewById(R.id.Rarity2);
            int r = Integer.valueOf(rarity);
            if (r == 16) {
                c.setBackgroundColor(Color.parseColor("#FFDF00"));
                t10.setText("Rare!");
            } else if (r == 21) {
                c.setBackgroundColor(Color.parseColor("#C0C0C0"));
                t10.setText("Legendary!");
            } else {
                t10.setText("Common!");
            }


            TextView tt = (TextView) findViewById(R.id.nameplayer);
            @SuppressLint("Range") String name1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1NAME"));
            tt.setText(name1);
            TextView tt1 = (TextView) findViewById(R.id.Team);
            @SuppressLint("Range") String TEAM1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1TEAM"));
            tt1.setText(TEAM1);
            TextView tt2 = (TextView) findViewById(R.id.Country);
            @SuppressLint("Range") String COUNTRY1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1COUNTRY"));
            tt2.setText(COUNTRY1);
            TextView TT3 = (TextView) findViewById(R.id.PAC);
            @SuppressLint("Range") String PAC1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1PAC"));
            TT3.setText("PAC: " + PAC1);
            TextView tt4 = (TextView) findViewById(R.id.SHO);
            @SuppressLint("Range") String SHO1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1SHO"));
            tt4.setText("SHO: " + SHO1);
            TextView tt5 = (TextView) findViewById(R.id.PAS);
            @SuppressLint("Range") String PAS1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1PAS"));
            tt5.setText("PAS: " + PAS1);
            TextView tt6 = (TextView) findViewById(R.id.DRI);
            @SuppressLint("Range") String DRI1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1DRI"));
            tt6.setText("DRI: " + DRI1);
            TextView tt7 = (TextView) findViewById(R.id.DEF);
            @SuppressLint("Range") String DEF1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1DEF"));
            tt7.setText("DEF: " + DEF1);
            TextView tt8 = (TextView) findViewById(R.id.PHY);
            @SuppressLint("Range") String PHY1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1PHY"));
            tt8.setText("PHY: " + PHY1);
            TextView tt9 = (TextView) findViewById(R.id.playerid);
            @SuppressLint("Range") String playerid1 = cursor3.getString(cursor3.getColumnIndex("PLAYERID1"));
            tt9.setText("playerid- #" + playerid1);

            ImageView ss = (ImageView) findViewById(R.id.picture);
            @SuppressLint("Range") String url1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1URL"));
            Picasso.get().load(url1).into(ss);

            String rarity1 = "3";
            ConstraintLayout c2 = (ConstraintLayout) findViewById(R.id.cardexpandlayout);
            rarity1 = cursor3.getString(cursor3.getColumnIndex("PLAYER1RARITY"));

            TextView tt10 = (TextView) findViewById(R.id.Rarity);
            int r2 = Integer.valueOf(rarity1);
            if (r2 == 16) {
                c2.setBackgroundColor(Color.parseColor("#FFDF00"));
                tt10.setText("Rare!");
            } else if (r2 == 21) {
                c2.setBackgroundColor(Color.parseColor("#C0C0C0"));
                tt10.setText("Legendary!");
            } else {
                tt10.setText("Common!");
            }
            String FROMUSERNAME = cursor3.getString(cursor3.getColumnIndex("FROMUSERNAME"));
            String TOUSERNAME = cursor3.getString(cursor3.getColumnIndex("TOUSERNAME"));
            Button accept = (Button) findViewById(R.id.acceptt);
            Button decline = (Button) findViewById(R.id.decline);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.accepttrade(FROMUSERNAME,TOUSERNAME,playerid1,playerid);
                    Toast.makeText(getApplicationContext(), "Accepted Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.declinetrade(FROMUSERNAME,playerid1,playerid);
                    Toast.makeText(getApplicationContext(), "Declined Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }
        else if (poistion == 4){
            AlertDialog.Builder b = new AlertDialog.Builder(CardClick.this);
            b.setTitle("Buy!");
            View buy = getLayoutInflater().inflate(R.layout.buyupi,null);
            b.setView(buy);

            b.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText editText = buy.findViewById(R.id.upiid);
                    sendDialogDataToActivity(editText.getText().toString());
                    int no = 0, amt = 0;
                    if(pos == 1){
                        no = 3;
                        amt = 999;
                    }else if(pos == 2){
                        no = 6;
                        amt = 1999;
                    }else if(pos == 3){
                        no = 9;
                        amt = 2999;
                    }else if(pos == 4){
                        no = 15;
                        amt = 3999;
                    }
                    db.buy(Username,editText.getText().toString(),no,amt);
                    Cursor c = db.randomcards(Username,Integer.toString(no));
                    while (c.moveToNext()){
                        String id = c.getString(c.getColumnIndex("PLAYERID"));
                        db.insertrandom(Username,id);
                    }

                    finish();
                }
            });
            b.create();
            b.show();
        }

    }

    private void sendDialogDataToActivity(String upi) {
        Toast.makeText(this, "Purchase Successful\n UPI - " + upi, Toast.LENGTH_SHORT).show();
    }

    public void tradefun(View view){
        Intent i = new Intent(this, Trade.class);
        i.putExtra("username",Username);
        i.putExtra("posititon",poistion);
        i.putExtra("playerid",playerid);
        startActivity(i);

    }

}