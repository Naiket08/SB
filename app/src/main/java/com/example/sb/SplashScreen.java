package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView imageViewEllipse11,imageViewEllipse12,imageViewEllipse13,imageViewEllipse14,imageViewEllipse15,imageViewEllipse16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageViewEllipse11 = (ImageView)findViewById(R.id.imageViewEllipse11);
        imageViewEllipse12 = (ImageView)findViewById(R.id.imageViewEllipse12);
        imageViewEllipse13 = (ImageView)findViewById(R.id.imageViewEllipse13);
        imageViewEllipse14 = (ImageView)findViewById(R.id.imageViewEllipse14);
        imageViewEllipse15 = (ImageView)findViewById(R.id.imageViewEllipse15);
        imageViewEllipse16 = (ImageView)findViewById(R.id.imageViewEllipse16);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,TermsAndConditions.class);
                startActivity(i);
                finish();
            }
        },5000);
    }
}