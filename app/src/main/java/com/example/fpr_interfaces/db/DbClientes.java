package com.example.fpr_interfaces.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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
}
