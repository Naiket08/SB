package com.example.sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    ImageView imageViewWaveTopPinOne;
    TextView textViewPinOne,textViewPinOnetwo,forgetpassword;
    EditText username,Password;
    ImageButton submitbutton;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageViewWaveTopPinOne=(ImageView)findViewById(R.id.imageViewWaveTopPinOne);
        textViewPinOne=(TextView)findViewById(R.id.textViewPinOne);
        textViewPinOnetwo=(TextView)findViewById(R.id.textViewPinOnetwo);
        forgetpassword=(TextView)findViewById(R.id.forgetpassword);
        username=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.Password);
        submitbutton=(ImageButton)findViewById(R.id.SubmitButton);

        mAuth = FirebaseAuth.getInstance();

        //Forgot Password Functionality
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString().trim())){
                    Toast.makeText(Login.this, "Please provide valid email address", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.sendPasswordResetEmail(username.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "We have sent reset link to provided email address", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Login.this, "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString().trim())){
                    username.setError("Username is Required");
                }
                else if(TextUtils.isEmpty(Password.getText().toString().trim())){
                    Password.setError("Password is Required");
                }
                else if(TextUtils.isEmpty(username.getText().toString().trim())&&TextUtils.isEmpty(Password.getText().toString().trim())){
                    username.setError("Username is Required");
                    Password.setError("Password is Required");
                }
                else {

                    String email,password;

                    email = username.getText().toString().trim();
                    password = Password.getText().toString().trim();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Login.this,Home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                overridePendingTransition(10,0);
                                finish();
                            }
                            else{
                                Toast.makeText(Login.this, "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}