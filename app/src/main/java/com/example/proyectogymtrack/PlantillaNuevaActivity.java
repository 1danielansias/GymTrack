package com.example.proyectogymtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Clase que controla la lógica de la pantalla de creción de una nueva plantilla de entrenamiento.
 *
 * @author Daniel Ansias.
 */
public class PlantillaNuevaActivity extends AppCompatActivity {

    // Declaración de variables
    private TextInputLayout nombrePlantilla;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla_entrenamiento);

        // Inicialización de las vistas mediante sus identificadores
        nombrePlantilla = findViewById(R.id.nombrePlantilla);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Configuración de un OnClickListener para el botón de guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nombre que el usuario asigna a la nueva plantilla
                String nombre = nombrePlantilla.getEditText().getText().toString().trim();

                // Pasar los datos obtenidos a la actividad principal donde se encuentran los fragmentos
                Intent intent = new Intent();
                intent.putExtra("NombrePlantillaNueva", nombre);
                setResult(RESULT_OK, intent);

                // Finalizar la actividad actual
                finish();
            }
        });
    }
}