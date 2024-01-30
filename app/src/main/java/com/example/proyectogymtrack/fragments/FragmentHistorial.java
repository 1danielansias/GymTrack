package com.example.proyectogymtrack.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.proyectogymtrack.R;

import java.util.Calendar;

/**
 * Controla la lógica del fragmento de historial.
 *
 * @author Daniel Ansias.
 */
public class FragmentHistorial extends Fragment {

    private Button btnCalendario;

    public FragmentHistorial() {
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
        return inflater.inflate(R.layout.fragment_historial, container, false);
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
        // Lógica la vista
        btnCalendario = view.findViewById(R.id.buttonCalendario);

        // Agrega un OnClickListener al botón para mostrar el DatePicker
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });
    }

    // Método para mostrar el DatePicker en un diálogo
    private void mostrarDatePicker() {
        // Obtiene la fecha actual
        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Crea un DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Aquí puedes manejar la fecha seleccionada
                        // Por ejemplo, mostrarla en un TextView o realizar alguna otra acción
                        // Mostrar los entrenamientos del día seleccionado
                    }
                },
                año,
                mes,
                dia
        );

        // Muestra el diálogo
        datePickerDialog.show();
    }
}