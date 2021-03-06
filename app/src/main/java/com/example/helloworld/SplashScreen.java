package com.example.helloworld;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animacion
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView textViewSplash = findViewById(R.id.textViewSplash);
        ImageView imageSplash = findViewById(R.id.imageSplash);

        textViewSplash.setAnimation(animacion2);
        imageSplash.setAnimation(animacion1);

        Typeface TFLogoSplash = Typeface.createFromAsset(getAssets(),"Fonts/SLANT.TTF");

        textViewSplash.setTypeface(TFLogoSplash);

        //enviar a otra pantalla
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
