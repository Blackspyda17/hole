package com.motivaimagine.motivaimagine_trial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.StepperLayout;

public class Reg_Implant extends Fragment {
    private MyStepperAdapter _adapter;
    static StepperLayout mStepperLayout;
    static implant my_implant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view=inflater.inflate(R.layout.fragment_reg_implant, container, false);
        mStepperLayout = (StepperLayout) view.findViewById(R.id.stepperLayout);
        my_implant=new implant();
        this._adapter= new MyStepperAdapter(getChildFragmentManager(), this.getContext());
        this._adapter.add(new implant_regist_1());
        this._adapter.add(new implant_regist_2());
        this._adapter.add(new implant_regist_4());
        mStepperLayout.setAdapter(this._adapter);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void incializar(){
        mStepperLayout.setAdapter(new MyStepperAdapter(getFragmentManager(), this.getContext()));
    }





}
