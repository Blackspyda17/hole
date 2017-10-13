package com.motivaimagine.motivaimagine_trial.rest_client.user.listeners;

import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;

/**
 * Created by gpaez on 9/21/2017.
 */

public interface LoginListener {

    void onLoginStart();
    void onLoginCompleted(User user);
/*    void onLoginCompleted(int userId, String name, String lastname,String email,int type,int type_id,String method, String token,String apptoken, String picture, int doctor_id);*/
    void onLoginError(Error error);

}
