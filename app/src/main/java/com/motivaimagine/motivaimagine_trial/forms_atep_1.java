package com.motivaimagine.motivaimagine_trial;

import android.support.v7.app.AppCompatActivity;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;

public class forms_atep_1 extends AppCompatActivity  {
    private VerticalStepperFormLayout verticalStepperForm;
    // Information about the steps/fields of the form
/*
    private static final int SWB_STEP_NUM = 0;
    private static final int PWB_STEP_NUM = 1;
    private static final int PWB_CHEST_STEP_NUM = 2;
    private static final int SXWB_STEP_NUM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_atep_1);




    }




    private void initializeActivity() {


        // Vertical Stepper form vars
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
        String[] stepsTitles = getResources().getStringArray(R.array.steps_titles);
        //String[] stepsSubtitles = getResources().getStringArray(R.array.steps_subtitles);

        // Here we find and initialize the form
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, stepsTitles, this, this)
                //.stepsSubtitles(stepsSubtitles)
                //.materialDesignInDisabledSteps(true) // false by default
                //.showVerticalLineWhenStepsAreCollapsed(true) // false by default
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true)
                .init();
    }


    @Override
    public View createStepContentView(int stepNumber) {
        // Here we generate the content view of the correspondent step and we return it so it gets
        // automatically added to the step layout (AKA stepContent)
        View view = null;
        switch (stepNumber) {
            case SWB_STEP_NUM:
                view = createAlarmTitleStep();
                break;
            case PWB_STEP_NUM:
                view = createAlarmDescriptionStep();
                break;
            case PWB_CHEST_STEP_NUM:
                view = createAlarmTimeStep();
                break;
            case SXWB_STEP_NUM:
                view = createAlarmDaysStep();
                break;
        }
        return view;
    }
*/


}
