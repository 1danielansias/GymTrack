package com.example.proyectogymtrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Clase Adaptador para el GridView de plantillas.
 *
 * @author Daniel Ansias.
 *
 */
public class PlantillaAdapter extends BaseAdapter {

    // Declaración de variables
    private Context context;
    private List<PlantillaItem> plantillas;

    public PlantillaAdapter(Context context, List<PlantillaItem> plantillas) {
        this.context = context;
        this.plantillas = plantillas;
    }

    @Override
    public int getCount() {
        return plantillas.size();
    }

    @Override
    public Object getItem(int position) {
        return plantillas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Devuelve una vista que muestra los datos en la posición especificada del conjunto de datos (dataset)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflar vista
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.template_item_view, parent, false);

        // Inicialización de TextView que muestra el nombre de la plantilla en la vista
        TextView nombrePlantilla = view.findViewById(R.id.nombrePlantilla);
        // Obtenemos el nombre de la plantilla del array de plantillas y lo asignamos al TextView correspondiente
        nombrePlantilla.setText(plantillas.get(position).getNombrePlantilla());

        String ejercicios = "";
        // Crear String con el conjunto de ejercicios que contiene la plantilla
        for (int i = 0; i < plantillas.get(position).getEjerciciosPlantilla().size(); i++) {
            // Iteramos sobre el array de ejercicios que contiene la plantilla
            ejercicios = ejercicios + plantillas.get(position).getEjerciciosPlantilla().get(i) + ", ";
        }
        TextView ejerciciosPlantilla = view.findViewById(R.id.ejerciciosPlantilla);
        // Asignamos al TextView que muestra los ejercicios el string de ejercicios obtenido anteriormente
        ejerciciosPlantilla.setText(ejercicios);

        // Devolver la vista creada
        return view;
    }
}
