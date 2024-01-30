package com.example.proyectogymtrack.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectogymtrack.PlantillaAdapter;
import com.example.proyectogymtrack.PlantillaItem;
import com.example.proyectogymtrack.PlantillaNuevaActivity;
import com.example.proyectogymtrack.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Clase que controla la lógica del fragmento de entrenamientos.
 *
 * @author Daniel Ansias.
 *
 */
public class FragmentEntrenamiento extends Fragment {

    // Declaración de variables
    private Button btnEntrenamiento;
    private ImageView btnNuevaPlantilla;
    private GridView gridViewTemplates, gridViewBaseTemplates;
    private ArrayList<PlantillaItem> plantillas = new ArrayList<>();
    private ArrayList<String> ejerciciosPlantilla = new ArrayList<>();
    private PlantillaAdapter plantillaAdapter;

    public FragmentEntrenamiento() {
        // Constructor público vacío requerido
    }

    public ArrayList<PlantillaItem> getPlantillas() {
        return plantillas;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_entrenamiento, container, false);
    }

    /**
     * Lógica del fragmento una vez inflada la Vista
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recuperar datos mediante bundle en caso de que los haya
        if (savedInstanceState != null) {
            plantillas = (ArrayList<PlantillaItem>) savedInstanceState.getSerializable("plantillasGuardadas");
        }
        Snackbar.make(view, R.string.plantillas_recuperadas,Snackbar.LENGTH_SHORT).show();

        // Botón para añadir nueva plantilla
        btnNuevaPlantilla = view.findViewById(R.id.btnNuevaPlantilla);
        btnNuevaPlantilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la página de creación de plantillas mediante un Intent
                Intent intent = new Intent(getActivity(), PlantillaNuevaActivity.class);
                // Lanzar la actividad esperando un resultado de vuelta
                startActivityForResult(intent, 1);
            }
        });

        // Cargar la información de las plantillas
        loadTemplateInfo();

        // Añadir el adaptor personalizado al gridView de plantillas del usuario
        gridViewTemplates = view.findViewById(R.id.gridViewTemplates);
        plantillaAdapter = new PlantillaAdapter(getContext(), plantillas);
        gridViewTemplates.setAdapter(plantillaAdapter);

        // Añadir el adaptador personalizado al GridView de plantillas base
        gridViewBaseTemplates = view.findViewById(R.id.gridViewTemplates2);
        gridViewBaseTemplates.setAdapter(plantillaAdapter);
    }

    /**
     * Guarda las plantillas en un bundle para poder rescatarlas al inicializar el fragment.
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guardamos las plantillas para rescatarlas en caso de que se añadiera alguna plantilla nueva
        outState.putSerializable("plantillasGuardadas", plantillas);
        Snackbar.make(getView(), "Plantillas guardadas",Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Recibe el resultado de una llamada previa a startActivityForResult()
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            // Rescatar los datos del bundle
            String yourdata = data.getStringExtra("NombrePlantillaNueva");
            plantillas.add(new PlantillaItem(yourdata, ejerciciosPlantilla));
            // Notificar al adaptador sobre los nuevos cambios
            plantillaAdapter.notifyDataSetChanged();
            Snackbar.make(getView(), R.string.pagina_actualizada,Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * Carga la informacion de las plantillas que se mostrarán.
     */
    public void loadTemplateInfo() {
        // Cargar los ejercicios de las plantillas en un array que recibira el PlantillaItem
        ejerciciosPlantilla.add("Press banca (barra)");
        ejerciciosPlantilla.add("Dominadas");
        ejerciciosPlantilla.add("Press militar");

        // Cargar el array de plantillas que se le pasará al adaptador
        plantillas.add(new PlantillaItem("Plantilla 1", ejerciciosPlantilla));
        plantillas.add(new PlantillaItem("Plantilla 2", ejerciciosPlantilla));
        plantillas.add(new PlantillaItem("Plantilla 3", ejerciciosPlantilla));
        plantillas.add(new PlantillaItem("Plantilla 4", ejerciciosPlantilla));
    }
}