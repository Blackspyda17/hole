package com.motivaimagine.motivaimagine_trial;

/**
 * Created by gpaez on 8/18/2017.
 */

public class Country {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private int id;



}
