package com.example.sia.conexiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bd_empleados extends SQLiteOpenHelper {

    public static final String ELIMINAR_TABLA_EMPLEADOS = "DROP TABLE empleados;";
    public static final String CREAR_TABLA_EMPLEADOS = "CREATE TABLE empleados (id_empleado INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombres_empleado TEXT NOT NULL," +
            " apellidos_empleado TEXT NOT NULL," +
            " identidad_empleado TEXT NOT NULL," +
            " cargo_empleado TEXT NOT NULL," +
            " telefono_empleado TEXT NOT NULL," +
            " direccion_empleado TEXT NOT NULL)";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "bd_empleados";


    public bd_empleados(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAR_TABLA_EMPLEADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ELIMINAR_TABLA_EMPLEADOS);
        onCreate(sqLiteDatabase);
    }
}
