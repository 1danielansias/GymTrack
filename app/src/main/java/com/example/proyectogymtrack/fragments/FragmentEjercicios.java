package com.example.proyectogymtrack.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectogymtrack.EjercicioAdapter;
import com.example.proyectogymtrack.EjercicioItem;
import com.example.proyectogymtrack.R;
import com.example.proyectogymtrack.EjercicioSelectedItemListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que controla la lógica del fragmento de ejecicios.
 *
 * @author Daniel Ansias
 *
 */
public class FragmentEjercicios extends Fragment implements EjercicioSelectedItemListener {

    // Declaración de variables
    private List<EjercicioItem> ejercicioItems;
    private EjercicioAdapter itemEjercicioAdapter;
    private View view;

    public FragmentEjercicios() {
        // Constructor público vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_ejercicios, container, false);
    }

    /**
     * Controla la lógica de la vista inflada devuelta por onCreateView().
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializar el ArrayList de elementos del RecyclerView
        ejercicioItems = new ArrayList<>();
        // Cargar los ejercicios dentro del ArrayList
        cargarEjercicios();

        // Logica del RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemEjercicioAdapter = new EjercicioAdapter(getContext(), ejercicioItems, this);
        recyclerView.setAdapter(itemEjercicioAdapter);

        // Logica del SearchView
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }

    // Listener para cada item del RecyclerView
    @Override
    public void onItemClicked(EjercicioItem ejercicioItemSelected) {
        Snackbar.make(getView(), ejercicioItemSelected.getEjercicio(),Snackbar.LENGTH_SHORT).show();
    }

    // Logica del SearchView
    private void filterList(String text) {
        // Inicialización de ArrayList de resultados filtrados
        List<EjercicioItem> filteredList = new ArrayList<>();
        // Recorrer lista de ejercicioItems
        for (EjercicioItem ejercicioItem : ejercicioItems) {
            // Si coincide con la busqueda se añade a la nueva lista
            if (ejercicioItem.getEjercicio().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(ejercicioItem);
            }
        }
        // Comprobaciones
        if (filteredList.isEmpty()) {
            Snackbar.make(getView(), R.string.no_se_encontraron_resultados,Snackbar.LENGTH_SHORT).show();
        } else {
            // Enviar al adaptador (actualizar recyclerView)
            itemEjercicioAdapter.setFilteredList(filteredList);
        }
    }

    public void cargarEjercicios() {
        ejercicioItems.add(new EjercicioItem("Press Banca", "Pecho", R.drawable.bench_press));
        ejercicioItems.add(new EjercicioItem("Press Banca Inclinado (Mancuerna)", "Pecho", R.drawable.bench_press_inc));
        ejercicioItems.add(new EjercicioItem("Press de Hombro (Mancuerna)", "Hombro", R.drawable.press_hombro));
        ejercicioItems.add(new EjercicioItem("Press de Pierna", "Pierna", R.drawable.press_pierna));
        ejercicioItems.add(new EjercicioItem("Extensión de Pierna", "Pierna", R.drawable.extension_pierna));
        ejercicioItems.add(new EjercicioItem("Bicep Curl (Mancuerna)", "Brazo", R.drawable.bicep_curl));
        ejercicioItems.add(new EjercicioItem("Dominada", "Espalda", R.drawable.dominada));
        ejercicioItems.add(new EjercicioItem("Elevaciones Laterales", "Hombro", R.drawable.elevaciones_laterales));
        ejercicioItems.add(new EjercicioItem("Elevaciones Frontales", "Hombro", R.drawable.elevaciones_frontales));

        ejercicioItems.add(new EjercicioItem("Press Banca", "Pecho", R.drawable.bench_press));
        ejercicioItems.add(new EjercicioItem("Press Banca Inclinado (Mancuerna)", "Pecho", R.drawable.bench_press_inc));
        ejercicioItems.add(new EjercicioItem("Press de Hombro (Mancuerna)", "Hombro", R.drawable.press_hombro));
        ejercicioItems.add(new EjercicioItem("Press de Pierna", "Pierna", R.drawable.press_pierna));
        ejercicioItems.add(new EjercicioItem("Extensión de Pierna", "Pierna", R.drawable.extension_pierna));
    }
}