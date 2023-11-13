package com.example.sia.conexiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bd_roles extends SQLiteOpenHelper {

    public static final String ELIMINAR_TABLA_ROLES = "DROP TABLE roles;";
    public static final String CREAR_TABLA_ROLES = "CREATE TABLE roles (id_rol INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombre_rol TEXT NOT NULL," +
            " codigo_rol TEXT NOT NULL)";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "bd_roles";


    public bd_roles(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAR_TABLA_ROLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ELIMINAR_TABLA_ROLES);
        onCreate(sqLiteDatabase);
    }
}
