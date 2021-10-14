package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fpr_interfaces.adaptadores.ListaClienteAsapter;
import com.example.fpr_interfaces.db.DbClientes;
import com.example.fpr_interfaces.entidades.Clientes;

import java.util.ArrayList;

public class MostrarClientePrueba extends AppCompatActivity {
    public RecyclerView recyclerpruebacliente;
    public ArrayList<Clientes> listaClientesArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_cliente_prueba);

        recyclerpruebacliente = findViewById(R.id.recyclerpruebacliente);
        recyclerpruebacliente.setLayoutManager(new LinearLayoutManager(this));
        DbClientes dbclientes = new DbClientes(MostrarClientePrueba.this);
        listaClientesArray = new ArrayList<>();

        ListaClienteAsapter adapter = new ListaClienteAsapter(dbclientes.mostraClientes());
        recyclerpruebacliente.setAdapter(adapter);
    }
}