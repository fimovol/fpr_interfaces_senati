package com.example.fpr_interfaces.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                .inflate(R.layout.prueba_clientes_borrar,null,false);
        return new ClientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {
        holder.textView4nombre.setText(listaClientes.get(position).getNomre());
        holder.textView5usuario.setText(listaClientes.get(position).getUsuario());
        holder.textView6saldo.setText(listaClientes.get(position).getSaldo());
        holder.textView7contra.setText(listaClientes.get(position).getContrasena());
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView textView4nombre,textView5usuario,textView6saldo,textView7contra;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView4nombre = itemView.findViewById(R.id.textView4nombre);
            textView5usuario = itemView.findViewById(R.id.textView5usuario);
            textView6saldo = itemView.findViewById(R.id.textView6saldo);
            textView7contra = itemView.findViewById(R.id.textView7contra);
        }
    }
}
