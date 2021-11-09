package com.example.fpr_interfaces;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fpr_interfaces.db.DbClientes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReciclerViewAdaptador extends RecyclerView.Adapter<ReciclerViewAdaptador.ViewHolder> {
    private List<CantanteModelo> originalItems;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView cantante ,nacionalidad,precio;
        private Button comprarterapia;
        ImageView fotocantante;

        public ViewHolder(View itemView) {
            super(itemView);
            cantante = (TextView) itemView.findViewById(R.id.nombreterapiaplantilla);
            nacionalidad = (TextView) itemView.findViewById(R.id.descipterapiaplantilla);
            fotocantante = (ImageView) itemView.findViewById(R.id.imgCantante);
            precio = (TextView) itemView.findViewById(R.id.precioterapiaplantilla);
            comprarterapia = (Button) itemView.findViewById(R.id.comprarterapia);
        }
    }
    public List<CantanteModelo> cantanteLista;

    public ReciclerViewAdaptador(List<CantanteModelo> cantanteLista) {
        this.cantanteLista = cantanteLista;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(cantanteLista);
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
        holder.precio.setText("S/."+cantanteLista.get(position).getPrecio());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(holder.itemView.getContext(),detalle.class);
            i.putExtra("contra",cantanteLista.get(position));
            i.putExtra("id_terapeuta",cantanteLista.get(position).getId_terapia());
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                i.putExtra("titulo",email);
            }

            holder.itemView.getContext().startActivity(i);
        });

        holder.comprarterapia.setOnClickListener(v -> {

            DbClientes db = new DbClientes(holder.comprarterapia.getContext());
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                int id_cliente=db.encontrarIdDelClienteConUsuario(email);
                String id_terapia=cantanteLista.get(position).getId_terapia_estesi();
                String saldo_cliente= db.traerSaldoClientes(email);
                String precio_terapia= db.traerPrecioTerapia(id_terapia);
                int saldo_clienteint = Integer.parseInt(saldo_cliente);
                int precio_terapiaint = Integer.parseInt(precio_terapia);
                if(saldo_clienteint>=precio_terapiaint){
                    int preciofinal = saldo_clienteint - precio_terapiaint;
                    String predioFinalString = String.valueOf(preciofinal);
                    db.cambiarSaldoCliente(email,predioFinalString);
                    db.comprarTerapia(id_cliente,id_terapia);
                    Intent i = new Intent(holder.comprarterapia.getContext(),pruebabuscador.class);
                    i.putExtra("email",email);
                    holder.comprarterapia.getContext().startActivity(i);

                    CharSequence text = "GESTIONA TU CUENTA PA VER LO QUE COMPRASTE";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(holder.comprarterapia.getContext(), text, duration);
                    toast.show();
                }else{

                    CharSequence text = "NO TE ALCANZA EL DINERO PARA ESTO";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(holder.comprarterapia.getContext(), text, duration);
                    toast.show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cantanteLista.size();
    }
    public void filter(final String strSearch){
        cantanteLista.clear();
        if(strSearch.length()==0){
            cantanteLista.clear();
            cantanteLista.addAll(originalItems);
        }
        else{
            cantanteLista.clear();
            List<CantanteModelo> collect = originalItems.stream()
                    .filter(i -> i.getCantante().toLowerCase().contains(strSearch))
                    .collect(Collectors.toList());
            cantanteLista.addAll(collect);
        }
    }
}
