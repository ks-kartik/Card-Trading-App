package com.example.tradingapp;



public class DynamicRVModelTrade {

    String name;
    int pos;
    String rating, rarity,country, team, url, username, playerid;

    public DynamicRVModelTrade(String name, String rating, String rarity, String country, String team, String url, int pos, String username,String playerid) {
        this.name = name;
        this.rating = rating;
        this.rarity = rarity;
        this.country = country;
        this.team = team;
        this.url = url;
        this.pos = pos;
        this.username = username;
        this.playerid = playerid;
    }


    public String getRarity() {
        return "Rarity - " + rarity;
    }

    public String getCountry() {
        return "Country - "+ country;
    }

    public String getTeam() {
        return "Team- "+team;
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
        return "Rating - " + rating;
    }

    public String getPlayerid() {
        return playerid;
    }

    public String getUsername() {
        return username;
    }
}
