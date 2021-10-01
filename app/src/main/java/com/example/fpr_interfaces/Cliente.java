package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Cliente extends AppCompatActivity {
    TextView asd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        asd = findViewById(R.id.textView2);
        String newString;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            asd.setText(newString);
        }
    }
    public void verperfilterapeuta(View v){
        Intent PerfilTerapeuta = new Intent(Cliente.this,PerfilTerapeuta.class);
        startActivity(PerfilTerapeuta);
    }

    public void deslogeate(View v){
        FirebaseAuth.getInstance().signOut();
        Intent deslogeate = new Intent(Cliente.this,MainActivity.class);
        startActivity(deslogeate);
    }

    public void elegirTerapia(View v){
        Intent elegirTerapia = new Intent(Cliente.this,elegirTerapia.class);
        startActivity(elegirTerapia);
    }
}