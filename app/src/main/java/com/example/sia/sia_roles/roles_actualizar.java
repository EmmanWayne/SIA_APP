package com.example.sia.sia_roles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.clases.clase_roles;
import com.example.sia.consultas.consultas_roles;

public class roles_actualizar extends AppCompatActivity {

    EditText txtNombre_rol, txtCodigo_rol;
    Button btnActualizar;
    boolean correcto = false;
    clase_roles clase_roles;
    int id_rol = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_roles_actualizar);

        txtNombre_rol = findViewById(R.id.txtNombre_Rol);
        txtCodigo_rol = findViewById(R.id.txtCodigo_Rol);
        btnActualizar = findViewById(R.id.btnActualizar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id_rol = Integer.parseInt(null);
            } else {
                id_rol = extras.getInt("ID");
            }
        } else {
            id_rol = (int) savedInstanceState.getSerializable("ID");
        }

        final consultas_roles consultas = new consultas_roles(roles_actualizar.this);
        clase_roles = consultas.verRol(id_rol);

        if (clase_roles != null) {
            txtNombre_rol.setText(clase_roles.getNombre_rol());
            txtCodigo_rol.setText(clase_roles.getCodigo_rol());
        }

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre_rol.getText().toString().equals("") && !txtCodigo_rol.getText().toString().equals("")) {
                    correcto = consultas.actualizarRol(id_rol, txtNombre_rol.getText().toString(), txtCodigo_rol.getText().toString());

                    if (correcto) {
                        Toast.makeText(roles_actualizar.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show();
                        registros();
                    } else {
                        Toast.makeText(roles_actualizar.this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(roles_actualizar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void registros() {
        Intent intent = new Intent(this, roles_registrados.class);
        startActivity(intent);
        finish();
    }
}