package com.example.proyectogymtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.proyectogymtrack.fragments.FragmentAjustes;
import com.example.proyectogymtrack.fragments.FragmentEjercicios;
import com.example.proyectogymtrack.fragments.FragmentEntrenamiento;
import com.example.proyectogymtrack.fragments.FragmentHistorial;
import com.example.proyectogymtrack.fragments.FragmentPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Clase que controla la lógica de la pantalla principal de la aplicación.
 *
 * @author Daniel Ansias.
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // Declaración de variables
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public FirebaseAuth getAuth() {
        return auth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la barra de navegación inferior
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        if (savedInstanceState != null) {
            Fragment fragment = new FragmentAjustes();
            bottomNavigationView.getMenu().findItem(R.id.ajustesId).setChecked(true);
            loadFragments(fragment);
        } else {
            // Establecer el elemento de menú entrenamiento como seleccionado
            bottomNavigationView.getMenu().findItem(R.id.entrenamientoId).setChecked(true);
            // Carrgar el fragmento inicial (FragmentEntrenamiento)
            loadFragments(new FragmentEntrenamiento());
        }

        // Configurar el Listener para la barra de navegación inferior
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Autentificación de usuario
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SELECTED_FRAGMENT_KEY", bottomNavigationView.getSelectedItemId());
    }

    /**
     * Manejar el cierre de sesión.
     */
    public void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        Snackbar.make((findViewById(R.id.mainActivity)), R.string.sesion_cerrada, Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    /**
     * Carga un fragmento en el contenedor de fragmentos.
     *
     * @param fragment El fragmento a cargar.
     * @return True si la operación fue exitosa.
     */
    public boolean loadFragments(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
        return true;
    }

    /**
     * Carga un fragmento según el elemento de menú seleccionado en la barra de navegación inferior.
     *
     * @param item El elemento de menú seleccionado.
     * @return True si la operación fue exitosa.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        // Determinar que fragmento cargar según el elemento de menú seleccionado
        if (itemId == R.id.entrenamientoId) {
            fragment = new FragmentEntrenamiento();
        } else if (itemId == R.id.historialId) {
            fragment = new FragmentHistorial();
        } else if (itemId == R.id.perfilId) {
            fragment = new FragmentPerfil();
        } else if (itemId == R.id.ejerciciosId) {
            fragment = new FragmentEjercicios();
        } else if (itemId == R.id.ajustesId) {
            fragment = new FragmentAjustes();
        }
        // Cargar el fragmento seleccionado
        return loadFragments(fragment);
    }

    /**
     * Maneja el comportamiento cuando se presiona el botón de retroceso.
     */
    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.entrenamientoId) {
            super.onBackPressed();
            finish();
        } else {
            // Si no estamos en la sección de entrenamiento, volver a ella al presionar el botón de retroceso
            bottomNavigationView.setSelectedItemId(R.id.entrenamientoId);
        }
    }

    /**
     * Maneja los resultados de actividades secundarias (startActivityForResult)
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}