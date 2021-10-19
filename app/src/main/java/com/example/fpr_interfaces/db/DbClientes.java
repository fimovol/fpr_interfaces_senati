package com.example.fpr_interfaces.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.fpr_interfaces.Cliente;
import com.example.fpr_interfaces.entidades.Clientes;
import com.example.fpr_interfaces.entidades.Terapiasentidad;

import java.util.ArrayList;

public class DbClientes extends Dbhelper2{


    Context context;
    public DbClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public boolean comprobarsiexisteelterapeuta(String Usuario_terapeuta){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = null;

        cursor = db.rawQuery("select * from tb_terapeutas where usuario = '"+Usuario_terapeuta+"'",null);

        return cursor.moveToFirst();
    }
    public void agregartablaterapias(int comprado,String descripcion,String precio,int id_clientefk,int id_terapeutafk,String nombre){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("comprado",comprado);
        values.put("descripcion",descripcion);
        values.put("precio",precio);
        values.put("id_clientefk",id_clientefk);
        values.put("id_terapeutafk",id_terapeutafk);
        values.put("nombre",nombre);

        db.insert("terapias",null,values);
    }
    public void agregaratablaterapeuta(String nombre,String contra,String descrip,String foto,String saldo,String usuarios){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre",nombre);
        values.put("contrasena",contra);
        values.put("descripcion",descrip);
        values.put("foto",foto);
        values.put("saldo",saldo);
        values.put("usuario",usuarios);

        db.insert("tb_terapeutas",null,values);

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
    public ArrayList<Terapiasentidad> mostrarTerapiasPorTerapeuta(String terapeuta){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<Terapiasentidad> lista = new ArrayList<>();
        Terapiasentidad terapia = null;
        Cursor cursor = null;
        int id_terapeutafk=encontrarIdDelTerapeutaConUsuario(terapeuta);
        cursor = db.rawQuery(
                "select nombre,precio,descripcion from terapias WHERE id_terapeutafk = ?;"
                ,new String [] {String.valueOf(id_terapeutafk)});

        if(cursor.moveToFirst()){
            do{
                terapia = new Terapiasentidad();
                terapia.setNombre(cursor.getString(0));
                terapia.setPrecio(cursor.getString(1));
                terapia.setDescripcion(cursor.getString(2));

                lista.add(terapia);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
    public int encontrarIdDelTerapeutaConUsuario(String usuario){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery(
                "select id_terapeuta from tb_terapeutas where usuario = ?"
                ,new String [] {String.valueOf(usuario)});

        if(cursor.moveToFirst()){
            String id=cursor.getString(0);
            int identero = Integer.parseInt(id);
            return identero;
        }
        return 0;
    }
}
