package com.example.sia.conexiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bd_usuarios extends SQLiteOpenHelper {

    public static final String ELIMINAR_TABLA_USUARIOS = "DROP TABLE usuarios;";
    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE usuarios (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " nombre_usuario TEXT NOT NULL,\n" +
            " contrasena_usuario TEXT NOT NULL,\n" +
            " rol_usuario TEXT NOT NULL)";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "bd_usuarios";

    public bd_usuarios(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ELIMINAR_TABLA_USUARIOS);
        onCreate(sqLiteDatabase);
    }
}
