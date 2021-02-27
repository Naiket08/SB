package com.example.sb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapterGeneral extends RecyclerView.Adapter<CustomAdapterGeneral.ViewHolder> {
    ArrayList Generalanme,GeneralType;
    String Roomname,s3,text3,num1,num2;
    private FirebaseAuth mAuth;
    Context context;


    public CustomAdapterGeneral(Context context, ArrayList Generalname,ArrayList GeneralType,FirebaseAuth mAuth) {


        this.context = context;
        this.Generalanme =Generalname;
        this.GeneralType=GeneralType;
        this.mAuth=mAuth;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_general, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Edittextgeneral1.setText(String.valueOf(Generalanme.get(position)));
        holder.buttongeneral1.setImageResource((Integer)GeneralType.get(position));


        holder.imageViewGeneral1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = holder.Edittextgeneral1.getText().toString();
                ////////////////////////////////////////////////////////////////////////////////////
                DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites");
                itemsRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String s1 = dataSnapshot.getKey();

                        //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                        ////////////////////////               22222222222222222222222222222222222


                        //////////////////////
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1);
                        db.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                // Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                //This one

                                    /////////////////////////////////////////////
                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1);
                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            String s2 = dataSnapshot.getKey();

                                                //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                                //////////////////////// 333333333333333333333333333333333333333333
                                                DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2);
                                                itemsRef2.addChildEventListener(new ChildEventListener() {
                                                    @Override
                                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                        String s3 = dataSnapshot.getKey();

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            //String s6 = dataSnapshot.child("category").getValue(String.class);
                                                      //  Toast.makeText(context, s5, Toast.LENGTH_SHORT).show();

                                                        if(s5.equals(text)) {
                                                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                            db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                    num2 = dataSnapshot.child("mode").getValue(String.class);
                                                                    //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                                                    if (num2.equals("on")) {
                                                                        db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
                                                                                //holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttonred);
                                                                            }
                                                                        });
                                                                        db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
                                                                                //holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttonred);
                                                                            }
                                                                        });


                                                                    } else {
                                                                        db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                //holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttongreen);
                                                                            }
                                                                        });
                                                                        db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                //holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttongreen);
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


    @Override
    public int getItemCount() {
        return Generalanme.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
       ImageView imageViewGeneral1;
       ImageButton  buttongeneral1;
       EditText Edittextgeneral1;


        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's



            imageViewGeneral1=(ImageView)itemView.findViewById(R.id.imageViewGeneral1);
            buttongeneral1=(ImageButton) itemView.findViewById(R.id.buttongeneral1);
            Edittextgeneral1=(EditText)itemView.findViewById(R.id.Edittextgeneral1);




        }
    }
}

