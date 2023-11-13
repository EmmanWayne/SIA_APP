package com.example.sia.consultas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sia.clases.clase_empleados;
import com.example.sia.conexiones.bd_empleados;

import java.util.ArrayList;

public class consultas_empleados extends bd_empleados {

    private static final String TABLA_EMPLEADOS = "empleados";
    Context context;

    public consultas_empleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long guardarEmpleado(String nombres_empleado, String apellidos_empleado, String identidad_empleado, String cargo_empleado, String telefono_empleado, String direccion_empleado) {

        long id_empleado = 0;

        try {
            bd_empleados bd_empleados = new bd_empleados(context);
            SQLiteDatabase db = bd_empleados.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombres_empleado", nombres_empleado);
            values.put("apellidos_empleado", apellidos_empleado);
            values.put("identidad_empleado", identidad_empleado);
            values.put("cargo_empleado", cargo_empleado);
            values.put("telefono_empleado", telefono_empleado);
            values.put("direccion_empleado", direccion_empleado);

            id_empleado = db.insert(TABLA_EMPLEADOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id_empleado;
    }

    public ArrayList<clase_empleados> mostrarEmpleados() {

        bd_empleados bd_empleados = new bd_empleados(context);
        SQLiteDatabase db = bd_empleados.getWritableDatabase();

        ArrayList<clase_empleados> lista = new ArrayList<>();
        clase_empleados clase_empleados;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLA_EMPLEADOS, null);

        if (cursor.moveToFirst()) {
            do {
                clase_empleados = new clase_empleados();
                clase_empleados.setId_empleado(cursor.getInt(0));
                clase_empleados.setNombres_empleado(cursor.getString(1));
                clase_empleados.setApellidos_empleado(cursor.getString(2));
                clase_empleados.setIdentidad_empleado(cursor.getString(3));
                clase_empleados.setCargo_empleado(cursor.getString(4));
                clase_empleados.setTelefono_empleado(cursor.getString(5));
                clase_empleados.setDireccion_empleado(cursor.getString(6));
                lista.add(clase_empleados);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista;
    }

    public clase_empleados verEmpleado(int id_empleado) {

        bd_empleados bd_empleados = new bd_empleados(context);
        SQLiteDatabase db = bd_empleados.getWritableDatabase();

        clase_empleados clase_empleados = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLA_EMPLEADOS + " WHERE id_empleado = " + id_empleado + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            clase_empleados = new clase_empleados();
            clase_empleados.setId_empleado(cursor.getInt(0));
            clase_empleados.setNombres_empleado(cursor.getString(1));
            clase_empleados.setApellidos_empleado(cursor.getString(2));
            clase_empleados.setIdentidad_empleado(cursor.getString(3));
            clase_empleados.setCargo_empleado(cursor.getString(4));
            clase_empleados.setTelefono_empleado(cursor.getString(5));
            clase_empleados.setDireccion_empleado(cursor.getString(6));
        }

        cursor.close();

        return clase_empleados;
    }

    public boolean actualizarEmpleado(int id_empleado, String nombres_empleado, String apellidos_empleado, String identidad_empleado, String cargo_empleado, String telefono_empleado, String direccion_empleado) {

        boolean correcto = false;

        bd_empleados bd_empleados = new bd_empleados(context);
        SQLiteDatabase db = bd_empleados.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_EMPLEADOS + " SET nombres_empleado = '" + nombres_empleado + "', apellidos_empleado = '" + apellidos_empleado + "', identidad_empleado = '" + identidad_empleado + "', cargo_empleado = '" + cargo_empleado + "', telefono_empleado = '" + telefono_empleado + "', direccion_empleado = '" + direccion_empleado + "' WHERE id_empleado ='" + id_empleado + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarEmpleados(int id_empleado) {

        boolean correcto = false;

        bd_empleados bd_empleados = new bd_empleados(context);
        SQLiteDatabase db = bd_empleados.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_EMPLEADOS + " WHERE id_empleado = '" + id_empleado + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
