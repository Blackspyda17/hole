package com.motivaimagine.motivaimagine_trial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.motivaimagine.motivaimagine_trial.rest_client.user.Controller;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;

public class MainActivity extends AppCompatActivity {
    private String METHOD = "E";

    private GoogleHelper mgoogle;
    private DB mydb;
    User user_re=null;
    public ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        progressDialog=new ProgressDialog(MainActivity.this,
                R.style.AppThemeMain2_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Validating, please wait...");


        mydb = new DB(this);
        if (mydb.numberOfRows() > 0) {
            user_re = mydb.getUser();
            if (user_re != null) {

                if(CheckNetwork.isInternetAvailable(this))  //if connection available
                {
                    comprove_data(user_re);
                }else{
                    Dialogo.messageDialog(this);
                }

            }
        }


        Button patient = (Button) findViewById(R.id.btn_patient);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(CheckNetwork.isInternetAvailable(MainActivity.this))  //if connection available
                {
                    Intent doctores = new Intent(getApplicationContext(), doctor_list.class);
                    startActivity(doctores);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                }else{
                    Dialogo.messageDialog(MainActivity.this,"Network Connection","No Internet Connection");
                }
            }
        });


  /*      Button doctor = (Button) findViewById(R.id.btn_doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CheckNetwork.isInternetAvailable(MainActivity.this))  //if connection available
                {
                LoginActivity.createInstance(MainActivity.this, 2);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }else{
                    Dialogo.messageDialog(MainActivity.this,"Network Connection","No Internet Connection");
                }
            }
        });*/

        Button Login = (Button) findViewById(R.id.btn_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckNetwork.isInternetAvailable(MainActivity.this))  //if connection available
                {
                LoginActivity.createInstance(MainActivity.this, 0);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }else{
                    Dialogo.messageDialog(MainActivity.this,"Network Connection","No Internet Connection");
                }
            }
        });

    }

    private void comprove_data(User user) {
        request_initlog(MainActivity.this,user.getEmail(),null,null,user.getApp_token());

  /*      FacebookSdk.sdkInitialize(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        if(googleApiClient == null || !googleApiClient.isConnected()){
            try {
                googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                        .enableAutoManage(MainActivity.this *//* FragmentActivity *//*, this.mgoogle *//* OnConnectionFailedListener *//*)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if (AccessToken.getCurrentAccessToken() != null) {
            METHOD = "F";


        } else if (opr.isDone()) {
            METHOD = "G";
            request_initlog(MainActivity.this,email,null,opr.get().getSignInAccount().getIdToken(),app_token);

        } else {
            request_initlog(MainActivity.this,email,null,null,app_token);
        }
*/

    }

/*
    private void goMain2Screen(String auth, User user) {
        Main2Activity.createInstance(MainActivity.this, 1, auth, user);
    }*/

    public void request_initlog(Context context, String email, String password, String token, String apptoken) {
        Controller.getInstance().login(context, email, password, METHOD, token, apptoken, new MainActivity.InitCallback());

    }


    class InitCallback implements LoginListener {

        @Override
        public void onLoginStart() {
            progressDialog.show();
        }

        @Override
        public void onLoginCompleted(User user) {
            mydb = new DB(MainActivity.this);
            if (mydb.updateUser(user.getId(), user.getName(), user.getLastname(), user.getCountry_id(), user.getApp_token(), user.getType(), user.getEmail(), user.getDoctor_id(),user_re.getMethod(),user_re.getPicture())) {
              User updt_user=mydb.getUser();
                progressDialog.dismiss();
                Main2Activity.createInstance(MainActivity.this, updt_user.getType(),user);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

        }

        @Override
        public void onLoginError(Error message) {
            mydb = new DB(MainActivity.this);
            if(message.getCode().equals("100")){
                progressDialog.dismiss();
                mydb.deleteUser(mydb.getIDFUser());
            }else{
                progressDialog.dismiss();
                mydb.deleteUser(mydb.getIDFUser());
                Snackbar snackbar = Snackbar
                        .make(getCurrentFocus(),"Error , please try ton Sign in Again", Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.Dissmiss), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        if(!CheckNetwork.isInternetAvailable(this))  //if connection available
        {
            finish();
        }else {
            super.onBackPressed();
        }

    }
}