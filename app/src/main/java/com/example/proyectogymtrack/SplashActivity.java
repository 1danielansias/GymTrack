package com.example.proyectogymtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Clase que controla la lógica de la pantalla Splash que se mostrará antes de cargar la pantalla principal de la aplicación.
 *
 * @author Daniel Ansias.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Handler que retrasa la ejecución del Intent que envia al usuario a la pantalla principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent que envia al usuario a la pantalla principal
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // Retrasar la ejecución por 2 segundos
    }
}