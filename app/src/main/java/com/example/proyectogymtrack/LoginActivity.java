package com.example.proyectogymtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Clase que controla la lógica de la pantalla de Login.
 *
 * @author Daniel Ansias.
 */
public class LoginActivity extends AppCompatActivity {

    // Declaración de variables
    private TextView tvPassword;
    private TextInputLayout emailTextInput, passwordTextInput;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder resel_alert;
    private LayoutInflater inflater;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicialización de las vistas mediante sus ids
        emailTextInput = findViewById(R.id.textInputEmail);
        passwordTextInput = findViewById(R.id.textInputPassword);
        tvPassword = findViewById(R.id.textViewPassword);
        progressBar = findViewById(R.id.progressBar);
        TextView tvSignUp = findViewById(R.id.textViewSignUp);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        resel_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        mAuth = FirebaseAuth.getInstance();

        // Configuración del botón de inicio de sesión con un Listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                // Obtener el texto de los campos de email y contraseña
                String email = emailTextInput.getEditText().getText().toString().trim();
                String password = passwordTextInput.getEditText().getText().toString().trim();
                // Verificar que los campos no estén vacios
                if (!"".equals(email) && !"".equals(password)) {
                    validarUsuario(email, password);
                } else {
                    progressBar.setVisibility(View.GONE);
                    // Mensaje de error en caso de que estén vacios
                    Snackbar.make((findViewById(R.id.loginActivity)), R.string.email_y_o_contrasena_incorrecto,Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        // Implementación de un Listener en el TextView para navegar a la pantalla de cambio de contraseña
        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPassword.setText("Menú contraseña");
                // Lógica para establecer nueva contraseña
            }
        });

        // Implementación de un Listener en el TextView para navegar a la pantalla de registro
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Navegar a la página de registro mediante un Intent
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Listener para el boton de cambiar la contrasela
        tvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflater.inflate(R.layout.reset_password, null);
                resel_alert.setTitle(R.string.olvidaste_tu_contrasena)
                        .setMessage(R.string.introduce_tu_email_para_obtener_el_enlace)
                        .setPositiveButton(R.string.reestablecer, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText email = view.findViewById(R.id.reset_email_pop);
                                if (email.getText().toString().isEmpty()) {
                                    email.setError(getString(R.string.el_campo_esta_vacio));
                                    return;
                                }
                                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make((findViewById(R.id.loginActivity)), R.string.email_enviado,Snackbar.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make((findViewById(R.id.loginActivity)), e.getMessage(),Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }).setNegativeButton(R.string.cancelar, null)
                        .setView(view)
                        .create().show();
            }
        });
    }

    // Método para validar la información del usuario
    private void validarUsuario(String email, String password) {
        // Logica para validar el usuario contra la base de datos
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Después de la validación, iniciar la SplashActivity que llevará al usuario a la pantalla principal
                            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                            startActivity(intent);
                            // Terminar la actividad
                            finish();
                        } else {
                            Snackbar.make((findViewById(R.id.loginActivity)), R.string.autentificacion_fallida, Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}