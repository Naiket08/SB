package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FinalPin extends AppCompatActivity {
    ImageView imageViewWaveFinalPin,lineFinalPin1,lineFinalPin2,lineFinalPin3,lineFinalPin4;
    TextView textViewFinalPin,textViewFinalPintwo;
    EditText edittextFinalPin1,edittextFinalPin2,edittextFinalPin3,edittextFinalPin4;
    ImageButton submitFinalPin,oneFinalPin,twoFinalPin,threeFinalPin,fourFinalPin,fiveFinalPin ,sixFinalPin,sevenFinalPin,eightFinalPin,nineFinalPin,zeroFinalPin,emptyFinalPin,erazeFinalPin;
    public int i1=0,y1=0;
    String s1;
    public Drawable linedone,line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_pin);

        imageViewWaveFinalPin=(ImageView)findViewById(R.id.imageViewWaveFinalPin);
        lineFinalPin1=(ImageView)findViewById(R.id.lineFinalPin1);
        lineFinalPin2=(ImageView)findViewById(R.id.lineFinalPin2);
        lineFinalPin3=(ImageView)findViewById(R.id.lineFinalPin3);
        lineFinalPin4=(ImageView)findViewById(R.id.lineFinalPin4);
        linedone=(Drawable)getResources().getDrawable(R.drawable.linedone);
        line=(Drawable)getResources().getDrawable(R.drawable.line);
        textViewFinalPin=(TextView)findViewById(R.id.textViewFinalPin);
        textViewFinalPintwo=(TextView)findViewById(R.id.textViewFinalPintwo);

        edittextFinalPin1=(EditText)findViewById(R.id.edittextFinalPin1);
        edittextFinalPin2=(EditText)findViewById(R.id.edittextFinalPin2);
        edittextFinalPin3=(EditText)findViewById(R.id.edittextFinalPin3);
        edittextFinalPin4=(EditText)findViewById(R.id.edittextFinalPin4);
        submitFinalPin=(ImageButton)findViewById(R.id.submitFinalPin);
        oneFinalPin=(ImageButton)findViewById(R.id.oneFinalPin);
        twoFinalPin=(ImageButton)findViewById(R.id.twoFinalPin);
        threeFinalPin=(ImageButton)findViewById(R.id.threeFinalPin);
        fourFinalPin=(ImageButton)findViewById(R.id.fourFinalPin);
        fiveFinalPin=(ImageButton)findViewById(R.id.fiveFinalPin);
        sixFinalPin=(ImageButton)findViewById(R.id.sixFinalPin);
        sevenFinalPin=(ImageButton)findViewById(R.id.sevenFinalPin);
        eightFinalPin=(ImageButton)findViewById(R.id.eightFinalPin);
        nineFinalPin=(ImageButton)findViewById(R.id.nineFinalPin);
        emptyFinalPin=(ImageButton)findViewById(R.id.emptyFinalPin);
        zeroFinalPin=(ImageButton)findViewById(R.id.zeroFinalPin);
        erazeFinalPin=(ImageButton)findViewById(R.id.erazeFinalPin);

        submitFinalPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i1==4)
                {
                    String num1 = null,num2 = null,num3 = null,num4 = null;
                    Intent ib = getIntent();
                    Bundle extras = ib.getExtras();

                    if(extras!=null){
                        num1 = extras.getString("Finalnumber1");
                        num2 = extras.getString("Finalnumber2");
                        num3 = extras.getString("Finalnumber3");
                        num4 = extras.getString("Finalnumber4");
                    }
                    //setContentView(R.layout.activity_re_pin_two);

                    String nr1,nr2,nr3,nr4;
                    nr1 = edittextFinalPin1.getText().toString().trim();
                    nr2 = edittextFinalPin2.getText().toString().trim();
                    nr3 = edittextFinalPin3.getText().toString().trim();
                    nr4 = edittextFinalPin4.getText().toString().trim();

                    if(nr1.equals(num1)&&nr2.equals(num2)&&nr3.equals(num3)&&nr4.equals(num4)){
                        //dialog();

                                String n1,n2,n3,n4;
                                n1 = edittextFinalPin1.getText().toString().trim();
                                n2 = edittextFinalPin2.getText().toString().trim();
                                n3 = edittextFinalPin3.getText().toString().trim();
                                n4 = edittextFinalPin4.getText().toString().trim();
                                Intent intent = new Intent(FinalPin.this,Login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                overridePendingTransition(10,0);
                                finish();


                    }
                    else{
                        Toast.makeText(FinalPin.this, "Pin does not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(FinalPin.this, "enter your Pin", Toast.LENGTH_SHORT).show();
                }

            }

        });

        if(i1<4) {
            oneFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 1;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            twoFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 2;
                    s1 = String.valueOf(y1);
                    enternumber();
                }
            });
            threeFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 3;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            fourFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 4;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            fiveFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 5;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            sixFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 6;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            sevenFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 7;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            eightFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 8;
                    s1= String.valueOf(y1);
                    enternumber();
                }
            });
            nineFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1= 9;
                    s1 = String.valueOf(y1);
                    enternumber();

                }
            });
            emptyFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            zeroFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i1;
                    y1 = 0;
                    s1 = String.valueOf(y1);
                    enternumber();
                }
            });
            erazeFinalPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletenumber();
                    if(i1==0){

                    }
                    else {
                        --i1;
                    }
                }
            });
        }
        else
        {
            i1=5;
            Toast.makeText(this, "Entry FUll", Toast.LENGTH_SHORT).show();

        }



    }


    void enternumber()
    {
        if(i1==1){
            edittextFinalPin1.setText(s1);
            lineFinalPin1.setBackground(linedone);
        }
        else if(i1==2){
            edittextFinalPin2.setText(s1);
            lineFinalPin2.setBackground(linedone);

        }
        else if(i1==3){
            edittextFinalPin3.setText(s1);
            lineFinalPin3.setBackground(linedone);

        }
        else if(i1==4){
            edittextFinalPin4.setText(s1);
            lineFinalPin4.setBackground(linedone);

        }
        else
        {
            i1=4;
            Toast.makeText(this, "ENTRY FULL", Toast.LENGTH_SHORT).show();
        }

    }
    void deletenumber()
    {

        if(i1==1){
            edittextFinalPin1.setText(null);
            lineFinalPin1.setBackground(line);
        }
        else if(i1==2){
            edittextFinalPin2.setText(null);
            lineFinalPin2.setBackground(line);

        }
        else if(i1==3){
            edittextFinalPin3.setText(null);
            lineFinalPin3.setBackground(line);

        }
        else if(i1==4){
            edittextFinalPin4.setText(null);
            lineFinalPin4.setBackground(line);

        }
        else
        {

            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }


    }
}