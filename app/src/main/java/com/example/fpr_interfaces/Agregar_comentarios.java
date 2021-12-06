package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.fpr_interfaces.db.DbClientes;

public class Agregar_comentarios extends AppCompatActivity {
    RatingBar estrellas;
    Button enviar;
    Bundle extras;
    String newString,calificacion,terapia;
    EditText comentarios;
    DbClientes db = new DbClientes(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_comentarios);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
            System.out.println("nada en el titulo :V");
            calificacion="0";
        } else {
            newString= extras.getString("titulo");
            calificacion=extras.getString("calificacion");
            terapia=extras.getString("terapia");
            setTitle(newString);
        }

        estrellas = findViewById(R.id.estrellas);
        enviar = findViewById(R.id.enviar);
        comentarios=findViewById(R.id.comentarios);

        System.out.println("estrllas es de "+calificacion);
        estrellas.setRating(Float.parseFloat(calificacion));
        estrellas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                calificacion= String.valueOf(rating);
                System.out.println("calificacion es "+calificacion);
            }
        });

        enviar.setOnClickListener(v -> {
            String variablecomentario;
            if(comentarios.getText().toString().equals("")){
                variablecomentario = null;
            }else{
                variablecomentario = comentarios.getText().toString();
            }
            String variableestrella= String.valueOf(estrellas.getRating());

            db.cambiarcomentarios(variablecomentario,variableestrella,terapia);
            Intent volverafestionarcliente = new Intent(Agregar_comentarios.this,MostrarClientePrueba.class);
            volverafestionarcliente.putExtra("email",newString);
            startActivity(volverafestionarcliente);
        });
    }
}