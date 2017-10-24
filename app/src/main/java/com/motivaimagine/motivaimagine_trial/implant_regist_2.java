package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class implant_regist_2 extends Fragment implements Step {

    public static final int DATEPICKER_FRAGMENT = 1;
    @BindView(R.id.edt_date)
    TextView _date;
  /*  @BindView(R.id.actv_doct)
    AutoCompleteTextView _doc_name;
    String[] doctors = {"Gerardo Mora", "Gerson Rodriguez", "Gilberth Andres Paez Ortiz", "jorge mayorga",};*/
    private int sMonth, sYear, sDay;
    static final int DATE_ID = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_implant_regist_2, container, false);
        ButterKnife.bind(this, v);
        //initialize your UI
        Calendar C = Calendar.getInstance();
        sYear = C.get(Calendar.YEAR);
        sMonth = C.get(Calendar.MONTH);
        sDay = C.get(Calendar.DAY_OF_MONTH);
        _date.setText("" + FechaAct());


        _date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = SelectDateFragment.newInstance(2);
                picker.setTargetFragment(implant_regist_2.this, DATEPICKER_FRAGMENT);
                picker.show(getFragmentManager().beginTransaction(), "Date Picker");


            }
        });

        //Creating the instance of ArrayAdapter containing list of fruit names
/*        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this.getContext(), android.R.layout.select_dialog_item, doctors);
        //Getting the instance of AutoCompleteTextView
        _doc_name = (AutoCompleteTextView) v.findViewById(R.id.actv_doct);
        _doc_name.setThreshold(4);//will start working from first character
        _doc_name.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView*/

        return v;
    }

    @Override
    public VerificationError verifyStep() {
        //return null if the user can go to the next step, create a new VerificationError instance otherwise
        Reg_Implant.my_implant.setSugery_date(_date.getText().toString());
        return null;
    }


    @Override
    public void onSelected() {
        //update UI when selected
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }


    public String FechaAct() {


        String dia = "";
        String mes = "";


        if (sDay < 10) {
            dia = "0" + sDay;
        } else {
            dia = "" + sDay;
        }

        if (sMonth < 10) {
            mes = "0" + sMonth;
        } else {
            mes = "" + sMonth;
        }


        return dia + "-" + mes + "-" + sYear;


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DATEPICKER_FRAGMENT:
                if (resultCode == Activity.RESULT_OK) {
                    // here the part where I get my selected date from the saved variable in the intent and the displaying it.
                    Bundle bundle = data.getExtras();
                    String resultDate = bundle.getString("selectedDate", "error");
                    _date.setText(resultDate);
                }
                break;
        }
    }
}


