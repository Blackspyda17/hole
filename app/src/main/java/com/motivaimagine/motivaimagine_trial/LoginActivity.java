package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.motivaimagine.motivaimagine_trial.rest_client.user.UserController;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.mukeshsolanki.sociallogin.facebook.FacebookHelper;
import com.mukeshsolanki.sociallogin.facebook.FacebookListener;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements FacebookListener,GoogleListener {
    private static final String OPCION = "Opc";
    private String[] PERMISSION = new String[]{"public_profile", "email"};
    private int User;
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private FacebookHelper mFacebook;
    private GoogleHelper mGoogle;
    private DB mydb;
    private String METHOD = "E";
    @BindView(R.id.or_layout) RelativeLayout _or;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    @BindView(R.id.link_resetpass) TextView _resetpass;
    @BindView(R.id.btn_fb_login) Button _fb_login;
    @BindView(R.id.btn_google) Button _gog_login;
    public ProgressDialog progressDialog ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FacebookSdk.setApplicationId(getResources().getString(R.string.app_id));
        FacebookSdk.sdkInitialize(this);
        mFacebook=new FacebookHelper(LoginActivity.this);
        mGoogle = new GoogleHelper(this, this, null);
        Bundle x = this.getIntent().getExtras();
        if (x != null) {
           User= x.getInt(OPCION);
            switch (User){
                case 2:
                    _fb_login.setVisibility(View.GONE);
                    _gog_login.setVisibility(View.GONE);
                    _signupLink.setVisibility(View.GONE);
                    _or.setVisibility(View.GONE);
                    break;
            }
        }

//FACEBOOK SIG IN

        _fb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFacebook.performSignIn(LoginActivity.this);


            }
        });
//GOOGLE SIG IN

        _gog_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mGoogle.performSignIn(LoginActivity.this);

            }
        });
//EMAIL SIG IN

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        /*SIGN UP PROCESS*/

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        /*RESET PASSWORD PROCESS*/

        _resetpass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                forgot_pass.createInstance(LoginActivity.this,User);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

        /*Login process*/

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
      //  _loginButton.setEnabled(false);

        progressDialog=new ProgressDialog(LoginActivity.this,
        R.style.AppThemeMain2_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.


        requestLogin(email,password,"E",null,null);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                    //    onLoginSuccess();
                        // onLoginFailed();

                    }
                }, 3000);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebook.onActivityResult(requestCode, resultCode, data);
        mGoogle.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        goMainScreen();
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

        /*Authorization process*/
    private void requestLogin(String username, String password,String method, String token,String apptoken ){
        UserController.getInstance().login(this,username,password,method,token,apptoken,new LoginCallback());
    }




    public static void createInstance(Activity activity, int usuario) {
        Intent intent = getLaunchIntent(activity, usuario);
        activity.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, int usuario) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(OPCION,usuario);
        return intent;
    }



    @Override
    public void onFbSignInFail(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFbSignInSuccess(String authToken, String userId) {
        Main2Activity.createInstance(LoginActivity.this,1,"F",null);

    }

    @Override
    public void onFBSignOut() {
        Toast.makeText(getApplicationContext(),"Sing Out!!!!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onGoogleAuthSignIn(String s, String s1) {
        Main2Activity.createInstance(LoginActivity.this,1,"G",null);
    }

    @Override
    public void onGoogleAuthSignInFailed(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignOut() {
        Toast.makeText(getApplicationContext(),"Sing Out!!!!",Toast.LENGTH_SHORT).show();
    }





    class LoginCallback implements LoginListener {

        @Override
        public void onLoginStart() {
            progressDialog.show();
        }

        @Override
        public void onLoginCompleted(User user) {
            mydb = new DB(LoginActivity.this);
            if( mydb.insertUser(user.getId(),user.getName(),user.getLastname(),user.getCountry_id(),user.getApp_token(),user.getType(),user.getEmail(),user.getDoctor_id())){
                progressDialog.dismiss();
                Main2Activity.createInstance(LoginActivity.this,1,"E",user);
            }
        }

        @Override
        public void onLoginError(Error message) {
            progressDialog.dismiss();
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Error")
                    .setMessage(message.getCode())
                    .setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        }

/*        @Override
        public void onUserInfoStart() {
            //Nothing
        }



        @Override
        public void onUserInfoCompleted(User user) {
            //logueo
            mydb = new DB(LoginActivity.this);
           if( mydb.insertUser(user.getId(),user.getName(),user.getLastname(),user.getEmail(),user.getType(),user.getType_id(),user.getMethod(),user.getToken(),user.getApptoken(),user.getPicture(),user.getDoctor_id())){
               progressDialog.dismiss();
               Main2Activity.createInstance(LoginActivity.this,1,"n",user);
           }

}

        @Override
        public void onUserInfoError(String message) {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }*/
    }



}


