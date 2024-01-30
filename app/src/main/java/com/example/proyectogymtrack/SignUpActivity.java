package com.example.proyectogymtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que controla la lógica de la pantalla de registro.
 *
 * @author Daniel Ansias.
 */
public class SignUpActivity extends AppCompatActivity {

    // Inicialización de variables
    private TextInputLayout usernameTextInput, emailTextInput, passwordTextInput, passwordTextInput2;
    private String username, emailInput, password1, password2;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Inicializar las vistas mediante sus identificadores
        usernameTextInput = findViewById(R.id.textInputLayout1);
        emailTextInput = findViewById(R.id.textInputLayout2);
        passwordTextInput = findViewById(R.id.textInputLayout3);
        passwordTextInput2 = findViewById(R.id.textInputLayout4);
        progressBar = findViewById(R.id.progressBar);

        Button signUp = findViewById(R.id.buttonSignUp);
        mAuth = FirebaseAuth.getInstance();

        // Configuración del botón de registro con un OnClickListener
        signUp.setOnClickListener(new View.OnClickListener() {
            // Llamar al método para validar la entrada del usuario
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                validarEntrada();
            }
        });
    }

    // Método para validar el campo de nombre de usuario
    private boolean validarUsername() {
        username = usernameTextInput.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            usernameTextInput.setError("El campo no puede estar vacío");
            return false;
        } else {
            usernameTextInput.setError(null);
            return true;
        }
    }

    // Método para validar el campode correo electrónico
    private boolean validarEmail() {
        emailInput = emailTextInput.getEditText().getText().toString().trim();
        String emailRegex = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailInput);

        if (emailInput.isEmpty()) {
            emailTextInput.setError("El campo no puedo estar vacío");
            return false;
        } else if (matcher.matches()) {
            emailTextInput.setError(null);
            return true;
        } else {
            emailTextInput.setError("Introduzca un email valido.");
            return false;
        }
    }

    // Método para validar el campo de contraseña 1
    private boolean validarPassword() {
        password1 = passwordTextInput.getEditText().getText().toString().trim();
        if (password1.isEmpty()) {
            passwordTextInput.setError("El campo no puede estar vacío");
            return false;
        } else {
            passwordTextInput.setError(null);
            return true;
        }
    }

    // Método para validar el campo de contraseña 2
    private boolean validarPassword2() {
        password2 = passwordTextInput2.getEditText().getText().toString().trim();
        if (password2.isEmpty()) {
            passwordTextInput2.setError("El campo no puede estar vacío");
            return false;
        } else {
            passwordTextInput2.setError(null);
            return true;
        }
    }

    // Método para verificar que las contraseñas coincidan
    private boolean validarPassword3() {
        if (!password2.equals(password1)) {
            Snackbar.make((findViewById(R.id.signUpActivity)), R.string.las_contrasenas_deben_coincidir, Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // Método para validar la entrada del usuario
    private void validarEntrada() {
        // Validar todos lo campos antes de continuar
        if (!validarEmail() | !validarUsername() | !validarPassword() | !validarPassword2() | !validarPassword3()) {
            progressBar.setVisibility(View.GONE);
            return;
        }
        // Si la validación es existosa, registrar al usuario
        registrarUsuario(emailInput, password1);
    }

    // Método para registrar al usuario a través de Firebase
    private void registrarUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Snackbar.make((findViewById(R.id.signUpActivity)), R.string.cuenta_creada, Snackbar.LENGTH_SHORT).show();
                            // Retrasar la terminación de la actividad
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        } else {
                            Snackbar.make((findViewById(R.id.signUpActivity)), R.string.autentificacion_fallida, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}