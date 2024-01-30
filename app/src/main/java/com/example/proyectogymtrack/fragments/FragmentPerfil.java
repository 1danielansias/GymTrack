package com.example.proyectogymtrack.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectogymtrack.LoginActivity;
import com.example.proyectogymtrack.MainActivity;
import com.example.proyectogymtrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Controla la lógica del fragmento de perfil.
 *
 * @author Daniel Ansias.
 */
public class FragmentPerfil extends Fragment {

    // Declaración de variables
    private Button buttonSignOut;
    private TextView nombreUsuario;
    private FirebaseAuth auth;
    private FirebaseUser user;
    public FragmentPerfil() {
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
        return inflater.inflate(R.layout.fragment_perfil, container, false);
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
        buttonSignOut = view.findViewById(R.id.buttonSignOut);
        nombreUsuario = view.findViewById(R.id.nombreUsuario);
        auth = ((MainActivity) requireActivity()).getAuth();
        user = auth.getCurrentUser();

        nombreUsuario.setText(user.getEmail());

        // Listener para el botón de cerrar sesión
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).cerrarSesion();
            }
        });
    }
}