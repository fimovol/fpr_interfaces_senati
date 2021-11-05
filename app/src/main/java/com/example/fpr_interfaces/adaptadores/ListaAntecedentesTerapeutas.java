package com.example.fpr_interfaces.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fpr_interfaces.R;
import com.example.fpr_interfaces.entidades.Antecedentes;
import com.example.fpr_interfaces.entidades.Clientes;

import java.util.ArrayList;

public class ListaAntecedentesTerapeutas extends RecyclerView.Adapter<ListaAntecedentesTerapeutas.ClientesViewHolder>{

    ArrayList<Antecedentes> listaClientes;
    public ListaAntecedentesTerapeutas(ArrayList<Antecedentes> listaClientes){
        this.listaClientes = listaClientes;
    }
    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.antecedentes_template,parent,false);
        return new ListaAntecedentesTerapeutas.ClientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {
        String usuario = listaClientes.get(position).getUsuario()+" adquirio el servicio";
        String asd = listaClientes.get(position).getId_clientefk()+" adquirio el servicio";
        holder.nombre_antecedete.setText(usuario);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView nombre_antecedete;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_antecedete=itemView.findViewById(R.id.nombre_antecedete);
        }
    }
}
