package com.example.sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RePin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView imageViewWaveRePinOne,lineRepin1,lineRepin2,lineRepin3,lineRepin4,touch;
    TextView textViewRePin,textViewRePintwo;
    EditText numberRepin1,numberRepin2,numberRepin3,numberRepin4;
    ImageButton create,oneRepin,twoRepin,threeRepin,fourRepin,fiveRepin ,sixRepin,sevenRepin,eightRepin,nineRepin,zeroRepin,emptyRepin,erazeRepin,imageButtonclickable;
    public int i=0,y=0;
    String s;
    public Drawable linedone,line;
    String num1,num2,num3,num4;


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

        touch=(ImageButton)findViewById(R.id.imageButtonclickable);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("Pin");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                num1 = dataSnapshot.child("Pin1").getValue(String.class);
                num2 = dataSnapshot.child("Pin2").getValue(String.class);
                num3 = dataSnapshot.child("Pin3").getValue(String.class);
                num4 = dataSnapshot.child("Pin4").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==4)
                {
                    /*DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("Pin");
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            num1 = dataSnapshot.child("Pin1").getValue(String.class);
                            num2 = dataSnapshot.child("Pin2").getValue(String.class);
                            num3 = dataSnapshot.child("Pin3").getValue(String.class);
                            num4 = dataSnapshot.child("Pin4").getValue(String.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/

                    //setContentView(R.layout.activity_re_pin_two);

                    String nr1,nr2,nr3,nr4;
                    nr1 = numberRepin1.getText().toString().trim();
                    nr2 = numberRepin2.getText().toString().trim();
                    nr3 = numberRepin3.getText().toString().trim();
                    nr4 = numberRepin4.getText().toString().trim();

                    if(nr1.equals(num1)&&nr2.equals(num2)&&nr3.equals(num3)&&nr4.equals(num4)){
                        //dialog();
                        final Dialog myDialog = new Dialog(RePin.this);
                        myDialog.setContentView(R.layout.activity_re_pin_two);

                        ImageView touch=(ImageButton)myDialog.findViewById(R.id.imageButtonclickable);
                        touch.setOnClickListener(this);

                        touch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String n1,n2,n3,n4;
                                n1 = numberRepin1.getText().toString().trim();
                                n2 = numberRepin2.getText().toString().trim();
                                n3 = numberRepin3.getText().toString().trim();
                                n4 = numberRepin4.getText().toString().trim();
                                Intent intent = new Intent(RePin.this,FinalPin.class);
                                intent.putExtra("Finalnumber1",n1);
                                intent.putExtra("Finalnumber2",n2);
                                intent.putExtra("Finalnumber3",n3);
                                intent.putExtra("Finalnumber4",n4);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                overridePendingTransition(10,0);
                                finish();

                            }
                        });
                        myDialog.show();
                    }
                    else{
                        Toast.makeText(RePin.this, "Pin does not match", Toast.LENGTH_SHORT).show();
                    }
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