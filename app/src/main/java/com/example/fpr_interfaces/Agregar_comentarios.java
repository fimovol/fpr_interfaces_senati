package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

public class Agregar_comentarios extends AppCompatActivity {
    RatingBar estrellas;

    Bundle extras;
    String newString;
    String calificacion;
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
            setTitle(newString);
        }

        estrellas = findViewById(R.id.estrellas);

        System.out.println("estrllas es de "+calificacion);
        estrellas.setRating(Float.parseFloat(calificacion));
        estrellas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                calificacion= String.valueOf(rating);
                System.out.println("calificacion es "+calificacion);
            }
        });
    }
}