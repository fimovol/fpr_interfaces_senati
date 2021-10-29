package com.example.fpr_interfaces.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.fpr_interfaces.CantanteModelo;
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
                "select nombre,precio,descripcion,id_terapia,comprado  from terapias WHERE id_terapeutafk = ?;"
                ,new String [] {String.valueOf(id_terapeutafk)});

        if(cursor.moveToFirst()){
            do{
                terapia = new Terapiasentidad();
                terapia.setNombre(cursor.getString(0));
                terapia.setPrecio(cursor.getString(1));
                terapia.setDescripcion(cursor.getString(2));
                terapia.setId_terapia(cursor.getString(3));
                terapia.setCompradoSiNo(cursor.getString(4));

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
    public void cambiarTablaTerapiasEditar(String nombre,String descrip,String preciio,String id){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Cursor cursor = null;
        cursor =db.rawQuery(
                "UPDATE terapias SET descripcion = ?, precio= ?, nombre= ? WHERE id_terapia = ?;"
                ,new String [] {String.valueOf(descrip)
                        ,String.valueOf(preciio)
                        ,String.valueOf(nombre)
                        ,String.valueOf(id)});
        cursor.getCount();
    }
    public String traerNombreClientes(String clientes){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("select nombre from tb_cliente where usuario=?",
                new String [] {String.valueOf(clientes)});

        if(cursor.moveToFirst()){
                cliente=cursor.getString(0);
        }
        cursor.close();
        return cliente;
    }
    public String traerSaldoClientes(String clientes){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("select saldo from tb_cliente where usuario=?",
                new String [] {String.valueOf(clientes)});

        if(cursor.moveToFirst()){
            cliente=cursor.getString(0);
        }
        cursor.close();
        return cliente;
    }
    public ArrayList<CantanteModelo> mostrarTerapias(){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<CantanteModelo> listaclientes = new ArrayList<>();
        CantanteModelo cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT t.nombre, p.descripcion,t.foto,p.precio,t.id_terapeuta,p.id_terapia FROM tb_terapeutas as t INNER JOIN terapias as p on t.id_terapeuta = p.id_terapeutafk where p.comprado = 0",null);

        if(cursor.moveToFirst()){
            do{
                cliente = new CantanteModelo();
                cliente.setCantante(cursor.getString(0));
                cliente.setNacionalidad(cursor.getString(1));
                cliente.setFotocantante(cursor.getInt(2));
                cliente.setPrecio(cursor.getString(3));
                cliente.setId_terapia(cursor.getString(4));
                cliente.setId_terapia_estesi(cursor.getString(5));

                listaclientes.add(cliente);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listaclientes;
    }
    public String descripcionDelTerapeuta(String id_terapeuta){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String cliente = null;
        Cursor cursor = null;
        cursor = db.rawQuery("select descripcion from tb_terapeutas where id_terapeuta=?",
                new String [] {String.valueOf(id_terapeuta)});
        if(cursor.moveToFirst()){
            cliente=cursor.getString(0);
        }
        cursor.close();
        return cliente;
    }
    public String usuarioDelTerapeuta(String id_terapeuta){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String cliente = null;
        Cursor cursor = null;
        cursor = db.rawQuery("select usuario from tb_terapeutas where id_terapeuta=?",
                new String [] {String.valueOf(id_terapeuta)});
        if(cursor.moveToFirst()){
            cliente=cursor.getString(0);
        }
        cursor.close();
        return cliente;
    }
    public void eliminarTerapia(String idDeLaTerapia){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Cursor cursor = null;
        cursor =db.rawQuery(
                "DELETE FROM terapias WHERE id_terapia=?;"
                ,new String [] {String.valueOf(idDeLaTerapia)});
        cursor.getCount();
    }
    public void comprarTerapia(int id_clientefk ,String id_terapia){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Cursor cursor = null;
        cursor =db.rawQuery(
                "UPDATE terapias SET id_clientefk = ?,comprado=1 WHERE id_terapia = ?;"
                ,new String [] {String.valueOf(id_clientefk)
                        ,String.valueOf(id_terapia)});
        cursor.getCount();
    }
    public int encontrarIdDelClienteConUsuario(String usuario){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery(
                "select id_cliente from tb_cliente where usuario = ?"
                ,new String [] {String.valueOf(usuario)});

        if(cursor.moveToFirst()){
            String id=cursor.getString(0);
            int identero = Integer.parseInt(id);
            return identero;
        }
        return 0;
    }
    public String traerPrecioTerapia(String id_terapia){
        Dbhelper2 dbhelper = new Dbhelper2(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("select precio from terapias where id_terapia=?",
                new String [] {String.valueOf(id_terapia)});

        if(cursor.moveToFirst()){
            cliente=cursor.getString(0);
        }
        cursor.close();
        return cliente;
    }
}
