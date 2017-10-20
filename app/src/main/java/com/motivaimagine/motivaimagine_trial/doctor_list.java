package com.motivaimagine.motivaimagine_trial;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.motivaimagine.motivaimagine_trial.rest_client.user.Controller;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.DoctorListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Doctor;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;

import java.util.ArrayList;


public class doctor_list extends AppCompatActivity {
    public RecyclerView rv;
    public Doctor_Adapter adapter;
    LinearLayoutManager llm;
    public ArrayList<Doctor> LDoctors=new ArrayList<>();
    public ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog=new ProgressDialog(doctor_list.this,
                R.style.AppThemeMain2_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading the doctors list, please wait...");
        cargar_doctors();
        rv = (RecyclerView)findViewById(R.id.rv_docs);
        rv.setHasFixedSize(true);


        adapter = new Doctor_Adapter(this,LDoctors);
        rv.setAdapter(adapter);


        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

    }

    public void cargar_doctors(){
        Controller.getInstance().getDoctorList(this,new doctor_list.DoctorLCallback());
//        ArrayList<String> doct=new ArrayList<String>();
//        for ( int factor = 0; factor < 27; factor ++ ) {
//          doct.add("Doctor "+(factor+1));
//        }
//        return doct;
    }


    class DoctorLCallback implements DoctorListener {
        @Override
        public void onDoc_ListStart() {
            progressDialog.show();
        }

        @Override
        public void onDoc_ListCompleted(ArrayList<Doctor> doctors) {
            progressDialog.dismiss();
            LDoctors=doctors;
            rv = (RecyclerView)findViewById(R.id.rv_docs);
            rv.setHasFixedSize(true);


            adapter = new Doctor_Adapter(doctor_list.this,LDoctors);
            rv.setAdapter(adapter);


            llm = new LinearLayoutManager(doctor_list.this);
            rv.setLayoutManager(llm);
        }

        @Override
        public void onDoc_ListError(Error error) {
            String mensaje = error.getCode();
            if (error.getCode().equals("301")){
                mensaje= "Error Interpreting the Information";
            }
            progressDialog.dismiss();

            Snackbar snackbar = Snackbar
                    .make(getCurrentFocus(),mensaje, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.Dissmiss), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.show();
        }

       /* @Override
        public void onLoginStart() {
            progressDialog.show();
        }

        @Override
        public void onLoginCompleted(User user) {
            mydb = new DB(LoginActivity.this);
            if( mydb.insertUser(user.getId(),user.getName(),user.getLastname(),user.getCountry_id(),user.getApp_token(),user.getType(),user.getEmail(),user.getDoctor_id(),METHOD,PHOTO)){
                progressDialog.dismiss();
                Main2Activity.createInstance(LoginActivity.this,user.getType(),user);
            }
        }

        @Override
        public void onLoginError(Error message) {

            String mensaje = "Unknown Error";
            if (message.getCode().equals("101")){
                mensaje = getString(R.string.error_invalid_password);
            }else if(message.getCode().equals("102")){
                mensaje= getString(R.string.this_email_address_is_not_registered);
            }else  if (message.getCode().equals("301")){
                mensaje= "Error Interpreting the Information";
            }
            progressDialog.dismiss();

            Snackbar snackbar = Snackbar
                    .make(getCurrentFocus(),mensaje, Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.Dissmiss), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.show();*/

    /*        new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Error")
                    .setMessage(mensaje)
                    .setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
*/
        }

    }
