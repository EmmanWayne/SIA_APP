package com.example.sia.sia_empleados;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sia.R;
import com.example.sia.adaptadores.adaptador_empleados;
import com.example.sia.clases.clase_empleados;
import com.example.sia.consultas.consultas_empleados;

import java.util.ArrayList;

public class empleados_registrados extends AppCompatActivity {

    RecyclerView lista;
    ArrayList<clase_empleados> listaArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_empleados_registrados);

        lista = findViewById(R.id.listaEmpleados);
        lista.setLayoutManager(new LinearLayoutManager(this));

        consultas_empleados consulta = new consultas_empleados(empleados_registrados.this);

        listaArray = new ArrayList<>();

        adaptador_empleados adapter = new adaptador_empleados(consulta.mostrarEmpleados());
        lista.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registrar_empleados, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRegistrar:
                registro_empleado();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void registro_empleado() {
        Intent intent = new Intent(this, empleados_guardar.class);
        startActivity(intent);
        finish();
    }
}