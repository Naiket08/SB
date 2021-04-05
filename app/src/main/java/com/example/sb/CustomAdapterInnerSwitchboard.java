package com.example.sb;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
    ArrayList LightName;
    ArrayList LightType,LightType2;
    ImageView imageViewBrownJacket,imageViewWhiteJacket,imageViewAppliances,imageViewBulb;
    Button speedcontrol;
    DialView customdial;
    public String Roomname,s3,text3,num1,num2,x,fanspeed;
    private FirebaseAuth mAuth;
    Context context;


    public CustomAdapterInnerSwitchboard(Context context, ArrayList LightName, ArrayList LightType,ArrayList LightType2,FirebaseAuth mAuth,String Roomname,String text3,ImageView imageViewBrownJacket,ImageView  imageViewWhiteJacket,ImageView imageViewAppliances,ImageView imageViewBulb,DialView customdial,Button speedcontrol) {


        this.context = context;
        this.LightName = LightName;
        this.LightType = LightType;
        this.LightType2=LightType2;
        this.Roomname=Roomname;
        this.imageViewBrownJacket=imageViewBrownJacket;
        this.imageViewWhiteJacket=imageViewWhiteJacket;
        this.imageViewAppliances=imageViewAppliances;
        this.imageViewBulb=imageViewBulb;
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

        holder.innerSwitchboardbutton1.setImageResource((Integer)LightType.get(position));
        holder.imageViewInnerSwitchboard1.setImageResource((Integer)LightType2.get(position));
        //icon display
        holder.innerSwitchboardbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num2=holder.textViewInnerSwitchboardSB1.getText().toString();
                Toast.makeText(context, num2+" : Selected", Toast.LENGTH_SHORT).show();

                if(num2.contains("Fan")){
                   // Toast.makeText(context,"enter inside", Toast.LENGTH_SHORT).show();
                    imageViewBrownJacket.setVisibility(View.INVISIBLE);
                    imageViewBulb.setVisibility(View.INVISIBLE);
                    imageViewAppliances.setVisibility(View.INVISIBLE);
                    customdial.setVisibility(View.VISIBLE);
                    imageViewWhiteJacket.setVisibility(View.VISIBLE);
                    speedcontrol.setVisibility(View.VISIBLE);


                    speedcontrol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fanspeed = customdial.fancontroller;
                          //  Toast.makeText(context, fanspeed, Toast.LENGTH_SHORT).show();

                            mAuth = FirebaseAuth.getInstance();
                            String text = holder.textViewInnerSwitchboardSB1.getText().toString();
                            ////////////////////////////////////////////////////////////////////////////////////
                            DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
                            itemsRef.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    String s1 = dataSnapshot.getKey();

                                    //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                                    ////////////////////////               22222222222222222222222222222222222


                                    //////////////////////
                                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
                                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            // Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                            //This one

                                            /////////////////////////////////////////////
                                            DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
                                            itemsRef2.addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                    String s2 = dataSnapshot.getKey();

                                                    //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                                    //////////////////////// 333333333333333333333333333333333333333333
                                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2);
                                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                                        @Override
                                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                            String s3 = dataSnapshot.getKey();

                                                            String s5 = dataSnapshot.child("speed").getValue(String.class);
                                                            //String s6 = dataSnapshot.child("category").getValue(String.class);
                                                            //  Toast.makeText(context, s5, Toast.LENGTH_SHORT).show();

                                                            if (s3.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                db.child("speed").setValue(fanspeed).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Toast.makeText(context, "Speed Changed", Toast.LENGTH_SHORT).show();
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


///////////////////
// ////////////////////////////////////
                                            ////////////////////////////////////////////

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }//First ending

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


                            ////////////////////////////////////////////////////////////////////////////////////
                        }
                    });


                }

                else if(num2.contains("Light"))
                {
                    imageViewBrownJacket.setVisibility(View.VISIBLE);
                    imageViewBulb.setVisibility(View.VISIBLE);
                    imageViewAppliances.setVisibility(View.INVISIBLE);
                    customdial.setVisibility(View.INVISIBLE);
                    imageViewWhiteJacket.setVisibility(View.INVISIBLE);
                    speedcontrol.setVisibility(View.INVISIBLE);
                }

            }
        });

        //favourite button
        holder.imageViewInnerSwitchboardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
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
                         x = dataSnapshot.child("name").getValue(String.class);
                        HashMap<String,Object> values = new HashMap<>();
                        values.put("name",x);
                        values.put("mode","on");
                        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(Roomname).child(text3).child(s3).setValue(values).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "favorite added", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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

                        if(num1.equals("on"))
                        {
                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                  //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttonred);
                                }
                            });
                            db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                }
                            });



                        }
                        else
                        {
                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //  Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                    holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttongreen);
                                }
                            });
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
        });


    }


    @Override
    public int getItemCount() {
        return LightName.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ImageView imageViewInnerSwitchboard1,imageViewInnerSwitchboardInfo;
        EditText textViewInnerSwitchboardSB1;
        ImageButton innerSwitchboardbutton1;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's


            imageViewInnerSwitchboard1=(ImageView)itemView.findViewById(R.id.imageViewInnerSwitchboard1);
            imageViewInnerSwitchboardInfo=(ImageView)itemView.findViewById(R.id.imageViewInnerSwitchboardInfo);
            textViewInnerSwitchboardSB1=(EditText)itemView.findViewById(R.id.textViewInnerSwitchboardSB1);
            innerSwitchboardbutton1=(ImageButton)itemView.findViewById(R.id.innerSwitchboardbutton1);




        }
    }


}
