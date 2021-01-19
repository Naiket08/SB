package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RePin extends AppCompatActivity {
    ImageView imageViewWaveRePinOne,lineRepin1,lineRepin2,lineRepin3,lineRepin4;
    TextView textViewRePin,textViewRePintwo;
    EditText numberRepin1,numberRepin2,numberRepin3,numberRepin4;
    ImageButton create,oneRepin,twoRepin,threeRepin,fourRepin,fiveRepin ,sixRepin,sevenRepin,eightRepin,nineRepin,zeroRepin,emptyRepin,erazeRepin,correctsucesspin;
    public int i=0,y=0;
    String s;
    public Drawable linedone,line;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pin);
        linedone=(Drawable)getResources().getDrawable(R.drawable.linedone);
        line=(Drawable)getResources().getDrawable(R.drawable.line);
        imageViewWaveRePinOne = (ImageView) findViewById(R.id.imageViewWaveTopPinOne);
        lineRepin1=(ImageView)findViewById(R.id.lineRepin1);
        lineRepin2=(ImageView)findViewById(R.id.lineRepin2);
        lineRepin3=(ImageView)findViewById(R.id.lineRepin3);
        lineRepin4=(ImageView)findViewById(R.id.lineRepin4);
        textViewRePin=(TextView)findViewById(R.id.textViewRePin);
        textViewRePintwo=(TextView)findViewById(R.id.textViewRePintwo);
        numberRepin1=(EditText) findViewById(R.id.edittextRepin1);
        numberRepin2=(EditText) findViewById(R.id.edittextRepin2);
        numberRepin3=(EditText) findViewById(R.id.edittextRepin3);
        numberRepin4=(EditText) findViewById(R.id.edittextRepin4);
        create=(ImageButton)findViewById(R.id.create);
        oneRepin=(ImageButton)findViewById(R.id.oneRepin);
        twoRepin=(ImageButton)findViewById(R.id.twoRepin);
        threeRepin=(ImageButton)findViewById(R.id.threeRepin);
        fourRepin=(ImageButton)findViewById(R.id.fourRepin);
        fiveRepin=(ImageButton)findViewById(R.id.fiveRepin);
        sixRepin=(ImageButton)findViewById(R.id.sixRepin);
        sevenRepin=(ImageButton)findViewById(R.id.sevenRepin);
        eightRepin=(ImageButton)findViewById(R.id.eightRepin);
        nineRepin=(ImageButton)findViewById(R.id.nineRepin);
        emptyRepin=(ImageButton)findViewById(R.id.emptyRepin);
        zeroRepin=(ImageButton)findViewById(R.id.zeroRepin);
        erazeRepin=(ImageButton)findViewById(R.id.erazeRepin);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==4)
                {

                    setContentView(R.layout.activity_re_pin_two);



                }
                else
                {
                    Toast.makeText(RePin.this, "enter your Pin", Toast.LENGTH_SHORT).show();
                        }

            }

        });



        if(i<4) {
            oneRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 1;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            twoRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 2;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            threeRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 3;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            fourRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 4;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            fiveRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 5;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            sixRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 6;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            sevenRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 7;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            eightRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 8;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            nineRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 9;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            emptyRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            zeroRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 0;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            erazeRepin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletenumber();
                    if(i==0){

                    }
                    else {
                        --i;
                    }
                }
            });
        }
        else
        {
            i=5;
            Toast.makeText(this, "Entry FUll", Toast.LENGTH_SHORT).show();

        }



    }
    void enternumber()
    {
        if(i==1){
            numberRepin1.setText(s);
            lineRepin1.setBackground(linedone);
        }
        else if(i==2){
            numberRepin2.setText(s);
            lineRepin2.setBackground(linedone);

        }
        else if(i==3){
            numberRepin3.setText(s);
            lineRepin3.setBackground(linedone);

        }
        else if(i==4){
            numberRepin4.setText(s);
            lineRepin4.setBackground(linedone);

        }
        else
        {
            i=4;
            Toast.makeText(this, "ENTRY FULL", Toast.LENGTH_SHORT).show();
        }

    }
    void deletenumber()
    {

        if(i==1){
            numberRepin1.setText(null);
            lineRepin1.setBackground(line);
        }
        else if(i==2){
            numberRepin2.setText(null);
            lineRepin2.setBackground(line);

        }
        else if(i==3){
            numberRepin3.setText(null);
            lineRepin3.setBackground(line);

        }
        else if(i==4){
            numberRepin4.setText(null);
            lineRepin4.setBackground(line);

        }
        else
        {

            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }


    }
}