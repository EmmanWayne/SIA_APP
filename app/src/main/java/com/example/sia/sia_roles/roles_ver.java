package com.example.sia.sia_roles;

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
import com.example.sia.clases.clase_roles;
import com.example.sia.consultas.consultas_roles;

public class roles_ver extends AppCompatActivity {
    final consultas_roles consulta = new consultas_roles(roles_ver.this);
    EditText txtNombre_rol, txtCodigo_rol;
    Button btnActualizar;
    clase_roles claseRolesRegistrados;
    int id_rol = 0;

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


        claseRolesRegistrados = consulta.verRol(id_rol);


        if (claseRolesRegistrados != null) {
            txtNombre_rol.setText(claseRolesRegistrados.getNombre_rol());
            txtCodigo_rol.setText(claseRolesRegistrados.getCodigo_rol());
            btnActualizar.setVisibility(View.INVISIBLE);
            txtNombre_rol.setInputType(InputType.TYPE_NULL);
            txtCodigo_rol.setInputType(InputType.TYPE_NULL);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actualizar_roles, menu);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(roles_ver.this);
        builder.setMessage("¿Desea eliminar este registro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (consulta.eliminarRol(id_rol)) {
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
        Intent intent = new Intent(roles_ver.this, roles_actualizar.class);
        intent.putExtra("ID", id_rol);
        startActivity(intent);
        finish();
    }


    private void registros() {
        Intent intent = new Intent(this, roles_registrados.class);
        startActivity(intent);
        finish();
    }
}