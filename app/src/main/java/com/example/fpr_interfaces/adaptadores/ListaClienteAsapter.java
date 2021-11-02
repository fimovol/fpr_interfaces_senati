package com.example.fpr_interfaces.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fpr_interfaces.R;
import com.example.fpr_interfaces.entidades.Clientes;

import java.util.ArrayList;

public class ListaClienteAsapter extends RecyclerView.Adapter<ListaClienteAsapter.ClientesViewHolder>{
    ArrayList<Clientes> listaClientes;
    public ListaClienteAsapter(ArrayList<Clientes> listaClientes){
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.servicios_que_compro_el_cliente,parent,false);
        return new ClientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {
        holder.nombre.setText(listaClientes.get(position).getNomre());
        holder.precio.setText("S/."+listaClientes.get(position).getPrecio());
        holder.descripcion.setText(listaClientes.get(position).getDescripcion());
        holder.imagen.setImageResource(listaClientes.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,precio,descripcion;
        ImageView imagen;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            precio = itemView.findViewById(R.id.precio);
            descripcion = itemView.findViewById(R.id.descripcion);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }
}
