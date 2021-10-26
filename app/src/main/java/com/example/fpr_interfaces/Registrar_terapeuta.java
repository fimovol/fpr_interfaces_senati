package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fpr_interfaces.db.DbClientes;


import java.util.ArrayList;

public class Registrar_terapeuta extends AppCompatActivity {
    DbClientes dbclientes = new DbClientes(Registrar_terapeuta.this);
    Bundle extras;
    String email,contrasena;
    Button button;
    EditText descripcion;
    String foto="R.drawable._6";
    private RadioGroup radio;
    private RadioButton radiobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_terapeuta);

        radio=(RadioGroup)findViewById(R.id.radioGroup);
        descripcion=findViewById(R.id.descripcion);

        button=findViewById(R.id.button);
        extras = getIntent().getExtras();
        if(extras == null) {
            email= null;
        } else {
            email= extras.getString("usuari");
            contrasena= extras.getString("contra");
            setTitle(email);
        }
        button.setOnClickListener(v -> {

            metodo(foto,descripcion.getText().toString());
            Intent terapeuta = new Intent(Registrar_terapeuta.this,Terapeuta.class);
            terapeuta.putExtra("email",email);
            startActivity(terapeuta);
        });


    }
    public void checkitem(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButton2:
                if (checked)
                    foto="R.drawable._8";
                    break;
            case R.id.radioButton:
                if (checked)
                    foto="R.drawable._6";
                    break;
        }
    }

    public void metodo(String foto,String descrip){

        String nombre=dbclientes.traerNombreClientes(email);
        String saldo=dbclientes.traerSaldoClientes(email);
        System.out.println(nombre);
        System.out.println(saldo);
        dbclientes.agregaratablaterapeuta(nombre,contrasena,descrip,foto,saldo,email);
    }
}