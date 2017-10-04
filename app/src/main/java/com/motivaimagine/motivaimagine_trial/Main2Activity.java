package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.mukeshsolanki.sociallogin.facebook.FacebookHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener {
    private static final String USER_TYPE = "user_type";
    private static final String AUTH_TYPE = "auth_type";
    private static final String USUARIO = "User";

    private CircularImageView _profileImage;
    private TextView _name;
    private TextView _email;
    public Toolbar toolbar;
    boolean salir=false;
    public User usuario;
    private FacebookHelper mFacebook;
    private ProfileTracker profileTracker;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        _name= (TextView) hView.findViewById(R.id.txt_name_draw);
        _email= (TextView) hView.findViewById(R.id.txt_mail_draw);
        _profileImage= (CircularImageView) hView.findViewById(R.id.profileImage);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new Frag_home()).commit();

/*        Button btn_account = (Button) findViewById(R.id.btn_account);
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Logear = new Intent(getApplicationContext(),MyAccount.class);
                startActivity(Logear);
            }
        });*/


        Bundle x = this.getIntent().getExtras();
        if (x != null) {

    switch(x.getInt(USER_TYPE)){
        case 1:
            initprof(x.getString(AUTH_TYPE), x.getSerializable(USUARIO));

            break;
        case 2:

            break;


    }

        }



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = getSupportFragmentManager().getBackStackEntryCount();


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(count>0){
            getSupportFragmentManager().popBackStack();
        }
        else if(count==0 && salir==false){
            salir=true;
            Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();
        } else if(count==0 && salir==true){
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.nav_home) {

            toolbar.setTitle("Home");
            salir=false;
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Frag_home())
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_CTI) {

            toolbar.setTitle("Clinical Trial Info");
            salir=false;
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Clinical_T())
                    .commit();

        /*    toolbar.setTitle(R.string.my_account);
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Frag_myaccount())
                    .addToBackStack(null)
                    .commit();*/
        }else if (id == R.id.nav_regist_implant) {
            toolbar.setTitle(R.string.implant_registration);
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Reg_Implant())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_my_implants) {
            toolbar.setTitle("My Implants");
            fragmentManager.beginTransaction().replace(R.id.contenedor,new Implantes_list())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_questionaries) {
            toolbar.setTitle("My Forms");
            fragmentManager.beginTransaction().replace(R.id.contenedor,new forms_types())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_appoinments) {
            toolbar.setTitle("My Appoinmentsx");
            fragmentManager.beginTransaction().replace(R.id.contenedor,new appoiments())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_faq) {

        }else if (id == R.id.nav_log_out) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public static void createInstance(Activity activity, int user_type,String auth_type, User user) {
        Intent intent = getLaunchIntent(activity,user_type,auth_type,user);
        activity.startActivity(intent);
    }




    public static Intent getLaunchIntent(Context context,int user_type,String auth_type, User user) {
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra(USER_TYPE,user_type);
        intent.putExtra(AUTH_TYPE,auth_type);
        intent.putExtra(USUARIO, (Serializable) user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private void initprof(String auth_type, Serializable user) {

        switch (auth_type){
            case "f":

                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        if (currentProfile != null) {
                            displayProfileInfo(currentProfile);
                        }
                    }
                };

                if (AccessToken.getCurrentAccessToken() == null) {
                    goMainScreen();
                } else {
                    requestEmail(AccessToken.getCurrentAccessToken());

                    Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        displayProfileInfo(profile);
                    } else {
                        Profile.fetchProfileForCurrentAccessToken();
                    }
                }

                break;
            case "g":
                break;
            default:
                displayProfileInfo((User) user);
                break;



        }
    }


    private void displayProfileInfo(User profile) {

        String email= profile.getEmail();
        String name = profile.getName()+" "+profile.getLastname();
        String photoUrl = profile.getPicture();

        _name.setText(""+name);
        _email.setText(""+email);

        Glide.with(getApplicationContext())
                .load(photoUrl)
                .into(_profileImage);
    }

    private void displayProfileInfo(Profile profile) {
        String name = profile.getName();
        String photoUrl = profile.getProfilePictureUri(100, 100).toString();

        _name.setText(name);

        Glide.with(getApplicationContext())
                .load(photoUrl)
                .into(_profileImage);
    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void setEmail(String email) {
        _email.setText(email);
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        goMainScreen();
    }


    public void logOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goMainScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "Session not closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void logOut(View view) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goMainScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "Session Closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            _name.setText(account.getDisplayName());
            _email.setText(account.getEmail());

            Glide.with(this).load(account.getPhotoUrl()).into(_profileImage);

        } else {
            goMainScreen();
        }
    }
}
