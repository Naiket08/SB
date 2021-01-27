package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    Button buttonBackUserProfile,buttonKillUserProfile,buttonHamburgerUserProfile;
    TextView textViewUserProfile,textViewUserID,textViewUserProfileMail,textViewUserProfileMailValue,textViewUserProfilePhone,
            textViewUserProfilePhoneValue;
    CardView cardViewUserProfile;
    ImageView imageViewUserProfileImage;
    Spinner spinnerLoggedInDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        buttonBackUserProfile = (Button)findViewById(R.id.buttonBackUserProfile);
        buttonHamburgerUserProfile = (Button)findViewById(R.id.buttonHamburgerUserProfile);
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

        //On Pressing Back button
        buttonBackUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}