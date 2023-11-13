package com.example.sia.sia_empleados;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.clases.clase_empleados;
import com.example.sia.consultas.consultas_empleados;

public class empleados_actualizar extends AppCompatActivity {


    EditText txtNombres, txtApellidos, txtIdentidad, txtCargo, txtTelefono, txtDireccion;
    Button btnActualizar;
    boolean correcto = false;
    clase_empleados clase;
    int id_empleado = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_empleados_actualizar);

        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtIdentidad = findViewById(R.id.txtIdentidad);
        txtCargo = findViewById(R.id.txtCargo);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        btnActualizar = findViewById(R.id.btnActualizar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id_empleado = Integer.parseInt(null);
            } else {
                id_empleado = extras.getInt("ID");
            }
        } else {
            id_empleado = (int) savedInstanceState.getSerializable("ID");
        }

        final consultas_empleados consultas = new consultas_empleados(empleados_actualizar.this);
        clase = consultas.verEmpleado(id_empleado);

        if (clase != null) {
            txtNombres.setText(clase.getNombres_empleado());
            txtApellidos.setText(clase.getApellidos_empleado());
            txtIdentidad.setText(clase.getIdentidad_empleado());
            txtCargo.setText(clase.getCargo_empleado());
            txtTelefono.setText(clase.getTelefono_empleado());
            txtDireccion.setText(clase.getDireccion_empleado());
        }

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombres.getText().toString().equals("") &&
                        !txtApellidos.getText().toString().equals("") &&
                        !txtIdentidad.getText().toString().equals("")&&
                        !txtCargo.getText().toString().equals("") &&
                        !txtTelefono.getText().toString().equals("")&&
                        !txtDireccion.getText().toString().equals("")) {
                    correcto = consultas.actualizarEmpleado(id_empleado, txtNombres.getText().toString(), txtApellidos.getText().toString(), txtIdentidad.getText().toString(), txtCargo.getText().toString(), txtTelefono.getText().toString(), txtDireccion.getText().toString());

                    if (correcto) {
                        Toast.makeText(empleados_actualizar.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show();
                        registros();
                    } else {
                        Toast.makeText(empleados_actualizar.this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(empleados_actualizar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
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