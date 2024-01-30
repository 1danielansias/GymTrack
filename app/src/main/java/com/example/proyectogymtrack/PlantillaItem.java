package com.example.proyectogymtrack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un elemento Plantilla que se mostrará en el GridView.
 *
 * @author Daniel Ansias.
 *
 */
public class PlantillaItem implements Serializable {
    private String nombrePlantilla;
    private ArrayList<String> ejerciciosPlantilla;

    public PlantillaItem(String nombrePlantilla, ArrayList<String> ejerciciosPlantilla) {
        this.nombrePlantilla = nombrePlantilla;
        this.ejerciciosPlantilla = ejerciciosPlantilla;
    }

    public String getNombrePlantilla() {
        return nombrePlantilla;
    }

    public ArrayList<String> getEjerciciosPlantilla() {
        return ejerciciosPlantilla;
    }
}
