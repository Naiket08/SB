package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TermsAndConditions extends AppCompatActivity {

    ImageView imageViewWaveTopTandC;
    TextView textViewTermsandConditions,textViewtandc1,textViewtandc2,textViewtandc3,textViewtandc4,textViewAcceptTandC;
    CheckBox checkBoxAccept;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_terms_and_conditions);

        imageViewWaveTopTandC = (ImageView)findViewById(R.id.imageViewWaveTopTandC);
        textViewTermsandConditions = (TextView)findViewById(R.id.textViewTermsandConditions);
        textViewtandc1 = (TextView)findViewById(R.id.textViewtandc1);
        textViewtandc2 = (TextView)findViewById(R.id.textViewtandc2);
        textViewtandc3 = (TextView)findViewById(R.id.textViewtandc3);
        textViewtandc4 = (TextView)findViewById(R.id.textViewtandc4);
        textViewAcceptTandC = (TextView)findViewById(R.id.textVeiwAcceptTandC);
        checkBoxAccept = (CheckBox)findViewById(R.id.checkBoxAccept);
        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxAccept.isChecked()){

                    Intent intent = new Intent(TermsAndConditions.this,PinOne.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
                else
                {
                    Toast.makeText(TermsAndConditions.this, "Please Accept the T&C", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}