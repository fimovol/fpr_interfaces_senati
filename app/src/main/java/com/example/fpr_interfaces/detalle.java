package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fpr_interfaces.db.DbClientes;

public class detalle extends AppCompatActivity {
    private ImageView imgItemDetalle;
    private TextView txt1,txt2,usuariotext;
    private CantanteModelo itemDetalle;
    String id_terapeuta;

    Bundle extras;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        initialViews();
        initialValues();
        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
            System.out.println("nada en el titulo :V");
        } else {
            newString= extras.getString("titulo");
            setTitle(newString);
        }
    }
    public void anadirtitulo(){

    }
    public void initialViews(){
        imgItemDetalle= findViewById(R.id.imageView);
        txt1= findViewById(R.id.textView2);
        txt2= findViewById(R.id.textView3);
        usuariotext=findViewById(R.id.usuario);
    }
    public void initialValues(){
        itemDetalle = (CantanteModelo) getIntent().getExtras().getSerializable("contra");

        id_terapeuta=itemDetalle.getId_terapia();
        DbClientes db = new DbClientes(this);
        String descripcion=db.descripcionDelTerapeuta(id_terapeuta);
        String usuario=db.usuarioDelTerapeuta(id_terapeuta);
        //cambiar
        imgItemDetalle.setImageResource(itemDetalle.getFotocantante());
        txt1.setText(itemDetalle.getCantante());
        txt2.setText(descripcion);
        usuariotext.setText("Correo: "+usuario);



        System.out.println(id_terapeuta+"asedasd");
    }
}