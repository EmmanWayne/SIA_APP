package com.example.sia.sia_usuarios;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.clases.clase_roles;
import com.example.sia.clases.clase_usuarios;
import com.example.sia.conexiones.bd_roles;
import com.example.sia.consultas.consultas_usuarios;

import java.util.ArrayList;

public class usuarios_actualizar extends AppCompatActivity {

    Spinner comboIdentidades, comboRoles;
    ArrayList<String> listaRoles;
    ArrayList<clase_roles> rolesRegistrados;
    bd_roles conn;

    EditText txtUsuario, txtContraseña, txtRol;
    Button btnActualizar;
    boolean correcto = false;
    clase_usuarios clase;
    int id_usuario = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_usuarios_actualizar);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContraseña = findViewById(R.id.txtContrasena);
        txtRol = findViewById(R.id.txtRol);
        comboIdentidades = findViewById(R.id.comboIdentidades);
        comboRoles = findViewById(R.id.comboRoles);
        btnActualizar = findViewById(R.id.btnActualizar);
        txtUsuario.setInputType(InputType.TYPE_NULL);
        txtRol.setInputType(InputType.TYPE_NULL);

        conn = new bd_roles(getApplicationContext());
        comboRoles = (Spinner) findViewById(R.id.comboRoles);
        consultarListaRoles();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, android.R.layout.select_dialog_singlechoice, listaRoles);
        comboRoles.setAdapter(adaptador);
        comboRoles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
                if (position != 0) {
                    txtRol.setText(rolesRegistrados.get(position - 1).getNombre_rol());
                } else {
                    txtRol.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id_usuario = Integer.parseInt(null);
            } else {
                id_usuario = extras.getInt("ID");
            }
        } else {
            id_usuario = (int) savedInstanceState.getSerializable("ID");
        }

        final consultas_usuarios consultas = new consultas_usuarios(usuarios_actualizar.this);
        clase = consultas.verUsuarios(id_usuario);

        if (clase != null) {
            txtUsuario.setText(clase.getNombre_usuario());
            txtContraseña.setText(clase.getContrasena_usuario());
            txtRol.setText(clase.getRol_usuario());
        }

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtUsuario.getText().toString().equals("") && !txtContraseña.getText().toString().equals("")) {
                    correcto = consultas.actualizarUsuario(id_usuario, txtUsuario.getText().toString(), txtContraseña.getText().toString(), txtRol.getText().toString());

                    if (correcto) {
                        Toast.makeText(usuarios_actualizar.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show();
                        registros();
                    } else {
                        Toast.makeText(usuarios_actualizar.this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(usuarios_actualizar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void consultarListaRoles() {
        SQLiteDatabase db = conn.getReadableDatabase();
        clase_roles roles = null;
        rolesRegistrados = new ArrayList<clase_roles>();
        Cursor cursor = db.rawQuery("SELECT * FROM roles", null);
        while (cursor.moveToNext()) {
            roles = new clase_roles();
            roles.setCodigo_rol(cursor.getString(1));
            roles.setNombre_rol(cursor.getString(2));
            Log.i("codigo_rol", roles.getCodigo_rol());
            Log.i("nombre_rol", roles.getNombre_rol());
            rolesRegistrados.add(roles);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaRoles = new ArrayList<String>();
        listaRoles.add("Seleccione un rol");
        for (int i = 0; i < rolesRegistrados.size(); i++) {
            listaRoles.add(rolesRegistrados.get(i).getCodigo_rol() + " - " + rolesRegistrados.get(i).getNombre_rol());
        }
    }

    private void registros() {
        Intent intent = new Intent(this, usuarios_registrados.class);
        startActivity(intent);
        finish();
    }
}