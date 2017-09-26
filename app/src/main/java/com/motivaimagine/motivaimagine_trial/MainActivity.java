package com.motivaimagine.motivaimagine_trial;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }



        Button patient = (Button) findViewById(R.id.btn_patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent doctores = new Intent(getApplicationContext(),doctor_list.class);
                    startActivity(doctores);
            }
        });



        Button doctor= (Button) findViewById(R.id.btn_doctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.createInstance(MainActivity.this,2);
            }
        });

        Button Login= (Button) findViewById(R.id.btn_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.createInstance(MainActivity.this,0);
            }
        });

    }
    }
