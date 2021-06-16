package com.example.sb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapterInnerSwitchboard extends RecyclerView.Adapter<CustomAdapterInnerSwitchboard.ViewHolder> {
    ArrayList LightName,LightName2,LightName3;
    ArrayList LightType,LightType2,LightType3;
    TextView textviewdialview;
    TextView acvalue,acantina;
    ImageButton acupbutton,acdownbutton;
    LinearLayout llback;
    ImageView imageViewBrownJacket,imageViewWhiteJacket,imageViewAppliances,imageViewBulb,imageViewMain;
    Button speedcontrol;
    FanKnob customdial;
    public String Roomname,s3,text3,num1,num2,x;
    public String fanspeed;
    private FirebaseAuth mAuth;
    public Integer temp;
    Context context;
    ProgressDialog progressDoalog;


    public CustomAdapterInnerSwitchboard(Context context, ArrayList LightName, ArrayList LightName2, ArrayList LightName3, ArrayList LightType, ArrayList LightType2, ArrayList LightType3, FirebaseAuth mAuth, String Roomname, String text3, TextView textviewdialview, ImageView imageViewBrownJacket, ImageView  imageViewWhiteJacket, ImageView imageViewAppliances, ImageView imageViewBulb, FanKnob customdial, Button speedcontrol, TextView acantina, LinearLayout llback, TextView acvalue, ImageButton acupbutton, ImageButton acdownbutton, ImageView imageViewMain) {


        this.context = context;
        this.LightName = LightName;
        this.LightName2 = LightName2;
        this.LightName3 = LightName3;
        this.LightType = LightType;
        this.LightType2=LightType2;
        this.LightType3=LightType3;
        this.Roomname=Roomname;
        this.acantina=acantina;
        this.llback=llback;
        this.acvalue=acvalue;
        this.acupbutton=acupbutton;
        this.acdownbutton=acdownbutton;
        this.textviewdialview=textviewdialview;
        this.imageViewBrownJacket=imageViewBrownJacket;
        this.imageViewWhiteJacket=imageViewWhiteJacket;
        this.imageViewAppliances=imageViewAppliances;
        this.imageViewBulb=imageViewBulb;
        this.imageViewMain=imageViewMain;
        this.customdial=customdial;
        this.speedcontrol=speedcontrol;
        this.text3=text3;
        this.mAuth=mAuth;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_inner_switchboard, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewInnerSwitchboardSB1.setText(String.valueOf(LightName.get(position)));
        holder.textViewInnerSwitchboardmain.setText(String.valueOf(LightName2.get(position)));
        holder.textViewInnerSwitchboardcategory.setText(String.valueOf(LightName3.get(position)));

        holder.innerSwitchboardbutton1.setImageResource((Integer)LightType.get(position));
        holder.imageViewInnerSwitchboard1.setImageResource((Integer)LightType2.get(position));
        holder.imageViewInnerSwitchboardInfo.setImageResource((Integer)LightType3.get(position));
        //icon display
        holder.innerSwitchboardbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num2=holder.textViewInnerSwitchboardcategory.getText().toString();
               // Toast.makeText(context, num2+" : Selected", Toast.LENGTH_SHORT).show();

                if(num2.contains("Fan")){
                   // Toast.makeText(context,"enter inside", Toast.LENGTH_SHORT).show();
                    imageViewBrownJacket.setVisibility(View.INVISIBLE);
                    imageViewBulb.setVisibility(View.INVISIBLE);
                    textviewdialview.setVisibility(View.VISIBLE);
                    imageViewAppliances.setVisibility(View.INVISIBLE);
                    customdial.setVisibility(View.VISIBLE);
                    imageViewWhiteJacket.setVisibility(View.VISIBLE);
                    //ac
                    acantina.setVisibility(View.INVISIBLE);
                    llback.setVisibility(View.INVISIBLE);
                    acvalue.setVisibility(View.INVISIBLE);
                    acupbutton.setVisibility(View.INVISIBLE);
                    acdownbutton.setVisibility(View.INVISIBLE);
                    imageViewMain.setVisibility(View.INVISIBLE);

                    customdial.setOnStateChanged(new FanKnob.OnStateChanged() {
                        @Override
                        public void onState(int state) {
                            textviewdialview.setText(Integer.toString(state));
                            fanspeed=Integer.toString(customdial.getState());
                            //customdial.fancontroller;
                            //Toast.makeText(context, fanspeed, Toast.LENGTH_SHORT).show();

                            mAuth = FirebaseAuth.getInstance();
                            String text = holder.textViewInnerSwitchboardSB1.getText().toString();



                                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3);
                                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                                        @Override
                                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                            String s3 = dataSnapshot.getKey();

                                                            String s5 = dataSnapshot.child("speed").getValue(String.class);
                                                            //String s6 = dataSnapshot.child("category").getValue(String.class);
                                                            //  Toast.makeText(context, s5, Toast.LENGTH_SHORT).show();

                                                            if (s3.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                                                                db.child("speed").setValue(fanspeed).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                       // Toast.makeText(context, "Speed Changed", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });

                                                            }


                                                        }

                                                        @Override
                                                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                        }

                                                        @Override
                                                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                                        }

                                                        @Override
                                                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
                                                    ////////////////////////////////////////





                            ////////////////////////////////////////////////////////////////////////////////////



                        }
                    });


                }

                else if(num2.contains("Light"))
                {


                    if(holder.imageViewInnerSwitchboard1.getDrawable().getConstantState()== holder.imageViewInnerSwitchboard1.getResources().getDrawable( R.drawable.powerred).getConstantState())
                    {
                    //    Toast.makeText(context, "RED", Toast.LENGTH_SHORT).show();
                        imageViewBrownJacket.setVisibility(View.VISIBLE);
                        imageViewBulb.setVisibility(View.VISIBLE);
                        imageViewBulb.setImageResource(R.drawable.bulboff);
                    }
                    else
                    {
                     //   Toast.makeText(context, "Green", Toast.LENGTH_SHORT).show();
                        imageViewBrownJacket.setVisibility(View.VISIBLE);
                        imageViewBulb.setImageResource(R.drawable.bulbon);
                        imageViewBulb.setVisibility(View.VISIBLE);

                    }
                    imageViewAppliances.setVisibility(View.INVISIBLE);
                    customdial.setVisibility(View.INVISIBLE);
                    textviewdialview.setVisibility(View.INVISIBLE);
                    imageViewWhiteJacket.setVisibility(View.INVISIBLE);
                    speedcontrol.setVisibility(View.INVISIBLE);
                    acantina.setVisibility(View.INVISIBLE);
                    llback.setVisibility(View.INVISIBLE);
                    acvalue.setVisibility(View.INVISIBLE);
                    acupbutton.setVisibility(View.INVISIBLE);
                    acdownbutton.setVisibility(View.INVISIBLE);
                    imageViewMain.setVisibility(View.INVISIBLE);


                }
                else if(num2.contains("AC"))
                {

                    imageViewBrownJacket.setVisibility(View.INVISIBLE);
                    imageViewBulb.setVisibility(View.INVISIBLE);
                    textviewdialview.setVisibility(View.INVISIBLE);
                    imageViewAppliances.setVisibility(View.INVISIBLE);
                    customdial.setVisibility(View.INVISIBLE);
                    imageViewWhiteJacket.setVisibility(View.VISIBLE);
                    imageViewMain.setVisibility(View.INVISIBLE);
                    //ac
                    acantina.setVisibility(View.VISIBLE);
                    llback.setVisibility(View.VISIBLE);
                    acvalue.setVisibility(View.VISIBLE);
                    acupbutton.setVisibility(View.VISIBLE);
                    acdownbutton.setVisibility(View.VISIBLE);


                    mAuth = FirebaseAuth.getInstance();
                    String text = holder.textViewInnerSwitchboardSB1.getText().toString();
                    String text2 = holder.textViewInnerSwitchboardmain.getText().toString();


                                            DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3);
                                            itemsRef2.addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                    String s3 = dataSnapshot.getKey();
                                                    String s6=dataSnapshot.child("category").getValue(String.class);
                                                    String s7=dataSnapshot.child("name").getValue(String.class);

                                                    if (s3.equals(text)&&s6.equals("AC")) {
                                                        String s5 = dataSnapshot.child("Temperature").getValue(String.class);
                                                      //  Toast.makeText(context, s5, Toast.LENGTH_SHORT).show();
                                                        acvalue.setText(s5);

                                                        temp=Integer.parseInt(s5);
                                                        acupbutton.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                ++temp;
                                                                String l = String.valueOf(temp);
                                                                acvalue.setText(l);
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                                                                db.child("Temperature").setValue(l).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {

                                                                        // Toast.makeText(context, "Speed Changed", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });




                                                            }
                                                        });

                                                        acdownbutton.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                --temp;
                                                                String l = String.valueOf(temp);
                                                                acvalue.setText(l);
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                                                                db.child("Temperature").setValue(l).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        // Toast.makeText(context, "Speed Changed", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });

                                                            }
                                                        });


                                                    }


                                                }

                                                @Override
                                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                }

                                                @Override
                                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                                }

                                                @Override
                                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            ////////////////////////////////////////










                }

            }
        });

        //favourite button
        holder.imageViewInnerSwitchboardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                s3=holder.textViewInnerSwitchboardSB1.getText().toString();
               /* mAuth = FirebaseAuth.getInstance();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                          num2 = dataSnapshot.child("mode").getValue(String.class);
                                                          //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();
                                                      }

                                                      @Override
                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                      }
                });
*/
                mAuth = FirebaseAuth.getInstance();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String maincheck =dataSnapshot.child("Favorite").getValue(String.class);

                        if(maincheck.equals("false")) {
                            db.child("Favorite").setValue("true");
                            String modecheck=dataSnapshot.child("mode").getValue(String.class);
                            String catselect=dataSnapshot.child("category").getValue(String.class);
                            x = dataSnapshot.child("name").getValue(String.class);
                            HashMap<String, Object> values = new HashMap<>();
                            values.put("name", x);
                            values.put("mode", modecheck);
                            values.put("category", catselect);
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(Roomname).child(text3).child(s3).setValue(values).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Handler handle = new Handler() {
                                        @Override
                                        public void handleMessage(Message msg) {
                                            super.handleMessage(msg);
                                            progressDoalog.incrementProgressBy(5);
                                        }
                                    };
                                    progressDoalog = new ProgressDialog(context);
                                    progressDoalog.setMax(100);
                                    progressDoalog.setTitle("Favorite is Adding" +
                                            "" +
                                            "" +
                                            "");
                                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                    progressDoalog.show();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                while (progressDoalog.getProgress() <= progressDoalog
                                                        .getMax()) {
                                                    Thread.sleep(50);
                                                    handle.sendMessage(handle.obtainMessage());
                                                    if (progressDoalog.getProgress() == progressDoalog
                                                            .getMax()) {
                                                        progressDoalog.dismiss();
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();

                                    holder.imageViewInnerSwitchboardInfo.setImageResource(R.drawable.favoriteadded);

                                }
                            });
                        }
                        else{
                            db.child("Favorite").setValue("false");
                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(Roomname).child(text3).child(s3);
                            db2.removeValue();
                            holder.imageViewInnerSwitchboardInfo.setImageResource(R.drawable.favoriteselect);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        //Edit button
        holder.SwitchnameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailoguebox_predefine, null);
                //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);

                //retriving data from edittext
                String currentname= holder.textViewInnerSwitchboardmain.getText().toString();
                editTextdailogpredefine.setHint(currentname);//hint

                bottomSheetDialog.show();
                editTextdailogpredefine.addTextChangedListener(new TextWatcher()
                {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after)
                    {
                        if (s.length() == 15)
                        {
                            // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                            Toast.makeText(context, "Character Limit Reached ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                canceldailogpredefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });
                buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Namechange= editTextdailogpredefine.getText().toString();

                        //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                        if(TextUtils.isEmpty(Namechange)){
                            Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            s3=holder.textViewInnerSwitchboardSB1.getText().toString();
                            mAuth = FirebaseAuth.getInstance();
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(Roomname).child(text3).child(s3);

                            db.child("name").setValue(Namechange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.textViewInnerSwitchboardmain.setText(Namechange);
                                    bottomSheetDialog.cancel();
                                }
                            });
                            db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        db2.child("name").setValue(Namechange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    }
                });

            }
        });

        ////Info button
        holder.imageViewInnerSwitchboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s3=holder.textViewInnerSwitchboardSB1.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(Roomname).child(text3).child(s3);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        num1 = dataSnapshot.child("mode").getValue(String.class);
                      //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                        if(num1.equals("on")&&holder.innerSwitchboardbutton1.getDrawable().getConstantState()== holder.innerSwitchboardbutton1.getResources().getDrawable( R.drawable.ic_idea).getConstantState())
                        {
                            imageViewBrownJacket.setVisibility(View.VISIBLE);
                            imageViewBulb.setImageResource(R.drawable.bulboff);
                            imageViewBulb.setVisibility(View.VISIBLE);
                            imageViewAppliances.setVisibility(View.INVISIBLE);
                            customdial.setVisibility(View.INVISIBLE);
                            textviewdialview.setVisibility(View.INVISIBLE);
                            imageViewWhiteJacket.setVisibility(View.INVISIBLE);
                            speedcontrol.setVisibility(View.INVISIBLE);
                            imageViewMain.setVisibility(View.INVISIBLE);
                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerred);
                                }
                            });
                            db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                        else if(num1.equals("off")&&holder.innerSwitchboardbutton1.getDrawable().getConstantState()== holder.innerSwitchboardbutton1.getResources().getDrawable( R.drawable.ic_idea).getConstantState())
                        {
                            imageViewBrownJacket.setVisibility(View.VISIBLE);
                            imageViewBulb.setVisibility(View.VISIBLE);
                            imageViewBulb.setImageResource(R.drawable.bulbon);
                            imageViewAppliances.setVisibility(View.INVISIBLE);
                            customdial.setVisibility(View.INVISIBLE);
                            textviewdialview.setVisibility(View.INVISIBLE);
                            imageViewWhiteJacket.setVisibility(View.INVISIBLE);
                            speedcontrol.setVisibility(View.INVISIBLE);
                            imageViewMain.setVisibility(View.INVISIBLE);
                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powergreen);
                                }
                            });
                            db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                      else  if(num1.equals("on"))
                        {
                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                  //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerred);
                                }
                            });
                            db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }
                        else
                        {
                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powergreen);
                                }
                            });
                            db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });


    }


    @Override
    public int getItemCount() {
        return LightName.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ImageView imageViewInnerSwitchboard1,imageViewInnerSwitchboardInfo,SwitchnameChange;
        EditText textViewInnerSwitchboardSB1,textViewInnerSwitchboardmain,textViewInnerSwitchboardcategory;
        ImageButton innerSwitchboardbutton1;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's


            imageViewInnerSwitchboard1=(ImageView)itemView.findViewById(R.id.imageViewInnerSwitchboard1);
            imageViewInnerSwitchboardInfo=(ImageView)itemView.findViewById(R.id.imageViewInnerSwitchboardInfo);
            textViewInnerSwitchboardSB1=(EditText)itemView.findViewById(R.id.textViewInnerSwitchboardSB1);
            textViewInnerSwitchboardmain=(EditText)itemView.findViewById(R.id.textViewInnerSwitchboardmain);
            textViewInnerSwitchboardcategory=(EditText)itemView.findViewById(R.id.textViewInnerSwitchboardcategory);
            innerSwitchboardbutton1=(ImageButton)itemView.findViewById(R.id.innerSwitchboardbutton1);
            SwitchnameChange=(ImageView)itemView.findViewById(R.id.switchnamechange);


        }
    }


}
