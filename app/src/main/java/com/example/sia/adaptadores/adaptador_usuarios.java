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
import com.example.sia.clases.clase_usuarios;
import com.example.sia.sia_usuarios.usuarios_ver;

import java.util.ArrayList;

public class adaptador_usuarios extends RecyclerView.Adapter<adaptador_usuarios.usuariosViewHolder> {

    ArrayList<clase_usuarios> lista;

    public adaptador_usuarios(ArrayList<clase_usuarios> listaUsuarios) {
        this.lista = listaUsuarios;
    }

    @NonNull
    @Override
    public usuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ventana_usuarios_item, null, false);
        return new usuariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull usuariosViewHolder holder, int position) {
        holder.viewUsuario.setText(lista.get(position).getNombre_usuario());
        holder.viewContrasena.setText(lista.get(position).getContrasena_usuario());
        holder.viewRol.setText(lista.get(position).getRol_usuario());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class usuariosViewHolder extends RecyclerView.ViewHolder {

        TextView viewUsuario, viewContrasena, viewRol;

        public usuariosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewUsuario = itemView.findViewById(R.id.viewUsuario);
            viewContrasena = itemView.findViewById(R.id.viewContrasena);
            viewRol = itemView.findViewById(R.id.viewRol);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, usuarios_ver.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getId_usuario());
                    context.startActivity(intent);
                }
            });
        }
    }
}
