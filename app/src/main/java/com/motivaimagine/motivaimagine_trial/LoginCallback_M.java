package com.motivaimagine.motivaimagine_trial;

import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;

/**
 * Created by gpaez on 10/13/2017.
 */

public class LoginCallback_M {

    public LoginCallback_M(){
        this.listener = null;
    }


    public void setLoginCallback_MListener(LoginListener listener) {
        this.listener = listener;
    }
    private LoginListener listener;

}
