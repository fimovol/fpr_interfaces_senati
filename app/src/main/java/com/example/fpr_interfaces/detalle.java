package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fpr_interfaces.adaptadores.ListaAntecedentesTerapeutas;
import com.example.fpr_interfaces.db.DbClientes;

import java.util.List;

public class detalle extends AppCompatActivity {
    private ImageView imgItemDetalle;
    private TextView txt1,txt2,usuariotext;
    private CantanteModelo itemDetalle;
    String id_terapeuta;

    Bundle extras;
    String newString;

    private RecyclerView recyclerView;
    private ListaAntecedentesTerapeutas adaptador;
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
            id_terapeuta= extras.getString("id_terapeuta");
            setTitle(newString);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DbClientes dbconsulta = new DbClientes(this);

        adaptador=new ListaAntecedentesTerapeutas(dbconsulta.traeusuarioquecomproalterapeuta(id_terapeuta));
        recyclerView.setAdapter(adaptador);

    }
    public void anadirtitulo(){

    }
    public void initialViews(){
        recyclerView=(RecyclerView)findViewById(R.id.antecedentes);
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