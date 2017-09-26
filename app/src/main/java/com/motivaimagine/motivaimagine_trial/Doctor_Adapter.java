package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gpaez on 8/29/2017.
 */

public class Doctor_Adapter extends RecyclerView.Adapter<Doctor_Adapter.DoctorViewHolder> implements ItemClickListener {
    private ArrayList<String> mDataset;
    private final Context context;





    public static class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item

        public TextView nombre_doc;

        public ItemClickListener listener;
        CardView cardView;

        public DoctorViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre_doc=(TextView) v.findViewById(R.id.person_name);
            this.listener=listener;
            cardView=(CardView) itemView.findViewById(R.id.cardview_persona);
            v.setOnClickListener(this);

        }


        public void onClick(View v) {

                listener.onItemClick(v, getAdapterPosition());

        }
    }

    public Doctor_Adapter(Context context,ArrayList<String> items) {

        this.context = context;
        this.mDataset = items;

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public DoctorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.docs_list, viewGroup, false);
        return new DoctorViewHolder(v,this);


    }



    public void onBindViewHolder(DoctorViewHolder viewHolder, int i) {
        String currentItem = mDataset.get(i);
        viewHolder.nombre_doc.setText(currentItem);

    }





    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        CreateAccountActivity.createInstance((Activity) context,0);
    }


}
