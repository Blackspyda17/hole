package com.motivaimagine.motivaimagine_trial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class forms_types extends Fragment {

    public ArrayList<String> LForms;
    public View rootView;
    public RecyclerView rv;
    public Forms_Adapter adapter;
    LinearLayoutManager llm;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_forms_types, container, false);


        rv = (RecyclerView) rootView.findViewById(R.id.rv_forms_type);
        rv.setHasFixedSize(true);

        LForms =cargar_();
        adapter = new Forms_Adapter(this.getContext(), LForms);
        rv.setAdapter(adapter);
        llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);

        return rootView;
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
