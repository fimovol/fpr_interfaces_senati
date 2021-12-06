package com.example.fpr_interfaces.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fpr_interfaces.EditarServicio;
import com.example.fpr_interfaces.Quien_compro_el_servicio;
import com.example.fpr_interfaces.R;
import com.example.fpr_interfaces.detalle;
import com.example.fpr_interfaces.entidades.Terapiasentidad;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListaTerapiasPorTerapeutaAdapter extends RecyclerView.Adapter<ListaTerapiasPorTerapeutaAdapter.TerapiasViweHolder>{
    ArrayList<Terapiasentidad> listaterapias;
    public ListaTerapiasPorTerapeutaAdapter(ArrayList<Terapiasentidad> listaterapias){
        this.listaterapias = listaterapias;
    }
    @NonNull
    @Override
    public ListaTerapiasPorTerapeutaAdapter.TerapiasViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.plantellaparalasterapias,parent,false);
        return new ListaTerapiasPorTerapeutaAdapter.TerapiasViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaTerapiasPorTerapeutaAdapter.TerapiasViweHolder holder, int position) {
        String preicoConSoles = "S/."+(listaterapias.get(position).getPrecio());

        holder.nombreterapiaplantilla.setText(listaterapias.get(position).getNombre());
        holder.precioterapiaplantilla.setText(preicoConSoles);
        holder.descipterapiaplantilla.setText(listaterapias.get(position).getDescripcion());
        if(listaterapias.get(position).getCompradoSiNo().equals("1")){
            holder.anadircolordecompra.setBackgroundColor(0xff80ff80);//verde clarito jeje
            holder.botoneditarTerapia.setVisibility(View.INVISIBLE);
            holder.itemView.setOnClickListener(v -> {
                Intent i = new Intent(holder.itemView.getContext(), Quien_compro_el_servicio.class);
                i.putExtra("id_terapia",listaterapias.get(position).getId_terapia());
                holder.itemView.getContext().startActivity(i);
            });
        }
        holder.botoneditarTerapia.setOnClickListener(v -> {
            Intent i = new Intent(holder.itemView.getContext(), EditarServicio.class);
            i.putExtra("id_terapia",listaterapias.get(position).getId_terapia());
            i.putExtra("getNombre",listaterapias.get(position).getNombre());
            i.putExtra("getPrecio",listaterapias.get(position).getPrecio());
            i.putExtra("getDescripcion",listaterapias.get(position).getDescripcion());

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                i.putExtra("email",email);
            }

            holder.itemView.getContext().startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return listaterapias.size();
    }

    public class TerapiasViweHolder extends RecyclerView.ViewHolder {
        TextView nombreterapiaplantilla,precioterapiaplantilla,descipterapiaplantilla;
        Button botoneditarTerapia;
        CardView anadircolordecompra;
        public TerapiasViweHolder(@NonNull View itemView) {
            super(itemView);
            nombreterapiaplantilla=itemView.findViewById(R.id.nombreterapiaplantilla);
            precioterapiaplantilla=itemView.findViewById(R.id.precioterapiaplantilla);
            descipterapiaplantilla=itemView.findViewById(R.id.descipterapiaplantilla);
            botoneditarTerapia=itemView.findViewById(R.id.botoneditarTerapia);
            anadircolordecompra=itemView.findViewById(R.id.anadircolordecompra);
        }
    }
}
