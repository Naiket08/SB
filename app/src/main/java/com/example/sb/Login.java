package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    ImageView imageViewWaveTopPinOne;
    TextView textViewPinOne,textViewPinOnetwo,forgetpassword;
    EditText username,Password;
    ImageButton submitbutton;
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

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(10,0);
                finish();
            }
        });

    }
}