package com.example.sia.sia_usuarios;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sia.R;
import com.example.sia.clases.clase_empleados;
import com.example.sia.clases.clase_roles;
import com.example.sia.conexiones.bd_empleados;
import com.example.sia.conexiones.bd_roles;
import com.example.sia.consultas.consultas_usuarios;

import java.util.ArrayList;

public class usuarios_guardar extends AppCompatActivity {

    Spinner  comboRoles;
    ArrayList<String> listaRoles;
    ArrayList<clase_roles> rolesRegistrados;
    bd_roles conn;

    Spinner  comboIdentidades;
    ArrayList<String> listaIdentidades;
    ArrayList<clase_empleados> empleadosRegistrados;
    bd_empleados conn2;

    EditText txtUsuario, txtContraseña, txtRol;
    Button btnGuardar;
    TextView checkContrasena;

    private boolean esVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_usuarios_guardar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtContraseña = findViewById(R.id.txtContrasena);
        txtRol = findViewById(R.id.txtRol);
        comboIdentidades = findViewById(R.id.comboIdentidades);
        comboRoles = findViewById(R.id.comboRoles);
        btnGuardar = findViewById(R.id.btnGuardar);
        txtUsuario.setInputType(InputType.TYPE_NULL);
        txtRol.setInputType(InputType.TYPE_NULL);
        checkContrasena = findViewById(R.id.checkPass);

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

        conn2 = new bd_empleados(getApplicationContext());
        comboIdentidades = (Spinner) findViewById(R.id.comboIdentidades);
        consultarListaEmpleados();
        ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter
                (this, android.R.layout.select_dialog_singlechoice, listaIdentidades);
        comboIdentidades.setAdapter(adaptador2);
        comboIdentidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
                if (position != 0) {
                    txtUsuario.setText(empleadosRegistrados.get(position - 1).getIdentidad_empleado());
                } else {
                    txtUsuario.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        checkContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOcultarContrasena();
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtUsuario.getText().toString().equals("") && !txtContraseña.getText().toString().equals("") && !txtRol.getText().toString().equals("")) {

                    consultas_usuarios consulta = new consultas_usuarios(usuarios_guardar.this);
                    long id = consulta.guardarUsuario(txtUsuario.getText().toString(), txtContraseña.getText().toString(), txtRol.getText().toString());

                    if (id > 0) {
                        Toast.makeText(usuarios_guardar.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        registros();
                    } else {
                        Toast.makeText(usuarios_guardar.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(usuarios_guardar.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void registros() {
        Intent intent = new Intent(this, usuarios_registrados.class);
        startActivity(intent);
        finish();
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
        obtenerListaRoles();
        conn.close();
    }

    private void obtenerListaRoles() {
        listaRoles = new ArrayList<String>();
        listaRoles.add("Seleccione un rol");
        for (int i = 0; i < rolesRegistrados.size(); i++) {
            listaRoles.add(rolesRegistrados.get(i).getCodigo_rol() + " - " + rolesRegistrados.get(i).getNombre_rol());
        }
    }

    private void consultarListaEmpleados() {
        SQLiteDatabase db = conn2.getReadableDatabase();
        clase_empleados empleados = null;
        empleadosRegistrados = new ArrayList<clase_empleados>();
        Cursor cursor = db.rawQuery("SELECT * FROM empleados", null);
        while (cursor.moveToNext()) {
            empleados = new clase_empleados();
            empleados.setNombres_empleado(cursor.getString(1));
            empleados.setApellidos_empleado(cursor.getString(2));
            empleados.setIdentidad_empleado(cursor.getString(3));
            Log.i("nombres_empleado", empleados.getNombres_empleado());
            Log.i("apellidos_empleado", empleados.getApellidos_empleado());
            Log.i("identidad_empleado", empleados.getIdentidad_empleado());
            empleadosRegistrados.add(empleados);
        }
        obtenerListaEmpleados();
        conn.close();
    }

    private void obtenerListaEmpleados() {
        listaIdentidades = new ArrayList<String>();
        listaIdentidades.add("Seleccione un N° de identidad");
        for (int i = 0; i < empleadosRegistrados.size(); i++) {
            listaIdentidades.add(empleadosRegistrados.get(i).getNombres_empleado() + " - " + empleadosRegistrados.get(i).getApellidos_empleado() + " - " + empleadosRegistrados.get(i).getIdentidad_empleado());
        }
    }

    private void mostrarOcultarContrasena(){
        checkContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!esVisible) {
                    txtContraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    esVisible = true;
                    checkContrasena.setText("Mostrar");
                    ///aqui puedes cambiar el texto del boton, o textview, o cambiar la imagen de un imageView.
                }
                else {
                    txtContraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    esVisible = false;
                    checkContrasena.setText("Ocultar");
                    ///aqui puedes cambiar el texto del boton, o textview, o cambiar la imagen de un imageView.
                }
            }
        });

    }
}