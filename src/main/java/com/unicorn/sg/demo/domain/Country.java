package com.unicorn.sg.demo.domain;

import java.io.Serializable;

public enum Country implements Serializable {
    FR("fr","France"),
    IT("it","Italy"),
    AT("at", "Austria"),
    A("at", "Austria"),
    DAL("de", "Germany"),
    D("de", "Germany"),
    CH("ch", "Switzerland");


    private String code;
    private String name;


    Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Country findByName(){
        return Country.valueOf(name);
    }

}
