package com.motivaimagine.motivaimagine_trial.rest_client.user.models;

import java.io.Serializable;

/**
 * Created by gpaez on 9/21/2017.
 */

public class User  implements Serializable {
    private int id;
    private String name;
    private String lastname;
    private int country_id;
    private String app_token;
    private int type;
    private String email;
    private int doctor_id;


    public User(int id, String name, String lastname, int country_id, String app_token, int type, String email,int doctor_id) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.country_id = country_id;
        this.app_token = app_token;
        this.type = type;
        this.email=email;
        this.doctor_id=doctor_id;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
}

