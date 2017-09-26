package com.motivaimagine.motivaimagine_trial;



/**
 * Created by gpaez on 9/20/2017.
 */

public class User {


    int id;
    String token;
    int type;
    int type_id;
    String name;



    String lastname;
    String email;
    String picture;



    public User(int id, String token, int type,int type_id, String name, String lastname, String email, String picture) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.type_id=type_id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
