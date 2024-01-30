package com.example.proyectogymtrack;

/**
 * Clase que representa un elemento Ejercicio que se mostrará en el RecyclerView.
 *
 * @author Daniel Ansias.
 *
 */
public class EjercicioItem {
    private String ejercicio;
    private String desc;
    private int imagen;

    public EjercicioItem(String ejercicio, String desc, int imagen) {
        this.ejercicio = ejercicio;
        this.desc = desc;
        this.imagen = imagen;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
