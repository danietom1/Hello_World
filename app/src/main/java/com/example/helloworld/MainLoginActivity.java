package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

    public class MainLoginActivity extends AppCompatActivity {
        
        private EditText mEditTextEmail;
        private EditText mEditTextContraseña;
        private Button mButtonRegistrar;
        private Button mButtonLogin;

        //Datos a Registrar
        private String email = "";
        private String contraseña = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.authentication);

            //instanciar editText
            mEditTextEmail = (EditText) findViewById(R.id.EditEmailAddress);
            mEditTextContraseña = (EditText) findViewById(R.id.EditPassword);
            mButtonRegistrar = (Button) findViewById(R.id.Registrarbutton);
            mButtonLogin = (Button) findViewById(R.id.Loginbutton);



            //Accion Boton
            mButtonRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email = mEditTextEmail.getText().toString();
                    contraseña = mEditTextContraseña.getText().toString();

                    //engresa o no ingresa datos
                    if (!email.isEmpty() && !contraseña.isEmpty() ){
                        registerUser()
;                    }

                }
            });
        }
}
