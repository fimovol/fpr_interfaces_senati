package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fpr_interfaces.db.DbClientes;

public class EditarServicio extends AppCompatActivity {
    Bundle extras;
    String newString,id_terapia,getNombre,getPrecio,getDescripcion;
    EditText editserviceprecio,editservisdescrip,editservicenombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servicio);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
            id_terapia=null;
        } else {
            newString= extras.getString("email");
            id_terapia= extras.getString("id_terapia");
            getNombre= extras.getString("getNombre");
            getPrecio= extras.getString("getPrecio");
            getDescripcion= extras.getString("getDescripcion");
            setTitle(newString);
        }

        editservicenombre= findViewById(R.id.editservicenombre);
        editservisdescrip= findViewById(R.id.editservisdescrip);
        editserviceprecio= findViewById(R.id.editserviceprecio);

        editservicenombre.setText(getNombre);
        editservisdescrip.setText(getDescripcion);
        editserviceprecio.setText(getPrecio);

    }
    public void editservice(View v ){
        String nombre=editservicenombre.getText().toString();
        String descrip=editservisdescrip.getText().toString();
        String preciio=editserviceprecio.getText().toString();
        DbClientes db = new DbClientes(this);
        db.cambiarTablaTerapiasEditar(nombre,descrip,preciio,id_terapia);
        //metodo para cambiar la tabla
        //cambiarTablaTerapiasEditar
        Intent editar = new Intent(EditarServicio.this,Terapeuta.class);
        editar.putExtra("email",newString);
        startActivity(editar);
    }
}