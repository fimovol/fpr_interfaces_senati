package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.fpr_interfaces.db.DbClientes;
import com.example.fpr_interfaces.db.Dbhelper2;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pruebabuscador extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerViewCantante;
    private ReciclerViewAdaptador adaptadorCantante;
    private SearchView svSearch;
    Bundle extras;
    String newString;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebabuscador);

        svSearch = (SearchView)findViewById(R.id.svSearch);
        recyclerViewCantante=(RecyclerView)findViewById(R.id.reciclercantante);
        recyclerViewCantante.setLayoutManager(new LinearLayoutManager(this));

        adaptadorCantante=new ReciclerViewAdaptador(obtenerCantantes());
        recyclerViewCantante.setAdapter(adaptadorCantante);

        svSearch.setOnQueryTextListener(this);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }
    }
    public void deslogelearse(View vista){

        /*
        esto crea la base de datos
        Dbhelper2 dbhelper2 = new Dbhelper2(pruebabuscador.this);
        SQLiteDatabase db = dbhelper2.getWritableDatabase();
        if(db != null){
            Toast.makeText(pruebabuscador.this,"BASE DE DATOS CREADA",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(pruebabuscador.this,"error",Toast.LENGTH_LONG).show();
        }
        */
        System.out.println(newString);
        FirebaseAuth.getInstance().signOut();
        Intent deslogeate = new Intent(pruebabuscador.this,MainActivity.class);
        startActivity(deslogeate);
    }

    public List<CantanteModelo> obtenerCantantes(){
        List<CantanteModelo> cantante = new ArrayList<>();

        cantante.add(new CantanteModelo("julio",
                "Experimentado con más de 4 años de trayectoria desarrollada en el mundo de las terapias",
                R.drawable._7,"$109"));
        cantante.add(new CantanteModelo("maria",
                "Gracias a mi formación especializada, considero que puedo aportar valor y seguir desarrollándome profesionalmente",
                R.drawable._8,"$110"));
        cantante.add(new CantanteModelo("juliana",
                "Como persona organizada y con una gran motivación, soy capaz de adaptarme a cualquier cliente y dar siempre lo mejor de mí",
                R.drawable._9,"$115"));
        cantante.add(new CantanteModelo("roberto",
                "Tengo varios años de experiencia en terapias profecionales y siempre estoy en busqueda de nuevos clientes",
                R.drawable._10,"$102"));
        cantante.add(new CantanteModelo("sebastian",
                "Soy una persona perseverante, que adora los retos y no se rinde fácilmente, con buen ánimo y capaz de resolver problemas",
                R.drawable._11,"$104"));
        cantante.add(new CantanteModelo("esteban",
                "Con años de experiencia en la terapias, he sido capaz de adaptarme a los diferentes clientes para los que he trabajado",
                R.drawable._6,"$113"));
        return  cantante;
    }
    public void comprarterapia(View vista){
        System.out.println("ME LLAMO JAVIER"+vista);
        Toast.makeText(pruebabuscador.this, "Felicidades Compraste tu cita", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adaptadorCantante.filter(newText);
        return false;
    }
}