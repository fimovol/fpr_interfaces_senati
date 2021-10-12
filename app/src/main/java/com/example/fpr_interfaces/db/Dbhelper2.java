package com.example.fpr_interfaces.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper2 extends SQLiteOpenHelper {
    private static final int DATABASE_VESRSION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
    private static final String TABLE_CONTACTOS = "tb_cliente";

    public Dbhelper2(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VESRSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_CONTACTOS+" (" +
                "id_cliente INTEGER primary key autoincrement," +
                "nombre TEXT NOT NULL," +
                "contrasena TEXT NOT NULL," +
                "saldo INTEGER NOT NULL," +
                "usuario TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+TABLE_CONTACTOS);
        onCreate(db);
    }
}