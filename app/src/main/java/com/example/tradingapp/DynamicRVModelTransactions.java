package com.example.tradingapp;



public class DynamicRVModelTransactions {


    String id, no,upi, date, amt, username;

    public DynamicRVModelTransactions(String id, String no, String upi, String date, String amt, String username) {
        this.id = id;
        this.no = no;
        this.upi = upi;
        this.date = date;
        this.amt = amt;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public String getUpi() {
        return upi;
    }

    public String getDate() {
        return date;
    }

    public String getAmt() {
        return amt;
    }

    public String getUsername() {
        return username;
    }
}
