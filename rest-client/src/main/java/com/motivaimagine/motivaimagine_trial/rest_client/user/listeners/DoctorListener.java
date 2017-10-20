package com.motivaimagine.motivaimagine_trial.rest_client.user.listeners;

import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Doctor;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;

import java.util.ArrayList;

/**
 * Created by gpaez on 10/20/2017.
 */

public interface DoctorListener {

    void onDoc_ListStart();

    void onDoc_ListCompleted(ArrayList<Doctor> doctors);

    void onDoc_ListError(Error error);
}
