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
}