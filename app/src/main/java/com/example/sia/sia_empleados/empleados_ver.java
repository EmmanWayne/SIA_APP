package com.example.sia.sia_empleados;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.clases.clase_empleados;
import com.example.sia.consultas.consultas_empleados;

public class empleados_ver extends AppCompatActivity {
    final consultas_empleados consulta = new consultas_empleados(empleados_ver.this);
    EditText txtNombres, txtApellidos, txtIdentidad, txtCargo, txtTelefono, txtDireccion;
    Button btnActualizar;
    clase_empleados clase;
    int id_empleado = 0;

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


        clase = consulta.verEmpleado(id_empleado);


        if (clase != null) {
            txtNombres.setText(clase.getNombres_empleado());
            txtApellidos.setText(clase.getApellidos_empleado());
            txtIdentidad.setText(clase.getIdentidad_empleado());
            txtCargo.setText(clase.getCargo_empleado());
            txtTelefono.setText(clase.getTelefono_empleado());
            txtDireccion.setText(clase.getDireccion_empleado());
            btnActualizar.setVisibility(View.INVISIBLE);
            txtNombres.setInputType(InputType.TYPE_NULL);
            txtApellidos.setInputType(InputType.TYPE_NULL);
            txtIdentidad.setInputType(InputType.TYPE_NULL);
            txtCargo.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actualizar_empleados, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEditar:
                actualizar();
                return true;

            case R.id.menuBorrar:
                eliminar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void eliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(empleados_ver.this);
        builder.setMessage("¿Desea eliminar este registro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (consulta.eliminarEmpleados(id_empleado)) {
                            registros();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    private void actualizar() {
        Intent intent = new Intent(empleados_ver.this, empleados_actualizar.class);
        intent.putExtra("ID", id_empleado);
        startActivity(intent);
        finish();
    }

    private void registros() {
        Intent intent = new Intent(this, empleados_registrados.class);
        startActivity(intent);
        finish();
    }
}