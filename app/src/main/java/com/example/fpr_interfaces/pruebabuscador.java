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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebabuscador);




        svSearch = (SearchView)findViewById(R.id.svSearch);
        recyclerViewCantante=(RecyclerView)findViewById(R.id.reciclercantante);
        recyclerViewCantante.setLayoutManager(new LinearLayoutManager(this));

        DbClientes dbconsulta = new DbClientes(this);

        adaptadorCantante=new ReciclerViewAdaptador(dbconsulta.mostrarTerapias());
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

        System.out.println(newString);
        FirebaseAuth.getInstance().signOut();
        Intent deslogeate = new Intent(pruebabuscador.this,MainActivity.class);
        startActivity(deslogeate);
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
    public void gestionatucuenta(View v){
        Intent gestioncliente = new Intent(pruebabuscador.this,MostrarClientePrueba.class);
        gestioncliente.putExtra("email",newString);
        startActivity(gestioncliente);
    }

}