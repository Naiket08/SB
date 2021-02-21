package com.example.sb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("User Details");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("EmailID").getValue(String.class);
                String mobile = dataSnapshot.child("MobileNo").getValue(String.class);
                //Toast.makeText(UserProfile.this, email+" "+mobile, Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(mobile)){
                    Toast.makeText(UserProfile.this, "Email and Mobile not found", Toast.LENGTH_SHORT).show();
                }
                else{
                    textViewUserProfileMailValue.setText(email);
                    textViewUserProfilePhoneValue.setText(mobile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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