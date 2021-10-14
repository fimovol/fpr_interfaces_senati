package com.example.fpr_interfaces.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.fpr_interfaces.Cliente;
import com.example.fpr_interfaces.entidades.Clientes;

import java.util.ArrayList;

public class DbClientes extends Dbhelper2{


    Context context;
    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarClientes(String nombre,String contrasena,int saldo,String usuario){
        long id = 0;
        try{
            Dbhelper2 dbhelper = new Dbhelper2(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("contrasena",contrasena);
            values.put("saldo",saldo);
            values.put("usuario",usuario);

            id = db.insert("tb_cliente",null,values);
        }catch (Exception ex){
           ex.toString();
        }

        return id;
    }
    public ArrayList<Clientes> mostraClientes(){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<Clientes> listaclientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("select * from tb_cliente",null);

        if(cursor.moveToFirst()){
            do{
                cliente = new Clientes();
                cliente.setNomre(cursor.getString(1));
                cliente.setContrasena(cursor.getString(2));
                cliente.setSaldo(cursor.getString(3));
                cliente.setUsuario(cursor.getString(4));

                listaclientes.add(cliente);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listaclientes;
    }
}
