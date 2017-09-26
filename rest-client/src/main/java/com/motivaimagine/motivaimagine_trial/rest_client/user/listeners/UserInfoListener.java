package com.motivaimagine.motivaimagine_trial.rest_client.user.listeners;

import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;

/**
 * Created by gpaez on 9/21/2017.
 */

public interface UserInfoListener {
    void onUserInfoStart();
    void onUserInfoCompleted(User user);
    void onUserInfoError(String message);
}

