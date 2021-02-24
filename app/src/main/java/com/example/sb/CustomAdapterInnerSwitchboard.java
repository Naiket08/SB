package com.example.sb;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapterInnerSwitchboard extends RecyclerView.Adapter<CustomAdapterInnerSwitchboard.ViewHolder> {
    ArrayList LightName;
    ArrayList LightType;
    String Roomname,s3,text3,num1;
    private FirebaseAuth mAuth;
    Context context;
    public CustomAdapterInnerSwitchboard(Context context, ArrayList LightName, ArrayList LightType,FirebaseAuth mAuth,String Roomname,String text3) {


        this.context = context;
        this.LightName = LightName;
        this.LightType = LightType;
        this.Roomname=Roomname;
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
        holder.innerSwitchboardbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s3=holder.textViewInnerSwitchboardSB1.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3).child(s3);
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