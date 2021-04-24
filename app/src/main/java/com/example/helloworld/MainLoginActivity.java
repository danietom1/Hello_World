package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainLoginActivity extends AppCompatActivity {
        
        private EditText mEditTextEmail;
        private EditText mEditTextContrasena;
        private Button mButtonRegistrar;
        private Button mButtonLogin;

        //Datos a Registrar
        private String email = "";
        private String contrasena = "";

        //registro de usuario
        FirebaseAuth mAuth;
        DatabaseReference mDatabase;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.authentication);

            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();


            //instanciar editText
            mEditTextEmail = (EditText) findViewById(R.id.EditEmailAddress);
            mEditTextContrasena = (EditText) findViewById(R.id.EditPassword);
            mButtonRegistrar = (Button) findViewById(R.id.Registrarbutton);
            mButtonLogin = (Button) findViewById(R.id.Loginbutton);

            //Accion Boton Login
            mButtonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = mEditTextEmail.getText().toString();
                    contrasena = mEditTextContrasena.getText().toString();

                    if (!email.isEmpty() && !contrasena.isEmpty() ){

                        if(contrasena.length() >= 6 ){
                            loginUser();
                        }
                        else{
                            Toast.makeText(MainLoginActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                        Toast.makeText(MainLoginActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                    }

                }

                private void loginUser(){
                    mAuth.signInWithEmailAndPassword(email, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(MainLoginActivity.this, MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(MainLoginActivity.this, "No se puede iniciar sesión", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });


            //Accion Boton Registrar
            mButtonRegistrar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    email = mEditTextEmail.getText().toString();
                    contrasena = mEditTextContrasena.getText().toString();

                    //engresa o no ingresa datos
                    if (!email.isEmpty() && !contrasena.isEmpty() ){

                        if(contrasena.length() >=6 ){
                            registerUser();
                        }
                        else{
                            Toast.makeText(MainLoginActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                        Toast.makeText(MainLoginActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private void registerUser(){
            mAuth.createUserWithEmailAndPassword(email, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    //ingreso a la app correctamente
                    if(task.isSuccessful()){

                        Map<String, Object> map = new HashMap<>();
                        map.put("email", email);
                        map.put("contraseña", contrasena);

                        String id = mAuth.getCurrentUser().getUid();

                        mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if (task2.isSuccessful()){
                                    //envio de usuario a otra pantalla
                                    startActivity(new Intent(MainLoginActivity.this, home.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(MainLoginActivity.this, "Los datos no se crearon correctamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(MainLoginActivity.this, "Ya existe un usuario con este correo", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }




}
