package com.motivaimagine.motivaimagine_trial;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;

/**
 * Created by gpaez on 7/26/2017.
 */

public class MyStepperAdapter extends AbstractFragmentStepAdapter {
    private ArrayList<Step> _fragments;

    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
        this._fragments = new ArrayList<Step>();
    }



    public void add(Step fragment) {
        this._fragments.add(fragment);
    }

    @Override
    public Step createStep(int position) {
        return _fragments.get(position);
    }




    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return new StepViewModel.Builder(context)
                .setTitle("") //can be a CharSequence instead
                .create();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


}

