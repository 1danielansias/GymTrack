package com.example.proyectogymtrack.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.proyectogymtrack.R;

/**
 * Controla la lógica del fragmento de ajustes.
 *
 * @author Daniel Ansias.
 */
public class FragmentAjustes extends Fragment {

    private Spinner spinnerIdiomas, spinnerUnidades, spinnerNotificaciones;
    private Button btnTheme;
    private SwitchCompat switchTheme;


    public FragmentAjustes() {
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
        return inflater.inflate(R.layout.fragment_ajustes, container, false);
    }

    /**
     * Controlar la lógica de la vista inflada devuelta por onCreateView().
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lógica de la vista
        spinnerIdiomas = view.findViewById(R.id.spinnerIdiomas);
        spinnerUnidades = view.findViewById(R.id.spinnerUnidades);
        spinnerNotificaciones = view.findViewById(R.id.spinnerNotificaciones);

        // Setup del spinner de idiomas
        ArrayAdapter<CharSequence> adapterIdiomas = ArrayAdapter.createFromResource(getContext(), R.array.spinner_idiomas, android.R.layout.simple_spinner_item);
        // Con setDropDownViewResource indicamos el layout para cada elemento de la lista
        adapterIdiomas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdiomas.setAdapter(adapterIdiomas);

        // Setup del spinner de Unidades
        ArrayAdapter<CharSequence> adapterUnidades = ArrayAdapter.createFromResource(getContext(), R.array.spinner_unidades, android.R.layout.simple_spinner_item);
        adapterUnidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnidades.setAdapter(adapterUnidades);

        // Setup del spinner de spinnerNotificaciones
        ArrayAdapter<CharSequence> adapterNotificaciones = ArrayAdapter.createFromResource(getContext(), R.array.spinner_notificaciones, android.R.layout.simple_spinner_item);
        adapterNotificaciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotificaciones.setAdapter(adapterNotificaciones);

        switchTheme = view.findViewById(R.id.switchTheme);
        switchTheme.setChecked(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);

        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Cambia el modo de noche según el estado del switch
                AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

                // Recrea la actividad para aplicar el cambio de tema
                requireActivity().recreate();
            }
        });

    }

}