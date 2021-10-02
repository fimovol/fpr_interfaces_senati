package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class Cliente extends AppCompatActivity {
    Bundle extras;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }
    }
    public void verperfilterapeuta(View v){
        Intent PerfilTerapeuta = new Intent(Cliente.this,PerfilTerapeuta.class);
        PerfilTerapeuta.putExtra("email",newString);
        startActivity(PerfilTerapeuta);
    }

    public void deslogeate(View v){
        FirebaseAuth.getInstance().signOut();
        Intent deslogeate = new Intent(Cliente.this,MainActivity.class);
        startActivity(deslogeate);
    }

    public void elegirTerapia(View v){
        Intent elegirTerapia = new Intent(Cliente.this,elegirTerapia.class);
        elegirTerapia.putExtra("email",newString);
        startActivity(elegirTerapia);
    }
}