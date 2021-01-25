package com.example.sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ImageView imageViewWaveTopRegister;
    TextView textViewRegister,textViewRegisterTwo,textViewAlreadyAccount;
    EditText editTextRegisterMobileNo,editTextRegisterEmail,editTextRegisterPassword;
    Button buttonRegisterSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        imageViewWaveTopRegister = (ImageView)findViewById(R.id.imageViewWaveTopRegister);
        textViewRegister = (TextView)findViewById(R.id.textViewRegister);
        textViewRegisterTwo = (TextView)findViewById(R.id.textViewRegisterTwo);
        textViewAlreadyAccount = (TextView)findViewById(R.id.textViewAlreadyAccount);
        editTextRegisterEmail = (EditText)findViewById(R.id.editTextRegisterEmail);
        editTextRegisterMobileNo = (EditText)findViewById(R.id.editTextRegisterMobileNo);
        editTextRegisterPassword = (EditText)findViewById(R.id.editTextRegisterPassword);
        buttonRegisterSubmitButton = (Button)findViewById(R.id.buttonRegisterSubmitButton);

        mAuth = FirebaseAuth.getInstance();

        buttonRegisterSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextRegisterMobileNo.equals("")){
                    editTextRegisterMobileNo.setError("Please Enter Mobile number");
                }
                else if(editTextRegisterEmail.equals("")){
                    editTextRegisterEmail.setError("Please Enter Email ID");
                }
                else if(editTextRegisterPassword.equals("")){
                    editTextRegisterPassword.setError("Please Enter Password");
                }
                else if(editTextRegisterPassword.length()<6){
                    editTextRegisterPassword.setError("Enter more than 6 characters");
                }
                else if(editTextRegisterMobileNo.length()!=10){
                    editTextRegisterMobileNo.setError("Please Enter valid Mobile No");
                }
                else{
                    String mobileno,email,password;
                    mobileno = editTextRegisterMobileNo.getText().toString().trim();
                    email = editTextRegisterEmail.getText().toString().trim();
                    password = editTextRegisterPassword.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                /*FirebaseUser user = mAuth.getCurrentUser();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference();
                                myRef.child(user.getUid()).child("User Details").child("MobileNo").setValue(mobileno);
                                myRef.child(user.getUid()).child("User Details").child("Email ID").setValue(email);
                                myRef.child(user.getUid()).child("User Details").child("Password").setValue(password);*/
                                HashMap<String,Object> userDetails = new HashMap<>();
                                userDetails.put("MobileNo",mobileno);
                                userDetails.put("EmailID",email);
                                userDetails.put("Password",password);
                                FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("User Details").setValue(userDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegisterScreen.this, "Details added Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Toast.makeText(RegisterScreen.this, "User Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterScreen.this,PinOne.class));
                            }
                            else{
                                Toast.makeText(RegisterScreen.this, "Authentication Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}