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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class ConfirmPinToChange extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView imageViewWaveTopConfirmPin,line1ConfirmPin,line2ConfirmPin,line3ConfirmPin,line4ConfirmPin;
    TextView textViewConfirmPin,textViewConfirmPinTwo;
    EditText number1ConfirmPin,number2ConfirmPin,number3ConfirmPin,number4ConfirmPin;
    ImageButton nextbuttonConfirmPin,oneConfirmPin,twoConfirmPin,threeConfirmPin,fourConfirmPin,fiveConfirmPin ,sixConfirmPin,
            sevenConfirmPin,eightConfirmPin,nineConfirmPin,zeroConfirmPin,emptyConfirmPin,erazeConfirmPin;
    public int i=0,y=0;
    public String s;
    public Drawable linedone,line;

    //------------
    private FirebaseFirestore db = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    //-----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pin_to_change);

        linedone=(Drawable)getResources().getDrawable(R.drawable.linedone);
        line=(Drawable)getResources().getDrawable(R.drawable.line);
        imageViewWaveTopConfirmPin = (ImageView) findViewById(R.id.imageViewWaveTopConfirmPin);
        line1ConfirmPin=(ImageView)findViewById(R.id.line1ConfirmPin);
        line2ConfirmPin=(ImageView)findViewById(R.id.line2ConfirmPin);
        line3ConfirmPin=(ImageView)findViewById(R.id.line3ConfirmPin);
        line4ConfirmPin=(ImageView)findViewById(R.id.line4ConfirmPin);
        textViewConfirmPin=(TextView)findViewById(R.id.textViewConfirmPin);
        textViewConfirmPinTwo=(TextView)findViewById(R.id.textViewConfirmPinTwo);
        number1ConfirmPin=(EditText) findViewById(R.id.edittext1ConfirmPin);
        number2ConfirmPin=(EditText) findViewById(R.id.edittext2ConfirmPin);
        number3ConfirmPin=(EditText) findViewById(R.id.edittext3ConfirmPin);
        number4ConfirmPin=(EditText) findViewById(R.id.edittext4ConfirmPin);
        nextbuttonConfirmPin=(ImageButton)findViewById(R.id.nextbuttonConfirmPin);
        oneConfirmPin=(ImageButton)findViewById(R.id.oneConfirmPin);
        twoConfirmPin=(ImageButton)findViewById(R.id.twoConfirmPin);
        threeConfirmPin=(ImageButton)findViewById(R.id.threeConfirmPin);
        fourConfirmPin=(ImageButton)findViewById(R.id.fourConfirmPin);
        fiveConfirmPin=(ImageButton)findViewById(R.id.fiveConfirmPin);
        sixConfirmPin=(ImageButton)findViewById(R.id.sixConfirmPin);
        sevenConfirmPin=(ImageButton)findViewById(R.id.sevenConfirmPin);
        eightConfirmPin=(ImageButton)findViewById(R.id.eightConfirmPin);
        nineConfirmPin=(ImageButton)findViewById(R.id.nineConfirmPin);
        emptyConfirmPin=(ImageButton)findViewById(R.id.emptyConfirmPin);
        zeroConfirmPin=(ImageButton)findViewById(R.id.zeroConfirmPin);
        erazeConfirmPin=(ImageButton)findViewById(R.id.erazeConfirmPin);
        mAuth = FirebaseAuth.getInstance();

        //Confirm that entered pin is correct or not
        nextbuttonConfirmPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==4)
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID;
                    userID = user.getUid();
                    //this part added-----------
                    userId = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userId);
                    //------------

                    String num1,num2,num3,num4;
                    num1 = number1ConfirmPin.getText().toString().trim();
                    num2 = number2ConfirmPin.getText().toString().trim();
                    num3 = number3ConfirmPin.getText().toString().trim();
                    num4 = number4ConfirmPin.getText().toString().trim();
                    HashMap<String,Object> pin = new HashMap<>();
                    pin.put("Pin1",num1);
                    pin.put("Pin2",num2);
                    pin.put("Pin3",num3);
                    pin.put("Pin4",num4);
                    db.collection("users").document(userId).set(pin, SetOptions.merge());
                    //userDetails.put("Password",password);




                    FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("Pin").setValue(pin).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ConfirmPinToChange.this, "Pin added", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent intent = new Intent(ConfirmPinToChange.this,RePin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    finish();
                }
                else
                {
                    Toast.makeText(ConfirmPinToChange.this, "Enter the Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if(i<4) {
            oneConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 1;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            twoConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 2;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            threeConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 3;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            fourConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 4;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            fiveConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 5;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            sixConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 6;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            sevenConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 7;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            eightConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 8;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            nineConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 9;
                    s = String.valueOf(y);
                    enternumber();

                }
            });
            emptyConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            zeroConfirmPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ++i;
                    y = 0;
                    s = String.valueOf(y);
                    enternumber();
                }
            });
            erazeConfirmPin.setOnClickListener(new View.OnClickListener() {
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
            number1ConfirmPin.setText(s);
            line1ConfirmPin.setBackground(linedone);
        }
        else if(i==2){
            number2ConfirmPin.setText(s);
            line2ConfirmPin.setBackground(linedone);

        }
        else if(i==3){
            number3ConfirmPin.setText(s);
            line3ConfirmPin.setBackground(linedone);

        }
        else if(i==4){
            number4ConfirmPin.setText(s);
            line4ConfirmPin.setBackground(linedone);

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
            number1ConfirmPin.setText(null);
            line1ConfirmPin.setBackground(line);
        }
        else if(i==2){
            number2ConfirmPin.setText(null);
            line2ConfirmPin.setBackground(line);

        }
        else if(i==3){
            number3ConfirmPin.setText(null);
            line3ConfirmPin.setBackground(line);

        }
        else if(i==4){
            number4ConfirmPin.setText(null);
            line4ConfirmPin.setBackground(line);

        }
        else
        {

            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        }

    }
}