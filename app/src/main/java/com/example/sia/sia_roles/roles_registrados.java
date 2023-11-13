package com.example.sia.sia_roles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sia.R;
import com.example.sia.adaptadores.adaptador_roles;
import com.example.sia.clases.clase_roles;
import com.example.sia.consultas.consultas_roles;

import java.util.ArrayList;

public class roles_registrados extends AppCompatActivity {

    RecyclerView listaRoles;
    ArrayList<clase_roles> listaArrayRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_roles_registrados);

        listaRoles = findViewById(R.id.listaUsuarios);
        listaRoles.setLayoutManager(new LinearLayoutManager(this));

        consultas_roles consulta = new consultas_roles(roles_registrados.this);

        listaArrayRoles = new ArrayList<>();

        adaptador_roles adapter = new adaptador_roles(consulta.mostrarRoles());
        listaRoles.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registrar_roles, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRegistrar:
                registro_rol();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void registro_rol() {
        Intent intent = new Intent(this, roles_guardar.class);
        startActivity(intent);
        finish();
    }
}