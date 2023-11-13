package com.example.sia.consultas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sia.clases.clase_usuarios;
import com.example.sia.conexiones.bd_usuarios;

import java.util.ArrayList;

public class consultas_usuarios extends bd_usuarios {

    private static final String TABLA_USUARIOS = "usuarios";
    Context context;

    public consultas_usuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long guardarUsuario(String nombre_usuario, String contrasena_usuario, String rol_usuario) {


        long id_usuario = 0;

        try {
            bd_usuarios bd_usuarios = new bd_usuarios(context);
            SQLiteDatabase db = bd_usuarios.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_usuario", nombre_usuario);
            values.put("contrasena_usuario", contrasena_usuario);
            values.put("rol_usuario", rol_usuario);

            id_usuario = db.insert(TABLA_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id_usuario;
    }

    public ArrayList<clase_usuarios> mostrarUsuarios() {

        bd_usuarios bd_usuarios = new bd_usuarios(context);
        SQLiteDatabase db = bd_usuarios.getWritableDatabase();

        ArrayList<clase_usuarios> lista = new ArrayList<>();
        clase_usuarios clase_usuarios;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS, null);

        if (cursor.moveToFirst()) {
            do {
                clase_usuarios = new clase_usuarios();
                clase_usuarios.setId_usuario(cursor.getInt(0));
                clase_usuarios.setNombre_usuario(cursor.getString(1));
                clase_usuarios.setContrasena_usuario(cursor.getString(2));
                clase_usuarios.setRol_usuario(cursor.getString(3));
                lista.add(clase_usuarios);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista;
    }

    public clase_usuarios verUsuarios(int id_usuario) {

        bd_usuarios bd_usuarios = new bd_usuarios(context);
        SQLiteDatabase db = bd_usuarios.getWritableDatabase();

        clase_usuarios clase_usuarios = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS + " WHERE id_usuario = " + id_usuario + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            clase_usuarios = new clase_usuarios();
            clase_usuarios.setId_usuario(cursor.getInt(0));
            clase_usuarios.setNombre_usuario(cursor.getString(1));
            clase_usuarios.setContrasena_usuario(cursor.getString(2));
            clase_usuarios.setRol_usuario(cursor.getString(3));
        }

        cursor.close();

        return clase_usuarios;
    }

    public boolean actualizarUsuario(int id_usuario, String nombre_usuario, String contrasena_usuario, String rol_usuario) {

        boolean correcto = false;

        bd_usuarios bd_usuarios = new bd_usuarios(context);
        SQLiteDatabase db = bd_usuarios.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_USUARIOS + " SET nombre_usuario = '" + nombre_usuario + "', contrasena_usuario = '" + contrasena_usuario + "', rol_usuario = '" + rol_usuario + "' WHERE id_usuario ='" + id_usuario + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarUsuario(int id_usuario) {

        boolean correcto = false;

        bd_usuarios bd_usuarios = new bd_usuarios(context);
        SQLiteDatabase db = bd_usuarios.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_USUARIOS + " WHERE id_usuario = '" + id_usuario + "'");
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
