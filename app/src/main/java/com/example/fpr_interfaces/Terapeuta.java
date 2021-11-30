package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fpr_interfaces.adaptadores.ListaTerapiasPorTerapeutaAdapter;
import com.example.fpr_interfaces.db.DbClientes;

public class Terapeuta extends AppCompatActivity {
    Bundle extras;
    String newString;
    RecyclerView recyclerTerapiasporTerapeuta;
    Button antecedentes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapeuta);

        antecedentes = findViewById(R.id.antecedentes);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }
        recyclerTerapiasporTerapeuta=(RecyclerView)findViewById(R.id.recyclerTerapiasporTerapeuta);
        recyclerTerapiasporTerapeuta.setLayoutManager(new LinearLayoutManager(this));
        DbClientes dbclientes = new DbClientes(Terapeuta.this);
        String elTerapeutaes = newString;
        ListaTerapiasPorTerapeutaAdapter adaptador=new ListaTerapiasPorTerapeutaAdapter
                (dbclientes.mostrarTerapiasPorTerapeuta(elTerapeutaes));
        recyclerTerapiasporTerapeuta.setAdapter(adaptador);

        antecedentes.setOnClickListener(v -> {
            Intent editarantecedentesterapeuta = new Intent(Terapeuta.this,Editarantecedentesterapeuta.class);
            editarantecedentesterapeuta.putExtra("email",newString);
            startActivity(editarantecedentesterapeuta);
        });
    }
    public void editar (View v){
        Intent editar = new Intent(Terapeuta.this,EditarServicio.class);
        editar.putExtra("email",newString);
        startActivity(editar);
    }

    public void deslogeate (View v){
        Intent deslogeate = new Intent(Terapeuta.this,MainActivity.class);
        startActivity(deslogeate);
    }
    public void agregarterapia(View v){
        Intent agregarterapia = new Intent(Terapeuta.this, com.example.fpr_interfaces.agregarterapia.class);
        agregarterapia.putExtra("email",newString);
        startActivity(agregarterapia);
    }
}