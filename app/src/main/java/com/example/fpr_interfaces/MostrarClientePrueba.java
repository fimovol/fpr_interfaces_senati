package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fpr_interfaces.adaptadores.ListaClienteAsapter;
import com.example.fpr_interfaces.db.DbClientes;
import com.example.fpr_interfaces.entidades.Clientes;

import java.util.ArrayList;

public class MostrarClientePrueba extends AppCompatActivity {
    public RecyclerView recyclerpruebacliente;
    public ArrayList<Clientes> listaClientesArray;

    Bundle extras;
    String newString;

    TextView saldodelcliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_cliente_prueba);
        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }
        recyclerpruebacliente = findViewById(R.id.recyclerpruebacliente);
        recyclerpruebacliente.setLayoutManager(new LinearLayoutManager(this));
        DbClientes dbclientes = new DbClientes(MostrarClientePrueba.this);
        listaClientesArray = new ArrayList<>();
        int id_cliente =dbclientes.encontrarIdDelClienteConUsuario(newString);
        ListaClienteAsapter adapter = new ListaClienteAsapter(dbclientes.loQueComproElCliente(id_cliente));
        recyclerpruebacliente.setAdapter(adapter);


        saldodelcliente = findViewById(R.id.saldodelcliente);
        DbClientes db = new DbClientes(this);

        saldodelcliente.setText("Su saldo: S/."+db.traerSaldoClientes(newString));
    }
}