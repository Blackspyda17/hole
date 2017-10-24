package com.motivaimagine.motivaimagine_trial.rest_client.user.listeners;

import com.motivaimagine.motivaimagine_trial.rest_client.user.models.implant_info;

import java.util.ArrayList;

/**
 * Created by gpaez on 10/24/2017.
 */

public interface ImplantListener {

    void onImplantRegistrationStart();
    void onImplantRegistrationCompleted(ArrayList<implant_info> implants);
    void onImplantRegistrationError(Error error);

}
