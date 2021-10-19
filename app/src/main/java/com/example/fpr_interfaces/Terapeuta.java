package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Terapeuta extends AppCompatActivity {
    Bundle extras;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapeuta);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }
    }
    public void editar (View v){
        Intent editar = new Intent(Terapeuta.this,EditarServicio.class);
        editar.putExtra("email",newString);
        startActivity(editar);
    }

    public void deslogeate (View v){
        Intent deslogeate = new Intent(Terapeuta.this,MainActivity.class);
        startActivity(deslogeate);
    }
    public void agregarterapia(View v){
        Intent agregarterapia = new Intent(Terapeuta.this, com.example.fpr_interfaces.agregarterapia.class);
        startActivity(agregarterapia);
    }
}