package com.example.fpr_interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button logeo,registro;
    CheckBox checkterapeuta;
    EditText usuario,contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logeo = findViewById(R.id.logeo);
        registro = findViewById(R.id.registro);
        checkterapeuta = findViewById(R.id.checkterapeuta);
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
    }
    public void registro(View v){
        String usuari = usuario.getText().toString();
        String contra = contrasena.getText().toString();
        Intent terapeuta = new Intent(MainActivity.this,Registro.class);
        startActivity(terapeuta);

    }
    public void logeo(View v){


        String usuari = usuario.getText().toString();
        String contra = contrasena.getText().toString();
        if(!usuari.isEmpty() &&
                !contra.isEmpty()){
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(usuari,contra)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(checkterapeuta.isChecked()){
                                    Intent terapeuta = new Intent(MainActivity.this,Terapeuta.class);
                                    terapeuta.putExtra("email",task.getResult().getUser().getEmail());
                                    startActivity(terapeuta);
                                }else{
                                    Intent terapeuta = new Intent(MainActivity.this,pruebabuscador.class);
                                    terapeuta.putExtra("email",task.getResult().getUser().getEmail());
                                    startActivity(terapeuta);
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "error al logearse", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}