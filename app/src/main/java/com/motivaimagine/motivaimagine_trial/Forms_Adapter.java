package com.motivaimagine.motivaimagine_trial;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gpaez on 9/12/2017.
 */

public class Forms_Adapter extends RecyclerView.Adapter < Forms_Adapter.Forms_Adapter_ViewHolder> {


    private List<String> items;

    private final Context context;

    public static class Forms_Adapter_ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        CardView cardView;


        public Forms_Adapter_ViewHolder(View v) {
            super(v);
        titulo=(TextView) v.findViewById(R.id.titulo_form);

        }
    }

    public Forms_Adapter(Context context, List<String> items) {

        this.context = context;
        this.items = items;
    }


    @Override
    public int getItemCount() {

        try {
            return items.size();
        }catch (Exception e){
            return 0;
        }

    }



    public Forms_Adapter.Forms_Adapter_ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.form_type, viewGroup, false);
        return new Forms_Adapter.Forms_Adapter_ViewHolder(v);


    }


    public void onBindViewHolder(Forms_Adapter.Forms_Adapter_ViewHolder viewHolder, int i) {
       String currentItem = items.get(i);

        try{
            viewHolder.titulo.setText(currentItem);


        }catch (Exception e){

        }


    }
}
