package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fpr_interfaces.db.DbClientes;

public class agregarterapia extends AppCompatActivity {
    EditText editserviceprecioaddterapia,editservisdescripaddterapia,editservicenombreaddterapia;
    Bundle extras;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarterapia);
        editserviceprecioaddterapia = findViewById(R.id.editserviceprecioaddterapia);
        editservisdescripaddterapia = findViewById(R.id.editservisdescripaddterapia);
        editservicenombreaddterapia = findViewById(R.id.editservicenombreaddterapia);
        extras = getIntent().getExtras();
        if(extras == null) {
            usuario= null;
        } else {
            usuario= extras.getString("email");
            setTitle(usuario);
        }
    }
    public void agregarterapia(View v){
        String precio=editserviceprecioaddterapia.getText().toString();
        String descrip=editservisdescripaddterapia.getText().toString();
        String nombre=editservicenombreaddterapia.getText().toString();
        if(!precio.equals("")){
            if(!descrip.equals("")){
                if(!nombre.equals("")){
                    DbClientes dbclientes = new DbClientes(agregarterapia.this);
                    int idterapeutafk=dbclientes.encontrarIdDelTerapeutaConUsuario(usuario);
                    dbclientes.agregartablaterapias(0,descrip,precio,0,idterapeutafk,nombre);
                    Intent volver = new Intent(agregarterapia.this,Terapeuta.class);
                    volver.putExtra("email",usuario);
                    startActivity(volver);
                }else {
                    Toast.makeText(this, "falta nombre", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "falta edscripcion", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "falta preio", Toast.LENGTH_SHORT).show();
        }
    }
}