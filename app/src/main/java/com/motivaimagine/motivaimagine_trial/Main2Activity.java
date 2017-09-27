package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.Serializable;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String OPCION = "Opc";
    private static final String USUARIO = "User";
    public Toolbar toolbar;
    boolean salir=false;
    public User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public static void createInstance(Activity activity, int usuario, User user) {
        Intent intent = getLaunchIntent(activity, usuario,user);
        activity.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, int usuario,User user) {
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra(OPCION,usuario);
        intent.putExtra(USUARIO, (Serializable) user);
        return intent;
    }


}
