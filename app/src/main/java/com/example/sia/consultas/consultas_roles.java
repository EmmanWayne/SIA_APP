package com.example.sia.consultas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sia.clases.clase_roles;
import com.example.sia.conexiones.bd_roles;

import java.util.ArrayList;

public class consultas_roles extends bd_roles {

    private static final String TABLA_ROLES = "roles";
    Context context;

    public consultas_roles(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long guardarRol(String nombre_rol, String codigo_rol) {

        long id_rol = 0;

        try {
            bd_roles bd_roles = new bd_roles(context);
            SQLiteDatabase db = bd_roles.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_rol", nombre_rol);
            values.put("codigo_rol", codigo_rol);

            id_rol = db.insert(TABLA_ROLES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id_rol;
    }

    public ArrayList<clase_roles> mostrarRoles() {

        bd_roles bd_roles = new bd_roles(context);
        SQLiteDatabase db = bd_roles.getWritableDatabase();

        ArrayList<clase_roles> listaRoles = new ArrayList<>();
        clase_roles clase_roles;
        Cursor cursorRoles;

        cursorRoles = db.rawQuery("SELECT * FROM " + TABLA_ROLES, null);

        if (cursorRoles.moveToFirst()) {
            do {
                clase_roles = new clase_roles();
                clase_roles.setId_rol(cursorRoles.getInt(0));
                clase_roles.setNombre_rol(cursorRoles.getString(1));
                clase_roles.setCodigo_rol(cursorRoles.getString(2));
                listaRoles.add(clase_roles);
            } while (cursorRoles.moveToNext());
        }

        cursorRoles.close();

        return listaRoles;
    }

    public clase_roles verRol(int id_rol) {

        bd_roles bd_roles = new bd_roles(context);
        SQLiteDatabase db = bd_roles.getWritableDatabase();

        clase_roles clase_roles = null;
        Cursor cursorRoles;

        cursorRoles = db.rawQuery("SELECT * FROM " + TABLA_ROLES + " WHERE id_rol = " + id_rol + " LIMIT 1", null);

        if (cursorRoles.moveToFirst()) {
            clase_roles = new clase_roles();
            clase_roles.setId_rol(cursorRoles.getInt(0));
            clase_roles.setNombre_rol(cursorRoles.getString(1));
            clase_roles.setCodigo_rol(cursorRoles.getString(2));
        }

        cursorRoles.close();

        return clase_roles;
    }

    public boolean actualizarRol(int id_rol, String nombre_rol, String codigo_rol) {

        boolean correcto = false;

        bd_roles bd_roles = new bd_roles(context);
        SQLiteDatabase db = bd_roles.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_ROLES + " SET nombre_rol = '" + nombre_rol + "', codigo_rol = '" + codigo_rol + "' WHERE id_rol ='" + id_rol + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarRol(int id_rol) {

        boolean correcto = false;

        bd_roles bd_roles = new bd_roles(context);
        SQLiteDatabase db = bd_roles.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_ROLES + " WHERE id_rol = '" + id_rol + "'");
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
