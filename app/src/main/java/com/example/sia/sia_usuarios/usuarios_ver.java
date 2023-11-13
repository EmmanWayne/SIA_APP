package com.example.sia.sia_usuarios;

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
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.clases.clase_usuarios;
import com.example.sia.consultas.consultas_usuarios;

public class usuarios_ver extends AppCompatActivity {
    final consultas_usuarios consulta = new consultas_usuarios(usuarios_ver.this);
    EditText txtUsuario, txtContraseña, txtRol;
    Spinner comboIdentidades, comboRoles;
    Button btnActualizar;
    clase_usuarios claseUsuariosRegistrados;
    int id_usuario = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_usuarios_actualizar);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContraseña = findViewById(R.id.txtContrasena);
        txtRol = findViewById(R.id.txtRol);
        btnActualizar = findViewById(R.id.btnActualizar);
        comboIdentidades = findViewById(R.id.comboIdentidades);
        comboRoles = findViewById(R.id.comboRoles);


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


        claseUsuariosRegistrados = consulta.verUsuarios(id_usuario);


        if (claseUsuariosRegistrados != null) {
            txtUsuario.setText(claseUsuariosRegistrados.getNombre_usuario());
            txtContraseña.setText(claseUsuariosRegistrados.getContrasena_usuario());
            txtRol.setText(claseUsuariosRegistrados.getRol_usuario());
            btnActualizar.setVisibility(View.INVISIBLE);
            txtUsuario.setInputType(InputType.TYPE_NULL);
            txtContraseña.setInputType(InputType.TYPE_NULL);
            txtRol.setInputType(InputType.TYPE_NULL);
            comboIdentidades.setEnabled(false);
            comboRoles.setEnabled(false);

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actualizar_usuarios, menu);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(usuarios_ver.this);
        builder.setMessage("¿Desea eliminar este registro?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (consulta.eliminarUsuario(id_usuario)) {
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
        Intent intent = new Intent(usuarios_ver.this, usuarios_actualizar.class);
        intent.putExtra("ID", id_usuario);
        startActivity(intent);
        finish();
    }

    private void registros() {
        Intent intent = new Intent(this, usuarios_registrados.class);
        startActivity(intent);
        finish();
    }
}