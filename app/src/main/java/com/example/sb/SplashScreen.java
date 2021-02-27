package com.example.sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public String check;

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

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("User Details");
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    check = dataSnapshot.child("Roomfragment").getValue(String.class);
                    Toast.makeText(SplashScreen.this,check, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(SplashScreen.this,check, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user!=null){

                   /* startActivity(new Intent(SplashScreen.this, Home.class)
                            .putExtra("check", check));*/
                    startActivity(new Intent(SplashScreen.this,Home.class));
                }
                else{


                    startActivity(new Intent(SplashScreen.this,RegisterScreen.class));

                }
                finish();
            }
        },4000);
    }
}