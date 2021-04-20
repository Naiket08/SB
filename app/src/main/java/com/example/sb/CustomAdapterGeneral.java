package com.example.sb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.constraintlayout.widget.ConstraintLayout;
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
    ArrayList Generalname,GeneralRname,GeneralSname,GeneralSSname,GeneralType,GeneralType2;
    String Roomname,s3,text3,num1,num2;
    private FirebaseAuth mAuth;
    Context context;


    public CustomAdapterGeneral(Context context, ArrayList Generalname,ArrayList GeneralRname,ArrayList GeneralSname,ArrayList GeneralSSname,ArrayList GeneralType,ArrayList GeneralType2,FirebaseAuth mAuth) {


        this.context = context;
        this.Generalname =Generalname;
        this.GeneralRname =GeneralRname;
        this.GeneralSname =GeneralSname;
        this.GeneralSSname =GeneralSSname;
        this.GeneralType=GeneralType;
        this.GeneralType2=GeneralType2;
        this.mAuth=mAuth;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_general1, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Edittextgeneral1.setText(String.valueOf(Generalname.get(position)));
        holder.EdittextgeneralRname.setText(String.valueOf(GeneralRname.get(position)));
        holder.EdittextgeneralSname.setText(String.valueOf(GeneralSname.get(position)));
        holder.EdittextgeneralSother.setText(String.valueOf(GeneralSSname.get(position)));
        holder.buttongeneral1.setImageResource((Integer)GeneralType.get(position));
        holder.imageViewGeneral1.setImageResource((Integer)GeneralType2.get(position));



        holder.imageViewGeneral1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = holder.Edittextgeneral1.getText().toString();
                String textR = holder.EdittextgeneralRname.getText().toString();
                String textS = holder.EdittextgeneralSname.getText().toString();


                                                DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(textR).child(textS);
                                                itemsRef2.addChildEventListener(new ChildEventListener() {
                                                    @Override
                                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                        String s3 = dataSnapshot.getKey();

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            //String s6 = dataSnapshot.child("category").getValue(String.class);
                                                      //  Toast.makeText(context, s5, Toast.LENGTH_SHORT).show();

                                                        if(s5.equals(text)) {
                                                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(textR).child(textS).child(s3);
                                                            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(textR).child(textS).child(s3);
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
                                                                                holder.imageViewGeneral1.setImageResource(R.drawable.powerred);
                                                                            }
                                                                        });
                                                                        db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                //holder.imageViewInnerSwitchboard1.setImageResource(R.drawable.powerbuttonred);
                                                                            }
                                                                        });


                                                                    } else {
                                                                        db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                holder.imageViewGeneral1.setImageResource(R.drawable.powergreen);
                                                                            }
                                                                        });
                                                                        db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
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




///////////////////
// ////////////////////////////////////
                                ////////////////////////////////////////////






                ////////////////////////////////////////////////////////////////////////////////////
            }
        });


        holder.imageViewdeleteGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textR = holder.EdittextgeneralRname.getText().toString();
                String textS = holder.EdittextgeneralSname.getText().toString();
                String textSS = holder.EdittextgeneralSother.getText().toString();
                DatabaseReference db3 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(textR).child(textS);
                DatabaseReference db4 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(textR).child(textS);
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete This?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                              db4.child(textSS).child("Favorite").setValue("false");
                              db3.child(textSS).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                  @Override
                                  public void onSuccess(Void aVoid) {
                                      Generalname.remove(position);
                                      GeneralRname.remove(position);
                                      GeneralSname.remove(position);
                                      GeneralType.remove(position);
                                      GeneralType2.remove(position);
                                      notifyDataSetChanged();
                                  }
                              });
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });




    }


    @Override
    public int getItemCount() {
        return Generalname.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ConstraintLayout clgeneral;
       ImageView imageViewGeneral1,imageViewdeleteGeneral;
       ImageButton  buttongeneral1;
       EditText Edittextgeneral1,EdittextgeneralRname,EdittextgeneralSname,EdittextgeneralSother;


        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's



            clgeneral=(ConstraintLayout)itemView.findViewById(R.id.clgeneral);
            imageViewGeneral1=(ImageView)itemView.findViewById(R.id.imageViewGeneral1);
            imageViewdeleteGeneral=(ImageView)itemView.findViewById(R.id.imageViewdeleteGeneral);
            buttongeneral1=(ImageButton) itemView.findViewById(R.id.buttongeneral1);
            Edittextgeneral1=(EditText)itemView.findViewById(R.id.Edittextgeneral1);
            EdittextgeneralRname=(EditText)itemView.findViewById(R.id.EdittextgeneralRname);
            EdittextgeneralSname=(EditText)itemView.findViewById(R.id.EdittextgeneralSnmae);
            EdittextgeneralSother=(EditText)itemView.findViewById(R.id.EdittextgeneralSother);




        }
    }
}

