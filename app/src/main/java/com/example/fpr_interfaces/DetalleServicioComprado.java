package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fpr_interfaces.db.DbClientes;

public class DetalleServicioComprado extends AppCompatActivity {
    TextView usuariocorreo,descripciontextview,nombretextview;
    ImageView imageView;

    Bundle extras;
    String newString,id_terapeuta,nombre,descripcion,correodelterapeuta;
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
            setTitle(newString);
        }
        usuariocorreo.setText(correodelterapeuta);
        descripciontextview.setText(descripcion);
        nombretextview.setText(nombre);
        imageView.setImageResource(imagen);
    }
}