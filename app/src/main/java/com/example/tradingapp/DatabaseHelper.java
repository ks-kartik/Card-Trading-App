package com.example.tradingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "appdb.db";
    public static final String TABLE_NAME1 = "USERACC";
    public static final String TABLE_NAME2 = "CARDS";
    public static final String TABLE_NAME3 = "INVENTORY";

    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table1 = "CREATE TABLE " + TABLE_NAME1 + "(USERNAME TEXT PRIMARY KEY, PASSWORD TEXT, FULLNAME TEXT, EMAIL TEXT, PHONENO INTEGER)";
        String table2 = "CREATE TABLE " + TABLE_NAME2 + "(PLAYERID TEXT PRIMARY KEY, NAME TEXT, URL TEXT, COUNTRY TEXT, TEAM TEXT, RATING INTEGER, PAC INT, SHO INT, PAS INT, DRI INT, DEF INT, PHY INT, RARITY INT)";
        String table3 = "CREATE TABLE " + TABLE_NAME3 + "(USERNAME TEXT, PLAYERID TEXT,CONSTRAINT A FOREIGN KEY (USERNAME) REFERENCES USERACC(USERNAME), CONSTRAINT B FOREIGN KEY (PLAYERID) REFERENCES CARDS(PLAYERID))";
        String table4 = "CREATE TABLE REQUESTTRADE (REQUESTID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, FROMUSERNAME TEXT,TOUSERNAME TEXT,PLAYERID1 TEXT, PLAYERID2 TEXT, CONSTRAINT B FOREIGN KEY(TOUSERNAME) REFERENCES USERACC(USERNAME),CONSTRAINT C FOREIGN KEY(PLAYERID1) REFERENCES CARDS(PLAYERID),CONSTRAINT D FOREIGN KEY(PLAYERID2) REFERENCES CARDS(PLAYERID),CONSTRAINT A FOREIGN KEY(FROMUSERNAME) REFERENCES USERACC(USERNAME))";
        String table5 = "CREATE TABLE TRADE (TRADEID INTEGER NOT NULL PRIMARY KEY,FROMUSERNAME TEXT,TOUSERNAME TEXT,PLAYERID1 TEXT,PLAYERID2 TEXT,TRADEDATE DATE, CONSTRAINT A FOREIGN KEY(FROMUSERNAME) REFERENCES USERACC(USERNAME), CONSTRAINT C FOREIGN KEY(PLAYERID1) REFERENCES CARDS(PLAYERID), CONSTRAINT D FOREIGN KEY(PLAYERID2) REFERENCES CARDS(PLAYERID), CONSTRAINT B FOREIGN KEY(TOUSERNAME) REFERENCES USERACC(USERNAME))";
        String table6 = "CREATE TABLE BUY (PAYMENTID INTEGER PRIMARY KEY AUTOINCREMENT, NOOFCARDS INTEGER, USERNAME TEXT, UPIID TEXT, TRANSACTIONDATE DATE, Amount INTEGER, CONSTRAINT A FOREIGN KEY(USERNAME) REFERENCES USERACC(USERNAME))";
        sqLiteDatabase.execSQL(table1);
        sqLiteDatabase.execSQL(table2);
        sqLiteDatabase.execSQL(table3);
        sqLiteDatabase.execSQL(table4);
        sqLiteDatabase.execSQL(table5);
        sqLiteDatabase.execSQL(table6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    public boolean InsertUserAcc(String username, String fname, String pass, String email, double phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v1 = new ContentValues();
        v1.put("USERNAME",username);
        v1.put("PASSWORD",pass);
        v1.put("FULLNAME",fname);
        v1.put("EMAIL",email);
        v1.put("PHONENO",phone);

        long result = db.insert(TABLE_NAME1,null,v1);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean AuthenticateUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USERACC WHERE USERNAME = ? AND PASSWORD = ?", new String[]{username,password});
        if (c.getCount()>0)
            return true;
        else
            return false;

    }

    public Cursor MyCards(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT USERACC.USERNAME, INVENTORY.PLAYERID, NAME, URL, COUNTRY, TEAM, RATING, RARITY FROM USERACC,INVENTORY,CARDS WHERE USERACC.USERNAME = INVENTORY.USERNAME AND INVENTORY.PLAYERID = CARDS.PLAYERID AND USERACC.USERNAME = ? ORDER BY RARITY ASC", new String[]{username});
        return c;
    }

    public Cursor AllCards(){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM CARDS";
        Cursor c = db.rawQuery(q,null);
        return c;
    }

    public Cursor ClickCards(String username,String PLAYERNAME){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT CARDS.ROWID,USERACC.USERNAME, CARDS.* FROM USERACC,INVENTORY,CARDS WHERE USERACC.USERNAME = INVENTORY.USERNAME AND INVENTORY.PLAYERID = CARDS.PLAYERID AND USERACC.USERNAME = ? AND CARDS.NAME = ? ORDER BY RARITY ASC", new String[]{username, PLAYERNAME});
        return c;
    }

    public Cursor ClickCardsAll(int position){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT CARDS.ROWID, CARDS.* FROM CARDS WHERE CARDS.ROWID = ? ORDER BY RARITY ASC", new String[]{String.valueOf(position)});
        return c;
    }

    public Cursor TradeAbleCards(String username,String playerid){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor c = db.rawQuery("SELECT * from INVENTORY INNER JOIN CARDS ON CARDS.PLAYERID = INVENTORY.PLAYERID AND USERNAME != ? AND CARDS.PLAYERID != ?", new String[]{username,playerid});
        Cursor c = db.rawQuery("SELECT * from INVENTORY INNER JOIN CARDS ON CARDS.PLAYERID = INVENTORY.PLAYERID AND USERNAME != ? AND INVENTORY.PLAYERID NOT IN (SELECT PLAYERID FROM INVENTORY WHERE USERNAME = ?)", new String[]{username,username});
        return c;
    }

    public boolean REQUESTTRADE(String FROMusername, String TOUSERNAME, String FROMPLAYERID, String TOPLAYERID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v1 = new ContentValues();
        v1.put("FROMUSERNAME",FROMusername);
        v1.put("TOUSERNAME",TOUSERNAME);
        v1.put("PLAYERID1",FROMPLAYERID);
        v1.put("PLAYERID2",TOPLAYERID);

        long result = db.insert("REQUESTTRADE",null,v1);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor TRADEREQUESTS(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("WITH T1 AS (SELECT REQUESTID,FROMUSERNAME,TOUSERNAME,PLAYERID1,PLAYERID2,NAME AS \"PLAYER1NAME\", PLAYERID1 AS \"PLAYER1ID\", URL AS \"PLAYER1URL\", COUNTRY AS \"PLAYER1COUNTRY\", TEAM AS \"PLAYER1TEAM\", RATING AS \"PLAYER1RATING\", PAC AS \"PLAYER1PAC\", SHO AS \"PLAYER1SHO\", PAS AS \"PLAYER1PAS\", DRI AS \"PLAYER1DRI\", DEF AS \"PLAYER1DEF\", PHY AS \"PLAYER1PHY\", RARITY AS \"PLAYER1RARITY\" FROM REQUESTTRADE, CARDS WHERE REQUESTTRADE.PLAYERID1 = CARDS.PLAYERID) SELECT * FROM T1, CARDS WHERE T1.PLAYERID2 = CARDS.PLAYERID AND TOUSERNAME = ? ", new String[]{username});
        return c;
    }

    public Cursor TRADEREQUESTSexpand(String username, String player1name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("WITH T1 AS (SELECT REQUESTID,FROMUSERNAME,TOUSERNAME,PLAYERID1,PLAYERID2,NAME AS \"PLAYER1NAME\", PLAYERID1 AS \"PLAYER1ID\", URL AS \"PLAYER1URL\", COUNTRY AS \"PLAYER1COUNTRY\", TEAM AS \"PLAYER1TEAM\", RATING AS \"PLAYER1RATING\", PAC AS \"PLAYER1PAC\", SHO AS \"PLAYER1SHO\", PAS AS \"PLAYER1PAS\", DRI AS \"PLAYER1DRI\", DEF AS \"PLAYER1DEF\", PHY AS \"PLAYER1PHY\", RARITY AS \"PLAYER1RARITY\" FROM REQUESTTRADE, CARDS WHERE REQUESTTRADE.PLAYERID1 = CARDS.PLAYERID) SELECT * FROM T1, CARDS WHERE T1.PLAYERID2 = CARDS.PLAYERID AND TOUSERNAME = ? and PLAYER1NAME = ?", new String[]{username,player1name});
        return c;
    }

    public boolean accepttrade(String FROMusername, String TOUSERNAME, String FROMPLAYERID, String TOPLAYERID){
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v1 = new ContentValues();
        v1.put("FROMUSERNAME",FROMusername);
        v1.put("TOUSERNAME",TOUSERNAME);
        v1.put("PLAYERID1",FROMPLAYERID);
        v1.put("PLAYERID2",TOPLAYERID);
        v1.put("TRADEDATE",date);
        long result = db.insert("TRADE",null,v1);

        //db.execSQL("DELETE FROM REQUESTTRADE WHERE TOUSERNAME = ? AND FROMPLAYERID = ? AND TOPLAYERID = ?", new String[]{FROMusername, FROMPLAYERID, TOPLAYERID});
        //db.execSQL("DELETE FROM INVENTORY WHERE USERNAME = ? AND PLAYERID = ?", new String[]{FROMusername, FROMPLAYERID});
        //db.execSQL("DELETE FROM INVENTORY WHERE USERNAME = ? AND PLAYERID = ?", new String[]{TOUSERNAME, TOPLAYERID});
        db.delete("INVENTORY","USERNAME = ? AND PLAYERID = ?",new String[]{TOUSERNAME, TOPLAYERID});
        db.delete("INVENTORY","USERNAME = ? AND PLAYERID = ?",new String[]{FROMusername, FROMPLAYERID});
        db.delete("REQUESTTRADE","FROMUSERNAME = ? AND PLAYERID1 = ? AND PLAYERID2 = ?",new String[]{FROMusername, FROMPLAYERID, TOPLAYERID});

        db.execSQL("INSERT INTO INVENTORY(USERNAME, PLAYERID) VALUES (?,?)", new String[]{FROMusername, TOPLAYERID});
        db.execSQL("INSERT INTO INVENTORY(USERNAME, PLAYERID) VALUES (?,?)", new String[]{TOUSERNAME, FROMPLAYERID});

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void declinetrade(String FROMusername, String FROMPLAYERID, String TOPLAYERID){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM REQUESTTRADE WHERE FROMUSERNAME = ? AND PLAYERID1 = ? AND PLAYERID2 = ?", new String[]{FROMusername, FROMPLAYERID, TOPLAYERID});

    }

    public boolean buy(String username, String upi, int no, int amount){
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v1 = new ContentValues();
        v1.put("USERNAME",username);
        v1.put("UPIID",upi);
        v1.put("TRANSACTIONDATE",date);
        v1.put("Amount",amount);
        v1.put("NOOFCARDS",no);

        long result = db.insert("BUY",null,v1);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public Cursor transactionsbuy(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM BUY WHERE USERNAME = ?", new String[]{username});
        return c;
    }

    public Cursor randomcards(String username, String no){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * from cards where PLAYERID not in (SELECT PLAYERID from INVENTORY where USERNAME = ?) ORDER BY RANDOM() LIMIT ?;", new String[]{username, no});
        return c;
    }
    public void insertrandom(String username,String playerid){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO INVENTORY(USERNAME, PLAYERID) VALUES (?,?)", new String[]{username, playerid});

    }


}
