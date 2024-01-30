package com.example.proyectogymtrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Clase Adaptador para el RecyclerView de ejercicios.
 *
 * @author Daniel Ansias.
 *
 */
public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioViewHolder> {

    // Declaración de variables
    private Context context;
    private List<EjercicioItem> ejercicioItems;
    private EjercicioSelectedItemListener listener;

    public EjercicioAdapter(Context context, List<EjercicioItem> ejercicioItems, EjercicioSelectedItemListener listener) {
        this.context = context;
        this.ejercicioItems = ejercicioItems;
        this.listener = listener;
    }

    /**
     * Se llama a este método cuando se necesita crear un nuevo ViewHolder
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return El ViewHolder.
     */
    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del elemento de la lista y crear un nuevo ViewHolder
        return new EjercicioViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ejercicio_item_view,parent,false));
    }

    /**
     * Método que se llama para asignar datos a un ViewHolder .
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {
        // Asignar los datos del ejercicio al ViewHolder en la posición actual
        holder.nombre.setText(ejercicioItems.get(position).getEjercicio());
        holder.desc.setText(ejercicioItems.get(position).getDesc());
        holder.imageView.setImageResource(ejercicioItems.get(position).getImagen());

        // Listener para cada item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Notificar al listener cuando se hace click en un elemento
                listener.onItemClicked(ejercicioItems.get(position));
            }
        });
    }

    /**
     * Devuelve la cantidad total de elementos de la lista.
     *
     * @return La cantidad total de elementos de la lista.
     */
    @Override
    public int getItemCount() {
        return ejercicioItems.size();
    }

    /**
     * // Actualiza la lista de items con un nuevo conjunto de datos filtrados.
     *
     * @param filteredList La lista de elementos filtrados.
     */
    public void setFilteredList(List<EjercicioItem> filteredList) {
        this.ejercicioItems = filteredList;
        notifyDataSetChanged();
    }
}
