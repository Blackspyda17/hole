package layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.motivaimagine.motivaimagine_trial.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

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
AlertDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_implant_regist_4, container, false);

        ButterKnife.bind(this,v);

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
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                campo_SN(_til_left_SN,_left_serial,String.valueOf(s));

            }

            @Override
            public void afterTextChanged(Editable s) {

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
        return null;
    }

    @Override
    public void onSelected() {

    }



    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public void campo_SN(TextInputLayout til,EditText edt,String text){
        if(text.length()>0){

        if(text.charAt(0)=='1'|| text.charAt(0)=='e'){
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
        }

    }

    public void setEditTextMaxLength(EditText edt,int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        edt.setFilters(filterArray);
    }
}
