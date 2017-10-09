package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class forgot_pass extends AppCompatActivity {
    private static final String OPCION = "Opc";
    private int User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Bundle x = this.getIntent().getExtras();
        if (x != null) {
            User= x.getInt(OPCION);
        }
    }



        @Override
    public void onBackPressed() {
            LoginActivity.createInstance(forgot_pass.this,User);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void createInstance(Activity activity, int usuario) {
        Intent intent = getLaunchIntent(activity, usuario);
        activity.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, int usuario) {
        Intent intent = new Intent(context, forgot_pass.class);
        intent.putExtra(OPCION,usuario);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
