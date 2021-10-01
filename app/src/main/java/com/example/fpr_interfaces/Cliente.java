package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

    }
    public void verperfilterapeuta(View v){
        Intent PerfilTerapeuta = new Intent(Cliente.this,PerfilTerapeuta.class);
        startActivity(PerfilTerapeuta);
    }

    public void deslogeate(View v){
        Intent deslogeate = new Intent(Cliente.this,MainActivity.class);
        startActivity(deslogeate);
    }

    public void elegirTerapia(View v){
        Intent elegirTerapia = new Intent(Cliente.this,elegirTerapia.class);
        startActivity(elegirTerapia);
    }
}