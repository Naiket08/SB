package com.example.sb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserProfile extends AppCompatActivity {

    Button buttonBackUserProfile,buttonKillUserProfile,buttonLogoutUserProfile;
    TextView textViewUserProfile,textViewUserID,textViewUserProfileMail,textViewUserProfileMailValue,textViewUserProfilePhone,
            textViewUserProfilePhoneValue;
    CardView cardViewUserProfile;
    ImageView imageViewUserProfileImage;
    Spinner spinnerLoggedInDevices;

    private FirebaseAuth mAuth;

    //Profile Image Initializations
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;

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

        //Profile Image Code
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        StorageReference photoref = storageReference.child("images/"+mAuth.getCurrentUser().getUid().toString());
        photoref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(imageViewUserProfileImage);
                //Toast.makeText(UserProfile.this, "URI "+uri, Toast.LENGTH_SHORT).show();
            }
        });
        //Glide.with(this).load(downloaduri).into(imageViewUserProfileImage);

        cardViewUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

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

    private void SelectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image From here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST
            && resultCode == RESULT_OK
            && data != null
            && data.getData() != null){

            filepath = data.getData();
            try{
                Bitmap bitmap = MediaStore
                                    .Images
                                    .Media
                                    .getBitmap(
                                            getContentResolver(),
                                            filepath);
                imageViewUserProfileImage.setImageBitmap(bitmap);
            }

            catch (IOException e){
                e.printStackTrace();
            }

            //Upload image to firebase storage
            if(filepath != null){
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageReference.child("images/"+mAuth.getCurrentUser().getUid().toString());
                ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(UserProfile.this, "Image Uploaded!", Toast.LENGTH_SHORT).show();
                    }
                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(UserProfile.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })

                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+(int)progress + "%");
                            }
                        });
            }

        }
    }
}