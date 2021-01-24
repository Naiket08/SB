package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView imageViewEllipse11,imageViewEllipse12,imageViewEllipse13,imageViewEllipse14,imageViewEllipse15,imageViewEllipse16,imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        imageViewEllipse11 = (ImageView)findViewById(R.id.imageViewEllipse11);
        imageViewEllipse12 = (ImageView)findViewById(R.id.imageViewEllipse12);
        imageViewEllipse13 = (ImageView)findViewById(R.id.imageViewEllipse13);
        imageViewEllipse14 = (ImageView)findViewById(R.id.imageViewEllipse14);
        imageViewEllipse15 = (ImageView)findViewById(R.id.imageViewEllipse15);
        imageViewEllipse16 = (ImageView)findViewById(R.id.imageViewEllipse16);
        imageViewLogo = (ImageView)findViewById(R.id.imageViewLogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,Home.class);
                startActivity(i);
                finish();
            }
        },1000);
    }
}