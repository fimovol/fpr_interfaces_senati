package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
    Button botonsaldo,volverpb;
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
        botonsaldo = findViewById(R.id.botonsaldo);
        volverpb = findViewById(R.id.volverpb);
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

        botonsaldo.setOnClickListener(v ->{
            Intent aumentarSaldo = new Intent(MostrarClientePrueba.this,AumentarSaldo.class);
            aumentarSaldo.putExtra("email",newString);
            System.out.println(newString);
            startActivity(aumentarSaldo);
        });
        volverpb.setOnClickListener(v->{
            Intent volver = new Intent(MostrarClientePrueba.this,pruebabuscador.class);
            volver.putExtra("email",newString);
            startActivity(volver);
        });
    }
}