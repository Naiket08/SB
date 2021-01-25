package com.example.sb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PinOne extends AppCompatActivity {
    ImageView imageViewWaveTopPinOne,line1,line2,line3,line4;
    TextView textViewPinOne,textViewPinOnetwo;
    EditText number1,number2,number3,number4;
    ImageButton nextbuttonpinone,one,two,three,four,five ,six,seven,eight,nine,zero,empty,eraze;
    public int i=0,y=0;
    public String s;
    public Drawable linedone,line;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_one);
        linedone=(Drawable)getResources().getDrawable(R.drawable.linedone);
        line=(Drawable)getResources().getDrawable(R.drawable.line);
        imageViewWaveTopPinOne = (ImageView) findViewById(R.id.imageViewWaveTopPinOne);
        line1=(ImageView)findViewById(R.id.line1);
        line2=(ImageView)findViewById(R.id.line2);
        line3=(ImageView)findViewById(R.id.line3);
        line4=(ImageView)findViewById(R.id.line4);
        textViewPinOne=(TextView)findViewById(R.id.textViewPinOne);
        textViewPinOnetwo=(TextView)findViewById(R.id.textViewPinOnetwo);
        number1=(EditText) findViewById(R.id.edittext1);
        number2=(EditText) findViewById(R.id.edittext2);
        number3=(EditText) findViewById(R.id.edittext3);
        number4=(EditText) findViewById(R.id.edittext4);
        nextbuttonpinone=(ImageButton)findViewById(R.id.nextbutton);
        one=(ImageButton)findViewById(R.id.one);
        two=(ImageButton)findViewById(R.id.two);
        three=(ImageButton)findViewById(R.id.three);
        four=(ImageButton)findViewById(R.id.four);
        five=(ImageButton)findViewById(R.id.five);
        six=(ImageButton)findViewById(R.id.six);
        seven=(ImageButton)findViewById(R.id.seven);
        eight=(ImageButton)findViewById(R.id.eight);
        nine=(ImageButton)findViewById(R.id.nine);
        empty=(ImageButton)findViewById(R.id.empty);
        zero=(ImageButton)findViewById(R.id.zero);
        eraze=(ImageButton)findViewById(R.id.eraze);




        nextbuttonpinone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==4)
                {
                    String num1,num2,num3,num4;
                    num1 = number1.getText().toString().trim();
                    num2 = number2.getText().toString().trim();
                    num3 = number3.getText().toString().trim();
                    num4 = number4.getText().toString().trim();
                    Intent intent = new Intent(PinOne.this,RePin.class);
                    intent.putExtra("number1",num1);
                    intent.putExtra("number2",num2);
                    intent.putExtra("number3",num3);
                    intent.putExtra("number4",num4);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    finish();
                }
                else
                {
                    Toast.makeText(PinOne.this, "Enter the Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(i<4) {
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 1;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 2;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 3;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 4;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 5;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            six.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 6;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            seven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 7;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            eight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 8;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            nine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 9;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            empty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            zero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 0;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            eraze.setOnClickListener(new View.OnClickListener() {
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
            number1.setText(s);
            line1.setBackground(linedone);
        }
        else if(i==2){
            number2.setText(s);
            line2.setBackground(linedone);

        }
        else if(i==3){
            number3.setText(s);
            line3.setBackground(linedone);

        }
        else if(i==4){
            number4.setText(s);
            line4.setBackground(linedone);

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
            number1.setText(null);
            line1.setBackground(line);
        }
        else if(i==2){
            number2.setText(null);
            line2.setBackground(line);

        }
        else if(i==3){
            number3.setText(null);
            line3.setBackground(line);

        }
        else if(i==4){
            number4.setText(null);
            line4.setBackground(line);

        }
        else
        {

            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }

    }
}