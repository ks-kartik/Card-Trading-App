package com.example.tradingapp;

public class StaticRv {

    int image;
    String name;
    int pos;

    public StaticRv(int image, String name,int pos) {
        this.image = image;
        this.name = name;
        this.pos = pos;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    public int getPos() {
        return pos;
    }
}
