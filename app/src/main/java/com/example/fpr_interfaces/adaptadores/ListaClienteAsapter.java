package com.example.fpr_interfaces.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fpr_interfaces.DetalleServicioComprado;
import com.example.fpr_interfaces.MostrarClientePrueba;
import com.example.fpr_interfaces.R;
import com.example.fpr_interfaces.db.DbClientes;
import com.example.fpr_interfaces.entidades.Clientes;
import com.example.fpr_interfaces.pruebabuscador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        holder.cancelarterapia.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String email = user.getEmail();
            DbClientes db = new DbClientes(holder.cancelarterapia.getContext());
            String id_terapia = listaClientes.get(position).getId_terapia();
            db.cancelarcompraTerapia(id_terapia);
            String precioterapia = listaClientes.get(position).getPrecio();
            String saldo_cliente= db.traerSaldoClientes(email);
            int precioint = Integer.parseInt(precioterapia);
            int saldoclienteint = Integer.parseInt(saldo_cliente);
            saldo_cliente = String.valueOf(saldoclienteint+precioint);
            db.cambiarSaldoCliente(email,saldo_cliente);
            Intent i = new Intent(holder.cancelarterapia.getContext(), MostrarClientePrueba.class);
            i.putExtra("email",email);
            holder.cancelarterapia.getContext().startActivity(i);
        });
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(holder.itemView.getContext(), DetalleServicioComprado.class);
            i.putExtra("nombre",listaClientes.get(position).getNomre());
            //sacar descripcion del terapeuta con el id de la base de datos
            i.putExtra("imagen",String.valueOf(listaClientes.get(position).getImagen()));
            i.putExtra("id_terapeuta",listaClientes.get(position).getId_terapeuta());
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                i.putExtra("titulo",email);
            }
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,precio,descripcion;
        ImageView imagen;
        Button cancelarterapia;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            precio = itemView.findViewById(R.id.precio);
            descripcion = itemView.findViewById(R.id.descripcion);
            imagen = itemView.findViewById(R.id.imagen);
            cancelarterapia =itemView.findViewById(R.id.cancelarterapia);
        }
    }
}
