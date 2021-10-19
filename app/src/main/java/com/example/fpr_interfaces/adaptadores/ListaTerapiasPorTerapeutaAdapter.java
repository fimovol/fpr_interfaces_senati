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
import com.example.fpr_interfaces.R;
import com.example.fpr_interfaces.detalle;
import com.example.fpr_interfaces.entidades.Terapiasentidad;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                .inflate(R.layout.plantellaparalasterapias,null,false);
        return new ListaTerapiasPorTerapeutaAdapter.TerapiasViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaTerapiasPorTerapeutaAdapter.TerapiasViweHolder holder, int position) {
        holder.nombreterapiaplantilla.setText(listaterapias.get(position).getNombre());
        holder.precioterapiaplantilla.setText(listaterapias.get(position).getPrecio());
        holder.descipterapiaplantilla.setText(listaterapias.get(position).getDescripcion());


        holder.botoneditarTerapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), EditarServicio.class);
                i.putExtra("id_terapia",listaterapias.get(position).getId_terapia());

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();
                    i.putExtra("email",email);
                }

                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaterapias.size();
    }

    public class TerapiasViweHolder extends RecyclerView.ViewHolder {
        TextView nombreterapiaplantilla,precioterapiaplantilla,descipterapiaplantilla;
        Button botoneditarTerapia;
        public TerapiasViweHolder(@NonNull View itemView) {
            super(itemView);
            nombreterapiaplantilla=itemView.findViewById(R.id.nombreterapiaplantilla);
            precioterapiaplantilla=itemView.findViewById(R.id.precioterapiaplantilla);
            descipterapiaplantilla=itemView.findViewById(R.id.descipterapiaplantilla);
            botoneditarTerapia=itemView.findViewById(R.id.botoneditarTerapia);
        }
    }
}
