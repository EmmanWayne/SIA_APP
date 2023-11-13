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
import com.example.sia.clases.clase_roles;
import com.example.sia.sia_roles.roles_ver;

import java.util.ArrayList;

public class adaptador_roles extends RecyclerView.Adapter<adaptador_roles.rolesViewHolder> {

    ArrayList<clase_roles> lista;

    public adaptador_roles(ArrayList<clase_roles> listaRoles) {
        this.lista = listaRoles;
    }

    @NonNull
    @Override
    public rolesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ventana_roles_item, null, false);
        return new rolesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rolesViewHolder holder, int position) {
        holder.viewNombreRol.setText(lista.get(position).getNombre_rol());
        holder.viewCodigoRol.setText(lista.get(position).getCodigo_rol());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class rolesViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreRol, viewCodigoRol;

        public rolesViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreRol = itemView.findViewById(R.id.viewNombreRol);
            viewCodigoRol = itemView.findViewById(R.id.viewCodigoRol);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, roles_ver.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getId_rol());
                    context.startActivity(intent);
                }
            });
        }
    }
}
