package com.example.sia.sia_empleados;

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
import com.example.sia.conexiones.bd_roles;
import com.example.sia.consultas.consultas_empleados;
import com.example.sia.consultas.consultas_usuarios;

import java.util.ArrayList;

public class empleados_guardar extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtIdentidad, txtCargo, txtTelefono, txtDireccion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_empleados_guardar);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtIdentidad = findViewById(R.id.txtIdentidad);
        txtCargo = findViewById(R.id.txtCargo);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtNombres.getText().toString().equals("") &&
                        !txtApellidos.getText().toString().equals("") &&
                        !txtIdentidad.getText().toString().equals("")&&
                        !txtCargo.getText().toString().equals("") &&
                        !txtTelefono.getText().toString().equals("")&&
                        !txtDireccion.getText().toString().equals("")) {

                    consultas_empleados consulta = new consultas_empleados(empleados_guardar.this);
                    long id = consulta.guardarEmpleado(txtNombres.getText().toString(), txtApellidos.getText().toString(), txtIdentidad.getText().toString(), txtCargo.getText().toString(), txtTelefono.getText().toString(), txtDireccion.getText().toString());

                    if (id > 0) {
                        Toast.makeText(empleados_guardar.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        registros();
                    } else {
                        Toast.makeText(empleados_guardar.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(empleados_guardar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void registros() {
        Intent intent = new Intent(this, empleados_registrados.class);
        startActivity(intent);
        finish();
    }

}