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
import android.webkit.URLUtil;
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;

import org.json.JSONException;
import org.json.JSONObject;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String USER_TYPE = "user_type";
    private static final String AUTH_TYPE = "auth_type";
    private static final String USUARIO = "User";
    private String NAME;
    private String PHOTO;
    private String AUTH;

    private CircularImageView _profileImage;
    private TextView _name;
    private TextView _email;
    public Toolbar toolbar;
    boolean salir=false;
    public User usuario;
    private GoogleHelper mGoogle;
    private ProfileTracker profileTracker;
    private GoogleApiClient googleApiClient;
    public  Frag_home HOME;
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





        Bundle x = this.getIntent().getExtras();
        if (x != null) {

    switch(x.getInt(USER_TYPE)){
        case 1:
            usuario=(User) x.getSerializable(USUARIO);
            initprof(x.getString(AUTH_TYPE), usuario);
            break;
        case 2:
            break;


    }

        }

        FragmentManager fragmentManager=getSupportFragmentManager();
        Bundle arguments = new Bundle();
        arguments.putString("nombre", NAME);
        arguments.putString("foto", PHOTO);
        HOME=new Frag_home();
        HOME.setArguments(arguments);
        fragmentManager.beginTransaction().replace(R.id.contenedor,HOME).commit();
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
            fragmentManager.beginTransaction().replace(R.id.contenedor,HOME).commit();
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
            toolbar.setTitle("My Appoinments");
            fragmentManager.beginTransaction().replace(R.id.contenedor,new appoiments())
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_faq) {

        }else if (id == R.id.nav_log_out) {
            logout(AUTH);
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
        intent.putExtra(USUARIO, user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private void initprof(final String auth_type, final User user) {
        AUTH=auth_type;
        switch (auth_type){
            case "F":

                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        if (currentProfile != null) {
                            displayProfileInfo(AUTH,currentProfile,user);
                            profileTracker.startTracking();
                        }
                    }
                };


                if (AccessToken.getCurrentAccessToken() == null) {
                    goMainScreen();
                } else {
                    requestEmail(AccessToken.getCurrentAccessToken());

                    Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        displayProfileInfo(AUTH,profile,user);
                    } else {
                        Profile.fetchProfileForCurrentAccessToken();
                    }
                }

                break;
            case "G":

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                googleApiClient = new GoogleApiClient.Builder(this)
                        .enableAutoManage(this, mGoogle)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();

                CheckApiG(user);

                break;
            default:
                displayProfileInfo(AUTH,null,user);
                break;

        }
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

    public void logout(String auth) {
        switch (auth){
            case "F":
                LoginManager.getInstance().logOut();
                goMainScreen();
                break;
            case "G":
                if(googleApiClient!=null) {
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
                break;
            case "E":
                DB mydb=new DB(this.getApplicationContext());
                mydb.deleteUser(mydb.getIDFUser());
                goMainScreen();

                break;
        }

    }






    public void CheckApiG(User user){
        if(googleApiClient!=null) {
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);


            if (opr.isDone()) {
                GoogleSignInResult result = opr.get();
                displayProfileInfo("G",result,user);
            } else {
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                        displayProfileInfo("G",googleSignInResult,usuario);
                    }
                });
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
      if(profileTracker!=null){
          profileTracker.stopTracking();
      }

if(googleApiClient!=null){
    googleApiClient.stopAutoManage(Main2Activity.this);
    googleApiClient.disconnect();
}


    }






    private void displayProfileInfo(String opc, Object profile,User user) {

        switch (opc){
            case "F":
                Profile perfil=(Profile) profile;
                NAME = user.getName()+" "+user.getLastname();
                PHOTO=perfil.getProfilePictureUri(100, 100).toString();
                _name.setText(NAME);
                _email.setText(user.getEmail());
                Glide.with(getApplicationContext())
                        .load(PHOTO)
                        .into(_profileImage);
                break;
            case "G":
                GoogleSignInResult result=(GoogleSignInResult) profile;
                if (result.isSuccess()) {
                    GoogleSignInAccount account = result.getSignInAccount();
                    NAME = user.getName()+" "+user.getLastname();
                    PHOTO= account.getPhotoUrl()+"";
                    _name.setText(NAME);
                    _email.setText(user.getEmail());

                    if(URLUtil.isValidUrl(PHOTO)) {
                        Glide.with(this).load(account.getPhotoUrl()).into(_profileImage);
                    }


                } else {
                    goMainScreen();
                }
                break;
            case "E":

                NAME = user.getName()+" "+user.getLastname();
                PHOTO= null;
                _name.setText(NAME);
                _email.setText(user.getEmail());

                if(URLUtil.isValidUrl(PHOTO)) {
                    Glide.with(getApplicationContext())
                            .load(PHOTO)
                            .into(_profileImage);
                }

                break;
        }

    }


}
