package com.example.fpr_interfaces;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReciclerViewAdaptador extends RecyclerView.Adapter<ReciclerViewAdaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView cantante ,nacionalidad;
        ImageView fotocantante;

        public ViewHolder(View itemView) {
            super(itemView);
            cantante = (TextView) itemView.findViewById(R.id.id_tvcantante);
            nacionalidad = (TextView) itemView.findViewById(R.id.id_tvnacionalidad);
            fotocantante = (ImageView) itemView.findViewById(R.id.imgCantante);
        }
    }
    public List<CantanteModelo> cantanteLista;

    public ReciclerViewAdaptador(List<CantanteModelo> cantanteLista) {
        this.cantanteLista = cantanteLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cantante,parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cantante.setText(cantanteLista.get(position).getCantante());
        holder.nacionalidad.setText(cantanteLista.get(position).getNacionalidad());
        holder.fotocantante.setImageResource(cantanteLista.get(position).getFotocantante());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(),detalle.class);
                i.putExtra("contra",cantanteLista.get(position));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cantanteLista.size();
    }
}
