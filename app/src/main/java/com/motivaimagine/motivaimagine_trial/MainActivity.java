package com.motivaimagine.motivaimagine_trial;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;

public class MainActivity extends AppCompatActivity {

    private GoogleApiClient googleApiClient;
    private GoogleHelper mgoogle;
    private DB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(this);
        if (AccessToken.getCurrentAccessToken() != null) {
           goMain2Screen("f",null);
        }

        mydb=new DB(this);
        User user=null;
        try {
           user= mydb.getUser();
        }catch (Exception e){

        }
        if(user!=null){
            goMain2Screen("n",user);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, mgoogle)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            goMain2Screen("g",null);
        }


        Button patient = (Button) findViewById(R.id.btn_patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent doctores = new Intent(getApplicationContext(),doctor_list.class);
                    startActivity(doctores);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });



        Button doctor= (Button) findViewById(R.id.btn_doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.createInstance(MainActivity.this,2);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Button Login= (Button) findViewById(R.id.btn_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.createInstance(MainActivity.this,0);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    private void goMain2Screen(String auth,User user) {
        Main2Activity.createInstance(MainActivity.this,1,auth,user);
    }





}
