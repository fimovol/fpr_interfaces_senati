package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.fpr_interfaces.db.DbClientes;

public class DetalleServicioComprado extends AppCompatActivity {
    TextView usuariocorreo,descripciontextview,nombretextview;
    ImageView imageView;

    RatingBar estrellas;
    DbClientes dbconsulta = new DbClientes(this);
    Bundle extras;
    String newString,id_terapeuta,nombre,descripcion,correodelterapeuta,terapia,comprobarcomentario;
    int imagen;
    DbClientes db = new DbClientes(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_servicio_comprado);

        usuariocorreo=findViewById(R.id.usuariocorreo);
        descripciontextview=findViewById(R.id.descripcion);
        nombretextview=findViewById(R.id.nombre);
        imageView=findViewById(R.id.imageView);
        estrellas=findViewById(R.id.estrellas);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
            System.out.println("nada en el titulo :V");
        } else {
            newString= extras.getString("titulo");
            id_terapeuta = extras.getString("id_terapeuta");
            descripcion = db.descripcionDelTerapeuta(id_terapeuta);
            correodelterapeuta = db.usuarioDelTerapeuta(id_terapeuta);
            nombre = extras.getString("nombre");
            imagen= Integer.parseInt(extras.getString("imagen"));
            terapia=extras.getString("terapia");
            setTitle(newString);
        }
        comprobarcomentario=dbconsulta.traerestrellasdeclientesconid_terapia(terapia);
        if(comprobarcomentario==null){
            System.out.println("es nulll "+comprobarcomentario);
        }else{
            estrellas.setRating(Float.parseFloat(comprobarcomentario));
            estrellas.setIsIndicator(true);
            System.out.println("tiene algo aqui "+comprobarcomentario);
        }

        usuariocorreo.setText(correodelterapeuta);
        descripciontextview.setText(descripcion);
        nombretextview.setText(nombre);
        imageView.setImageResource(imagen);

        estrellas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Intent comentarios = new Intent(DetalleServicioComprado.this,Agregar_comentarios.class);
                String calificacion = String.valueOf(rating);
                comentarios.putExtra("calificacion",calificacion);
                comentarios.putExtra("terapia",terapia);
                comentarios.putExtra("titulo",newString);
                startActivity(comentarios);
            }
        });
    }
}