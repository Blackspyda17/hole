package com.motivaimagine.motivaimagine_trial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class doctor_list extends AppCompatActivity {
    public RecyclerView rv;
    public Doctor_Adapter adapter;
    LinearLayoutManager llm;
    public ArrayList<String> LPagos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = (RecyclerView)findViewById(R.id.rv_docs);
        rv.setHasFixedSize(true);

        LPagos=cargar_doctors();
        adapter = new Doctor_Adapter(this,LPagos);
        rv.setAdapter(adapter);


        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

    }

    public ArrayList<String> cargar_doctors(){
        ArrayList<String> doct=new ArrayList<String>();
        for ( int factor = 0; factor < 27; factor ++ ) {
          doct.add("Doctor "+(factor+1));
        }
        return doct;
    }
}
