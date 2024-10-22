package com.unicorn.sg.demo.domain;

import java.util.Random;

public class Border {
    Country inBorder;
    Country outBorder;

    public Border(Country inBorder, Country outBorder) {
        this.inBorder = inBorder;
        this.outBorder = outBorder;
    }
    public Border(){
        Random r = new Random();
        this.inBorder = Country.values()[r.nextInt(Country.values().length)];
        this.outBorder = Country.values()[r.nextInt(Country.values().length)];

    }

    public Border(String inCountryCode, String outCountryCode) {
        Random r = new Random();
        try{
            this.inBorder = Country.valueOf(inCountryCode);
        }
        catch (IllegalArgumentException| NullPointerException e){
            this.inBorder = Country.values()[r.nextInt(Country.values().length)];
        }
        try{
            this.outBorder = Country.valueOf(outCountryCode);
        }
        catch (IllegalArgumentException| NullPointerException e){
            this.outBorder = Country.values()[r.nextInt(Country.values().length)];
        }
    }

    public Country getInBorder() {
        return inBorder;
    }

    public Country getOutBorder() {
        return outBorder;
    }
}
