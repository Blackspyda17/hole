package com.motivaimagine.motivaimagine_trial;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;

public class implant_regist_1 extends Fragment implements Step{


    @BindView(R.id.rb_breast) RadioButton _typ_breast;
    @BindView(R.id.rg_q) RadioGroup _opt_q;
    @BindView(R.id.rb_1) RadioButton _rb_1;
    @BindView(R.id.rb_2) RadioButton _rb_2;
    @BindView(R.id.rb_left) RadioButton _side_l;
    @BindView(R.id.rb_right) RadioButton _side_r;
    @BindView(R.id.rg_side) RadioGroup _opt_side;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_implant_regist_1, container, false);
        ButterKnife.bind(this,v);
        _opt_q.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.rb_1:
                        if(_rb_1.isChecked()){
                            showOpt(true);
                        }

                        break;
                    case R.id.rb_2:
                        if(_rb_2.isChecked()){
                            showOpt(false);
                        }
                        break;
                }
            }
        });

        //initialize your UI

        return v;
    }


    private void showOpt(boolean b) {
        _opt_side.setVisibility(b ? View.VISIBLE : View.GONE);
    }


    @Override
    public VerificationError verifyStep() {
        //return null if the user can go to the next step, create a new VerificationError instance otherwise
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

}
