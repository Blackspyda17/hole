package com.motivaimagine.motivaimagine_trial;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.motivaimagine.motivaimagine_trial.rest_client.user.Controller;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.DoctorListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Doctor;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class implant_regist_4 extends Fragment implements Step {
    @BindView(R.id.img_help) ImageView _img_help1;
    @BindView(R.id.img_help2) ImageView _img_help2;
    @BindView(R.id.til_left_implant) TextInputLayout _til_left_SN;
    @BindView(R.id.til_right_implant) TextInputLayout _til_right_SN;
    @BindView(R.id.edt_left_serial) EditText _left_serial;
    @BindView(R.id.edt_left_vcode) EditText _left_vcode;
    @BindView(R.id.edt_right_serial) EditText _right_serial;
    @BindView(R.id.edt_right_vcode) EditText _right_vcode;
    @BindView(R.id.ln_left) LinearLayout _ln_left;
    @BindView(R.id.ln_right) LinearLayout _ln_right;
    private  boolean left=true;
    private  boolean right=true;
    private Doctor doctor;
    AlertDialog dialog;
    private User usuario;
    private String blockCharacterSet = "~#^|$%&*!";
    private InputFilter filter = new InputFilter() {


        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_implant_regist_4, container, false);
        ButterKnife.bind(this,v);
        DB mydb=new DB(getContext());
        usuario = mydb.getUser();
        doctor();
        _left_serial.setFilters(new InputFilter[] { filter });


        if(Reg_Implant.my_implant.getAmount()==1) {
           if(Reg_Implant.my_implant.getImplantL().equals("side")){
                right=false;
               _ln_right.setVisibility(View.GONE);
            }else {
                left=false;
               _ln_left.setVisibility(View.GONE);
           }
        }

        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
        View mView =getActivity().getLayoutInflater().inflate(R.layout.dialog_help,null);
        Button mOK=(Button) mView.findViewById(R.id.btn_ok);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        mBuilder.setView(mView);
        dialog=mBuilder.create();


        _left_serial.addTextChangedListener(new TextWatcher() {

            boolean hyphenExists;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() >= 9 && s.charAt(8) == '-') {
                    hyphenExists = true;
                } else {
                    hyphenExists = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                campo_SN(_til_left_SN,_left_serial,String.valueOf(s));

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 8) {
                    if (!hyphenExists)
                        s.append('-');
                }
            }
        });

        _right_serial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
         
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




                campo_SN(_til_right_SN,_right_serial,String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _img_help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 dialog.show();
            }
        });

        _img_help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
            }
        });


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public VerificationError verifyStep() {
if(validate_implants()) {

    return null;
}else {
    return new VerificationError("Empty text fields");
}

    }

    @Override
    public void onSelected() {

    }



    @Override
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(getContext(), " " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    public void campo_SN(TextInputLayout til,EditText edt,String text){
        if(text.length()>0){

        if(text.charAt(0)=='1'|| text.charAt(0)=='e'|| text.charAt(0)=='E'){
         til.setErrorTextAppearance(R.style.Success);
            til.setError("SN");
            til.setCounterEnabled(true);
            til.setCounterMaxLength(11);
            setEditTextMaxLength(edt,11);
        } else if(text.charAt(0)=='9'){
            til.setErrorTextAppearance(R.style.Success);
            til.setError("ESN");
            til.setCounterEnabled(true);
            til.setCounterMaxLength(15);
            setEditTextMaxLength(edt,15);
            edt.setInputType(InputType.TYPE_CLASS_NUMBER);

        }else {
            til.setErrorTextAppearance(R.style.Error);
            til.setError("SN/ESN");
            til.setCounterEnabled(false);
        }

        }
        if(text.length()<1) {
            til.setErrorTextAppearance(R.style.Error);
            til.setError("SN/ESN");
            til.setCounterEnabled(false);
            edt.setInputType(InputType.TYPE_CLASS_TEXT);
        }

    }

    public void setEditTextMaxLength(EditText edt,int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        edt.setFilters(filterArray);
    }

    public  boolean validate_implants() {


        boolean valid = true;
        String left_serial = _left_serial.getText().toString();
        String right_serial = _right_serial.getText().toString();
        String left_vcode = _left_vcode.getText().toString();
        String right_vcode = _right_vcode.getText().toString();


            if (left && left_serial.isEmpty()) {
                _left_serial.setError("at least 11 characters");
                valid = false;
            } else {
                _left_serial.setError(null);
            }
            if (left && left_vcode.isEmpty()) {
                _left_vcode.setError("at least 1 characters");
                valid = false;
            }
            if (right && right_serial.isEmpty()) {
                _right_serial.setError("at least 11 characters");
                valid = false;
            } else {
                _right_serial.setError(null);
            }
            if (right && right_vcode.isEmpty()) {
                _right_vcode.setError("at least 1 characters");
                valid = false;
            } else {
                _right_vcode.setError(null);
            }


        return valid;
    }

    public void doctor(){

        Controller.getInstance().getDoctorList(this.getContext(), new DoctorListener() {
            @Override
            public void onDoc_ListStart() {

            }

            @Override
            public void onDoc_ListCompleted(ArrayList<Doctor> doctors) {

                int targetID = usuario.getDoctor_id();
                Doctor result = null;
                for (Doctor c : doctors) {
                    if (targetID==c.getId()) {
                        result = c;
                        break;
                    }
                }
                doctor=result;
                System.out.println("dato enviadoooooooo = "+doctor.getId());
            }

            @Override
            public void onDoc_ListError(Error error) {
                System.out.println("dato enviadoooooooo = "+error.getCode());
            }
        });


    }




}
