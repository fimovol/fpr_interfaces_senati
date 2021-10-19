package com.example.fpr_interfaces.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fpr_interfaces.R;
import com.example.fpr_interfaces.entidades.Terapiasentidad;

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
    }

    @Override
    public int getItemCount() {
        return listaterapias.size();
    }

    public class TerapiasViweHolder extends RecyclerView.ViewHolder {
        TextView nombreterapiaplantilla,precioterapiaplantilla,descipterapiaplantilla;
        public TerapiasViweHolder(@NonNull View itemView) {
            super(itemView);
            nombreterapiaplantilla=itemView.findViewById(R.id.nombreterapiaplantilla);
            precioterapiaplantilla=itemView.findViewById(R.id.precioterapiaplantilla);
            descipterapiaplantilla=itemView.findViewById(R.id.descipterapiaplantilla);
        }
    }
}
