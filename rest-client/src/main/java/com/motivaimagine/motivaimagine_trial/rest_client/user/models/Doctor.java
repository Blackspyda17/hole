package com.motivaimagine.motivaimagine_trial.rest_client.user.models;

/**
 * Created by gpaez on 10/20/2017.
 */

public class Doctor {
    private int id;
    private String name;


    public Doctor(int id, String name) {
        this.setId(id);
        this.setName(name);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
