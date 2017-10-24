package com.motivaimagine.motivaimagine_trial;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.motivaimagine.motivaimagine_trial.rest_client.user.models.implant_info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gpaez on 9/7/2017.
 */

public class implant_info_Adapter  extends RecyclerView.Adapter < implant_info_Adapter.implant_info_ViewHolder> {

    private List<implant_info> items;

    private final Context context;


    public static class implant_info_ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item

        private TextView titulo;
        private TextView fecha;
        private TextView lado;
        private TextView num_serie;
        private TextView referencia;
        private TextView tipo;
        private TextView textura;
        private TextView base;
        private TextView proyeccion;
        private TextView volumen;
        CardView cardView;


        public implant_info_ViewHolder(View v) {
            super(v);

            titulo = (TextView) v.findViewById(R.id.txt_titulo);
            fecha =(TextView) v.findViewById(R.id.txt_fecha);
            lado = (TextView) v.findViewById(R.id.txt_info_lado);
            num_serie = (TextView) v.findViewById(R.id.txt_serie);
            referencia = (TextView) v.findViewById(R.id.txt_referencia);
            tipo = (TextView) v.findViewById(R.id.txt_tipo);
            textura = (TextView) v.findViewById(R.id.txt_textura);
            base = (TextView) v.findViewById(R.id.txt_base);
            proyeccion = (TextView) v.findViewById(R.id.txt_proyeccion);
            volumen = (TextView) v.findViewById(R.id.txt_volumen);
            cardView=(CardView) itemView.findViewById(R.id.card_implant);


        }

    }

    public implant_info_Adapter(Context context,List<implant_info> items) {

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


    public implant_info_ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.info_implant, viewGroup, false);
        return new implant_info_ViewHolder(v);


    }



    public void onBindViewHolder(implant_info_ViewHolder viewHolder, int i) {
        implant_info currentItem = items.get(i);

try{
    viewHolder.titulo.setText("Breast Implant");
    viewHolder.lado.setText(currentItem.getLado()+" Implant Information");
    viewHolder.fecha.setText(currentItem.getFecha());
    viewHolder.num_serie.setText(currentItem.getNum_serie());
    viewHolder.referencia.setText(currentItem.getReferencia());
    viewHolder.tipo.setText(currentItem.getTipo());
    viewHolder.textura.setText(currentItem.getTextura());
    viewHolder.base.setText(currentItem.getBase());
    viewHolder.proyeccion.setText(currentItem.getProyeccion());
    viewHolder.volumen.setText(currentItem.getVolumen());


}catch (Exception e){

}


    }




    public void updateAdapter(ArrayList<implant_info> List){
        for (implant_info implante:List){

        }
        try {

        }catch (Exception e){

        }
    }
}


