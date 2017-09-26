package com.motivaimagine.motivaimagine_trial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class forms_menu extends AppCompatActivity {
    public ArrayList<String> LForms;

    public RecyclerView rv;
    public Forms_Adapter adapter;
    LinearLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_menu);


        rv = (RecyclerView) findViewById(R.id.rv_forms_type);
        rv.setHasFixedSize(true);

        LForms =cargar_();
        adapter = new Forms_Adapter(this, LForms);
        rv.setAdapter(adapter);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        // Inflate the layout for this fragment

    }


    public ArrayList<String> cargar_(){
        ArrayList<String> doct=new ArrayList<>();

            doct.add("Pre-Operation");
            doct.add("30 Days");
            doct.add("1 Years");
            doct.add("2 Years");
            doct.add("3 Years");
            doct.add("4 Years");
            doct.add("5 Years");
            doct.add("6 Years");
            doct.add("7 Years");
            doct.add("8 Years");
            doct.add("9 Years");
            doct.add("10 Years");
        return doct;
    }

}
