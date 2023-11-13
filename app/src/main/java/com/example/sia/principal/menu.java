package com.example.sia.principal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.sia_empleados.empleados_registrados;
import com.example.sia.sia_roles.roles_registrados;
import com.example.sia.sia_usuarios.usuarios_registrados;

public class menu extends AppCompatActivity {
    Button btnUsuarios, btnEmpleados, btnRoles, btnAcercaDe, btnCerrarSesion, btnSalir;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_sia_menu_principal);

        btnUsuarios = findViewById(R.id.btnUsuarios);
        btnEmpleados = findViewById(R.id.btnEmpleados);
        btnRoles = findViewById(R.id.btnRoles);
        btnAcercaDe = findViewById(R.id.btnAcercaDe);
        btnSalir = findViewById(R.id.btnSalir);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);


        btnRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirRoles();
            }
        });

        btnUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirUsuarios();
            }
        });

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirEmpleados();
            }
        });

        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirAcercaDe();
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salir();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("¿Deseas salir de SIA?")
                    .setMessage("¿Estás seguro?")
                    .setNegativeButton("NO", null)// sin listener
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            menu.this.finish();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void abrirRoles() {
        Intent intent = new Intent(this, roles_registrados.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    private void abrirUsuarios() {
        Intent intent = new Intent(this, usuarios_registrados.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    private void abrirEmpleados() {
        Intent intent = new Intent(this, empleados_registrados.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    private void abrirAcercaDe() {
        Intent intent = new Intent(this, acercaDe.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    private void abrirLogin() {
        Intent intent = new Intent(this, login.class);
        intent.putExtra("ID", id);
        startActivity(intent);
        finish();
    }

    private void cerrarSesion() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("¿Deseas cerrar sesión?")
                .setMessage("¿Estás seguro?")
                .setNegativeButton("NO", null)// sin listener
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        abrirLogin();
                    }
                })
                .show();
    }

    private void Salir() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("¿Deseas salir de SIA?")
                .setMessage("¿Estás seguro?")
                .setNegativeButton("NO", null)// sin listener
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        menu.this.finish();
                    }
                })
                .show();
    }
}