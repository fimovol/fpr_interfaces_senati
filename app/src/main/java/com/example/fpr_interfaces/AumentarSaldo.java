package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.fpr_interfaces.db.DbClientes;

public class AumentarSaldo extends AppCompatActivity {
    Bundle extras;
    String newString;

    EditText saldo;
    Button aumentarsaldodecliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aumentar_saldo);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            setTitle(newString);
        }
        aumentarsaldodecliente=findViewById(R.id.aumentarsaldodecliente);
        saldo=findViewById(R.id.saldo);



        aumentarsaldodecliente.setOnClickListener(v ->{
            String saldoaAumentar = saldo.getText().toString();
            if(!saldoaAumentar.equals("")){
                DbClientes db = new DbClientes(this);
                db.cambiarSaldoCliente(newString,saldoaAumentar);
                Intent gestioncliente = new Intent(AumentarSaldo.this,MostrarClientePrueba.class);
                gestioncliente.putExtra("email",newString);
                startActivity(gestioncliente);
                //consulta base de datos le pasa el usuario y el id y retorna al mostrar cliente
            }
        });
    }
}