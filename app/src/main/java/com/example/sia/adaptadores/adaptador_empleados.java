package com.example.sia.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sia.R;
import com.example.sia.clases.clase_empleados;
import com.example.sia.sia_empleados.empleados_ver;

import java.util.ArrayList;

public class adaptador_empleados extends RecyclerView.Adapter<adaptador_empleados.rolesViewHolder> {

    ArrayList<clase_empleados> lista;

    public adaptador_empleados(ArrayList<clase_empleados> listaEmpleados) {
        this.lista = listaEmpleados;
    }

    @NonNull
    @Override
    public rolesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ventana_empleados_item, null, false);
        return new rolesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rolesViewHolder holder, int position) {
        holder.viewNombres.setText(lista.get(position).getNombres_empleado());
        holder.viewApellidos.setText(lista.get(position).getApellidos_empleado());
        holder.viewIdentidad.setText(lista.get(position).getIdentidad_empleado());
        holder.viewCargo.setText(lista.get(position).getCargo_empleado());
        holder.viewTelefono.setText(lista.get(position).getTelefono_empleado());
        holder.viewDireccion.setText(lista.get(position).getDireccion_empleado());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class rolesViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombres, viewApellidos, viewIdentidad, viewCargo, viewTelefono, viewDireccion;

        public rolesViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombres = itemView.findViewById(R.id.viewNombres);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewIdentidad = itemView.findViewById(R.id.viewIdentidad);
            viewCargo = itemView.findViewById(R.id.viewCargo);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, empleados_ver.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getId_empleado());
                    context.startActivity(intent);
                }
            });
        }
    }
}
