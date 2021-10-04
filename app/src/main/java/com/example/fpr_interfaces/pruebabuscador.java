package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class pruebabuscador extends AppCompatActivity {
    private RecyclerView recyclerViewCantante;
    private ReciclerViewAdaptador adaptadorCantante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebabuscador);

        recyclerViewCantante=(RecyclerView)findViewById(R.id.reciclercantante);
        recyclerViewCantante.setLayoutManager(new LinearLayoutManager(this));

        adaptadorCantante=new ReciclerViewAdaptador(obtenerCantantes());
        recyclerViewCantante.setAdapter(adaptadorCantante);
    }
    public List<CantanteModelo> obtenerCantantes(){
        List<CantanteModelo> cantante = new ArrayList<>();
        cantante.add(new CantanteModelo("nombre titulo editar luego","borrar luego",R.drawable._6));
        cantante.add(new CantanteModelo("nombre titulo editar luego","borrar luego",R.drawable._7));
        cantante.add(new CantanteModelo("nombre titulo editar luego","borrar luego",R.drawable._8));
        cantante.add(new CantanteModelo("nombre titulo editar luego","borrar luego",R.drawable._9));
        cantante.add(new CantanteModelo("nombre titulo editar luego","borrar luego",R.drawable._10));
        cantante.add(new CantanteModelo("nombre titulo editar luego","borrar luego",R.drawable._11));
        return  cantante;
    }
}