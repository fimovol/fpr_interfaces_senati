package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.fpr_interfaces.db.DbClientes;

public class Editarantecedentesterapeuta extends AppCompatActivity {
    Bundle extras;
    String newString;
    DbClientes db = new DbClientes(this);
    Button editarperfil;
    EditText editarnombre,editardescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarantecedentesterapeuta);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }

        editarperfil=findViewById(R.id.editarperfil);
        editarnombre=findViewById(R.id.editarnombre);
        editardescripcion=findViewById(R.id.editardescripcion);

        editarnombre.setText(db.traernombreterapeutaconusuario(newString));
        editardescripcion.setText(db.traerdescripcionterapeutaconusuario(newString));

        editarperfil.setOnClickListener( v -> {
            String nombre = editarnombre.getText().toString();
            String descripcion = editardescripcion.getText().toString();
            db.editarantecedentes(nombre,descripcion,newString);
            Intent i = new Intent(this, Terapeuta.class);
            i.putExtra("email",newString);
            startActivity(i);
        });
    }

}