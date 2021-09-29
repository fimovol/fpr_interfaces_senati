package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    Button logeo,registro;
    CheckBox checkterapeuta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logeo = findViewById(R.id.logeo);
        registro = findViewById(R.id.registro);
        checkterapeuta = findViewById(R.id.checkterapeuta);
    }
    public void logeo(View v){
        if(checkterapeuta.isChecked()){
            Intent terapeuta = new Intent(MainActivity.this,Terapeuta.class);
            startActivity(terapeuta);
        }else{
            Intent cliente = new Intent(MainActivity.this,Cliente.class);
            startActivity(cliente);
        }

    }
    public void registro(View v){
        Intent registro = new Intent(MainActivity.this,Registro.class);
        startActivity(registro);
    }
}