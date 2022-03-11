package com.example.tradingapp;



public class DynamicRVModel {

    String name;
    int pos;
    String rating, rarity,country, team, url;

    public DynamicRVModel(String name, String rating, String rarity, String country, String team,String url, int pos) {
        this.name = name;
        this.rating = rating;
        this.rarity = rarity;
        this.country = country;
        this.team = team;
        this.url = url;
        this.pos = pos;
    }


    public String getRarity() {
        return rarity;
    }

    public String getCountry() {
        return country;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getPos() {
        return pos;
    }

    public String getRating() {
        return rating;
    }
}
