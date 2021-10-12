package com.example.fpr_interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fpr_interfaces.db.DbClientes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {
    EditText usuarioregitro,contrasenaregistro,contrasenaregistro2,saldodecleente,nombrecliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuarioregitro = findViewById(R.id.usuarioregitro);
        contrasenaregistro = findViewById(R.id.contrasenaregistro);
        contrasenaregistro2 = findViewById(R.id.contrasenaregistro2);
        saldodecleente = findViewById(R.id.saldodecleente);
        nombrecliente = findViewById(R.id.nombrecliente);
    }
    public void registrateusuario(View v){
        String uduari = usuarioregitro.getText().toString();
        String contra = contrasenaregistro.getText().toString();
        String contra2 = contrasenaregistro2.getText().toString();
        String saldo = saldodecleente.getText().toString();
        String nombre = nombrecliente.getText().toString();

        String cadena1 = new String(contra);
        String cadena2 = new String(contra2);
        if(cadena1.equals(cadena2)){
            if(!uduari.isEmpty() &&
                    !contra.isEmpty() &&
                    !contra2.isEmpty()){
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(uduari,contra)
                        .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent pruebabuscador = new Intent(Registro.this,pruebabuscador.class);
                                    pruebabuscador.putExtra("email",task.getResult().getUser().getEmail());
                                    guardarEnBasededatos(nombre,contra,saldo,uduari);
                                    startActivity(pruebabuscador);
                                } else {
                                    Toast.makeText(Registro.this,
                                            "error al crear tu usuario",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }else{
                Toast.makeText(Registro.this,
                        "datos incompletos",
                        Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(Registro.this,
                    "contraseña diferente",
                    Toast.LENGTH_LONG).show();
        }

        //Intent registrateusuario = new Intent(Registro.this,MainActivity.class);
        //startActivity(registrateusuario);
    }
    public void guardarEnBasededatos(String nombre,String contrasena,String saldo,String usuario){
        DbClientes dbcontactos = new DbClientes(this);
        int saldoint =Integer.parseInt(saldo);
        long id=dbcontactos.insertarClientes(nombre,contrasena,saldoint,usuario);
    }
}