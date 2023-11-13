package com.example.sia.sia_roles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.consultas.consultas_roles;

public class roles_guardar extends AppCompatActivity {

    EditText txtNombre_rol, txtCodigo_rol;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_roles_guardar);

        txtNombre_rol = findViewById(R.id.txtNombre_Rol);
        txtCodigo_rol = findViewById(R.id.txtCodigo_Rol);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtNombre_rol.getText().toString().equals("") && !txtCodigo_rol.getText().toString().equals("")) {

                    consultas_roles consulta = new consultas_roles(roles_guardar.this);
                    long id = consulta.guardarRol(txtNombre_rol.getText().toString(), txtCodigo_rol.getText().toString());

                    if (id > 0) {
                        Toast.makeText(roles_guardar.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                        registros();
                    } else {
                        Toast.makeText(roles_guardar.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(roles_guardar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre_rol.setText("");
        txtCodigo_rol.setText("");
    }

    private void registros() {
        Intent intent = new Intent(this, roles_registrados.class);
        startActivity(intent);
        finish();
    }
}