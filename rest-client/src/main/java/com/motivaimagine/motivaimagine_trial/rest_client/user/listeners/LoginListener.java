package com.motivaimagine.motivaimagine_trial.rest_client.user.listeners;

/**
 * Created by gpaez on 9/21/2017.
 */

public interface LoginListener {

    void onLoginStart();
    void onLoginCompleted(int userId, String token);
    void onLoginError(String message);

}
