package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gpaez on 8/22/2017.
 */


public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int tipo_persona=0;
    String dob;
    private static final String PERSON = "TIPO";

    private  int MILLIS_IN_SECOND = 1000;
    private int SECONDS_IN_MINUTE = 60;
    private int MINUTES_IN_HOUR = 60;
    private int HOURS_IN_DAY = 24;
    private int DAYS_IN_YEAR = 365;
    private long MILLISECONDS_IN_YEAR = (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_YEAR;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (getArguments() != null) {
            tipo_persona = getArguments().getInt(PERSON);
        }

        Calendar calendar = Calendar.getInstance();
        if (tipo_persona>0 && tipo_persona==1) {
                    calendar.setTimeInMillis(System.currentTimeMillis() - (MILLISECONDS_IN_YEAR * 18));
            }
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
        if (tipo_persona>0) {
            switch (tipo_persona) {
                case 1:
                    dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - (MILLISECONDS_IN_YEAR * 18));
                    dialog.getDatePicker().setMinDate(System.currentTimeMillis() - (MILLISECONDS_IN_YEAR * 60));
                    break;
                case 2:
                    dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                    dialog.getDatePicker().setMinDate(System.currentTimeMillis() - (MILLISECONDS_IN_YEAR * 5));
                    break;
            }
        }



        return dialog;
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        dob= month+"/"+day+"/"+year;
        Intent i = new Intent();
        i.putExtra("selectedDate",dob);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);

    }


    public static SelectDateFragment newInstance(int tipo) {
        SelectDateFragment fragment = new SelectDateFragment();
        Bundle args = new Bundle();
        args.putSerializable(PERSON,tipo);
        fragment.setArguments(args);
        return fragment;
    }








}