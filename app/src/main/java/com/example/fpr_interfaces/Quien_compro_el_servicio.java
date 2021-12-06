package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.fpr_interfaces.db.DbClientes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Quien_compro_el_servicio extends AppCompatActivity {
    TextView comentario,correo,mostrarsinohayestrellas,hora;
    RatingBar estrellas;
    DbClientes dbconsulta = new DbClientes(this);
    Bundle extras;
    String id_terapia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quien_compro_el_servicio);

        extras = getIntent().getExtras();
        if(extras == null) {

            System.out.println("nada en el titulo :V");
        } else {
            id_terapia= extras.getString("id_terapia");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                setTitle(email);
            }
        }
        comentario=findViewById(R.id.comentario);
        estrellas=findViewById(R.id.estrellas);
        correo=findViewById(R.id.correo);
        mostrarsinohayestrellas=findViewById(R.id.mostrarsinohayestrellas);
        hora=findViewById(R.id.hora);

        String estrella=dbconsulta.traerestrellasdeclientesconid_terapia(id_terapia);
        String comentariostring=dbconsulta.traercomentariodeclientesconid_terapia(id_terapia);
        String usuario=dbconsulta.traerusuariodeclientesconid_terapia(id_terapia);
        String fecha = dbconsulta.traerfechadeclientesconid_terapia(id_terapia);

        if(estrella!=null){
            mostrarsinohayestrellas.setVisibility(View.INVISIBLE);
            estrellas.setVisibility(View.VISIBLE);
            estrellas.setRating(Float.parseFloat(estrella));
        }else{
            estrellas.setVisibility(View.INVISIBLE);
            mostrarsinohayestrellas.setVisibility(View.VISIBLE);
        }
        if(comentariostring!=null){
            comentario.setText(comentariostring);
        }else{
            comentario.setText("no hay comentarios");
        }
        if(usuario!=null){
            correo.setText(usuario);
        }else{
            correo.setText("no hay usuario que mostrar");
        }
        if(fecha!=null){
            hora.setText(fecha);
        }else{
            hora.setText("no hay fecha que mostrar");
        }

        System.out.println("usuario: "+dbconsulta.traerusuariodeclientesconid_terapia(id_terapia));
        System.out.println("comentarios: "+dbconsulta.traercomentariodeclientesconid_terapia(id_terapia));
        System.out.println("estrellas: "+dbconsulta.traerestrellasdeclientesconid_terapia(id_terapia));
    }
}