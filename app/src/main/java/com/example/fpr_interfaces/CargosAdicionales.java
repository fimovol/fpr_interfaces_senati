package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fpr_interfaces.db.DbClientes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CargosAdicionales extends AppCompatActivity {
    Bundle extras;
    String newString;
    TextView cargoadicional;
    int numeroaleatorio;
    String id_terapia;
    Button aumentarsaldodecliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargos_adicionales);

        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
        } else {
            newString= extras.getString("email");
            id_terapia= extras.getString("id_terapia");
            setTitle(newString);
        }

        aumentarsaldodecliente=findViewById(R.id.aumentarsaldodecliente);
        cargoadicional= findViewById(R.id.cargoadicional);
        numeroaleatorio = (int)(Math.random()*3)+2;
        cargoadicional.setText("S/."+numeroaleatorio);

        aumentarsaldodecliente.setOnClickListener(v -> {

            DbClientes db = new DbClientes(this);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                int id_cliente=db.encontrarIdDelClienteConUsuario(email);
                String saldo_cliente= db.traerSaldoClientes(email);
                String precio_terapia= db.traerPrecioTerapia(id_terapia);
                int saldo_clienteint = Integer.parseInt(saldo_cliente);
                int precio_terapiaint = Integer.parseInt(precio_terapia);
                precio_terapiaint = precio_terapiaint+numeroaleatorio;
                if(saldo_clienteint>=precio_terapiaint){
                    int preciofinal = saldo_clienteint - precio_terapiaint;
                    String predioFinalString = String.valueOf(preciofinal);
                    db.cambiarSaldoCliente(email,predioFinalString);

                    //añadiendo fecha
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myDateObj.format(myFormatObj);
                    System.out.println("After formatting: " + formattedDate);

                    db.comprarTerapia(id_cliente,id_terapia,formattedDate);
                    Intent i = new Intent(this,pruebabuscador.class);
                    i.putExtra("email",email);
                    startActivity(i);

                    CharSequence text = "GESTIONA TU CUENTA PA VER LO QUE COMPRASTE";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(this, text, duration);
                    toast.show();
                }else{

                    CharSequence text = "NO TE ALCANZA EL DINERO PARA ESTO";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(this, text, duration);
                    toast.show();
                }

            }
        });
    }

}