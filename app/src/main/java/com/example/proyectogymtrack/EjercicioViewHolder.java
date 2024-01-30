package com.example.proyectogymtrack;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Clase que actua como contenedor para las vistas que forman parte de un elemento individual en el RecyclerView.
 *
 * @author Daniel Ansias.
 *
 */
public class EjercicioViewHolder extends RecyclerView.ViewHolder {

    // Variables para las vistas
    public ImageView imageView;
    public TextView nombre, desc;
    public RelativeLayout itemLayout;

    public EjercicioViewHolder(@NonNull View itemView) {
        super(itemView);
        // Asignación de referencias mediante los identificadores
        imageView = itemView.findViewById(R.id.imageView);
        nombre = itemView.findViewById(R.id.tvEjercicio);
        desc = itemView.findViewById(R.id.tvDesc);
        itemLayout = itemView.findViewById(R.id.itemView);
    }
}
