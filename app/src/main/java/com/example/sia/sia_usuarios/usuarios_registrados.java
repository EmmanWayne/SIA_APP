package com.example.sia.sia_usuarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sia.R;
import com.example.sia.adaptadores.adaptador_usuarios;
import com.example.sia.clases.clase_roles;
import com.example.sia.consultas.consultas_usuarios;

import java.util.ArrayList;

public class usuarios_registrados extends AppCompatActivity {

    RecyclerView listaUsuarios;
    ArrayList<clase_roles> listaArrayUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_usuarios_registrados);

        listaUsuarios = findViewById(R.id.listaUsuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultas_usuarios consulta = new consultas_usuarios(usuarios_registrados.this);

        listaArrayUsuarios = new ArrayList<>();

        adaptador_usuarios adapter = new adaptador_usuarios(consulta.mostrarUsuarios());
        listaUsuarios.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registrar_usuarios, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRegistrar:
                registro_usuario();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void registro_usuario() {
        Intent intent = new Intent(this, usuarios_guardar.class);
        startActivity(intent);
        finish();
    }
}