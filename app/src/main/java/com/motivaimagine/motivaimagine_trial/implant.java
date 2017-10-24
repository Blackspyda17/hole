package com.motivaimagine.motivaimagine_trial;

import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Doctor;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;

/**
 * Created by gpaez on 10/23/2017.
 */

public class implant {


    private User user_regist;
    private Doctor doctor;
    private String sugery_date;
    private String implantL;

    public implant(User user_regist, Doctor doctor, String sugery_date, String implantL, String validationL, String implantR, String validationR, int amount) {

        this.user_regist = user_regist;
        this.doctor = doctor;
        this.sugery_date = sugery_date;
        this.implantL = implantL;
        this.validationL = validationL;
        this.implantR = implantR;
        this.validationR = validationR;
        this.amount = amount;
    }

    public implant() {
        this.user_regist = null;
        this.doctor = null;
        this.sugery_date = null;
        this.implantL = null;
        this.validationL = null;
        this.implantR = null;
        this.validationR = null;
        this.amount = 2;
    }

    private String validationL;
    private String implantR;
    private String validationR;
    private int amount;


    public User getUser_regist() {
        return user_regist;
    }

    public void setUser_regist(User user_regist) {
        this.user_regist = user_regist;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getSugery_date() {
        return sugery_date;
    }

    public void setSugery_date(String sugery_date) {
        this.sugery_date = sugery_date;
    }

    public String getImplantL() {
        return implantL;
    }

    public void setImplantL(String implantL) {
        this.implantL = implantL;
    }

    public String getValidationL() {
        return validationL;
    }

    public void setValidationL(String validationL) {
        this.validationL = validationL;
    }

    public String getImplantR() {
        return implantR;
    }

    public void setImplantR(String implantR) {
        this.implantR = implantR;
    }

    public String getValidationR() {
        return validationR;
    }

    public void setValidationR(String validationR) {
        this.validationR = validationR;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
