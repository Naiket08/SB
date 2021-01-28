package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {

    Button buttonBackUserProfile,buttonKillUserProfile,buttonLogoutUserProfile;
    TextView textViewUserProfile,textViewUserID,textViewUserProfileMail,textViewUserProfileMailValue,textViewUserProfilePhone,
            textViewUserProfilePhoneValue;
    CardView cardViewUserProfile;
    ImageView imageViewUserProfileImage;
    Spinner spinnerLoggedInDevices;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        buttonBackUserProfile = (Button)findViewById(R.id.buttonBackUserProfile);
        buttonLogoutUserProfile = (Button)findViewById(R.id.buttonLogoutUserProfile);
        buttonKillUserProfile = (Button)findViewById(R.id.buttonKillUserProfile);
        textViewUserProfile = (TextView)findViewById(R.id.textViewUserProfile);
        textViewUserID = (TextView)findViewById(R.id.textViewUserID);
        textViewUserProfileMail = (TextView)findViewById(R.id.textViewUserProfileMail);
        textViewUserProfileMailValue = (TextView)findViewById(R.id.textViewUserProfileMailValue);
        textViewUserProfilePhone = (TextView)findViewById(R.id.textViewUserProfilePhone);
        textViewUserProfilePhoneValue = (TextView)findViewById(R.id.textViewUserProfilePhoneValue);
        cardViewUserProfile = (CardView)findViewById(R.id.cardViewUserProfile);
        imageViewUserProfileImage = (ImageView)findViewById(R.id.imageViewUserProfileImage);
        spinnerLoggedInDevices = (Spinner)findViewById(R.id.spinnerLoggedInDevices);

        mAuth = FirebaseAuth.getInstance();

        //On Pressing Back button
        buttonBackUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buttonLogoutUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finishAffinity();
                startActivity(new Intent(UserProfile.this,Login.class));
            }
        });

    }
}