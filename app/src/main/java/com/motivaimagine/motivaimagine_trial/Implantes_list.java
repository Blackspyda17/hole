package com.motivaimagine.motivaimagine_trial;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motivaimagine.motivaimagine_trial.R;
import com.motivaimagine.motivaimagine_trial.implant_info;
import com.motivaimagine.motivaimagine_trial.implant_info_Adapter;

import java.util.ArrayList;
import java.util.List;


public class Implantes_list extends Fragment {



    public List<implant_info> LImplants;
    public View rootView;
    public RecyclerView rv;
    public implant_info_Adapter adapter;
    LinearLayoutManager llm;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LImplants=cargar_implants();

        rootView = inflater.inflate(R.layout.fragment_implantes_list, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.rv_implants_info);
        rv.setHasFixedSize(true);


        adapter = new implant_info_Adapter(this.getContext(),LImplants);
        rv.setAdapter(adapter);


        llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
        // Inflate the layout for this fragment

    }


    public ArrayList<implant_info> cargar_implants(){
        implant_info implante=new implant_info();
        ArrayList<implant_info> doct=new ArrayList<implant_info>();
        for ( int factor = 0; factor < 2; factor ++ ) {
           doct.add(implante);
        }
        return doct;
    }

}
