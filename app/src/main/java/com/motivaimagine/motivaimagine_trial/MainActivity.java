package com.motivaimagine.motivaimagine_trial;

import android.content.Context;
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
import com.motivaimagine.motivaimagine_trial.rest_client.user.UserController;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;

public class MainActivity extends AppCompatActivity {
    private String METHOD = "E";
    private GoogleApiClient googleApiClient;
    private GoogleHelper mgoogle;
    private DB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mydb = new DB(this);
        if (mydb.numberOfRows() > 0) {
            User user = mydb.getUser();
            if (user != null) {
                comprove_data(user.getEmail(), user.getApp_token());
            }
        }


        Button patient = (Button) findViewById(R.id.btn_patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctores = new Intent(getApplicationContext(), doctor_list.class);
                startActivity(doctores);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


        Button doctor = (Button) findViewById(R.id.btn_doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.createInstance(MainActivity.this, 2);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Button Login = (Button) findViewById(R.id.btn_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.createInstance(MainActivity.this, 0);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    private void comprove_data(String email, String app_token) {
        FacebookSdk.sdkInitialize(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        if(googleApiClient == null || !googleApiClient.isConnected()){
            try {
                googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                        .enableAutoManage(MainActivity.this /* FragmentActivity */, this.mgoogle /* OnConnectionFailedListener */)
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


    }


    private void goMain2Screen(String auth, User user) {
        Main2Activity.createInstance(MainActivity.this, 1, auth, user);
    }

    public void request_initlog(Context context, String email, String password, String token, String apptoken) {
        UserController.getInstance().login(context, email, password, METHOD, token, apptoken, new MainActivity.InitCallback());

    }


    class InitCallback implements LoginListener {

        @Override
        public void onLoginStart() {

        }

        @Override
        public void onLoginCompleted(User user) {
            mydb = new DB(MainActivity.this);
            if (mydb.updateUser(user.getId(), user.getName(), user.getLastname(), user.getCountry_id(), user.getApp_token(), user.getType(), user.getEmail(), user.getDoctor_id())) {
                Main2Activity.createInstance(MainActivity.this, 1, METHOD, user);
            }
        }

        @Override
        public void onLoginError(Error message) {
            mydb = new DB(MainActivity.this);
            if(message.getCode().equalsIgnoreCase("100")){
                mydb.deleteUser(mydb.getIDFUser());
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(googleApiClient!=null){
            googleApiClient.stopAutoManage(MainActivity.this);
            googleApiClient.disconnect();
        }
    }
}