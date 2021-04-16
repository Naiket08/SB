package com.example.sb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    FirebaseAuth mAuth;
    ArrayList roomNames;
    ArrayList roomNames2;
    ArrayList roomImages;
    Context context;
    public String s2,s3;
    EditText editTextdailogpredefine;
    public CustomAdapter(Context context, ArrayList roomNames,ArrayList roomNames2, ArrayList roomImages,FirebaseAuth mAuth) {


        this.context = context;
        this.roomNames = roomNames;
        this.roomNames2 = roomNames2;
        this.roomImages = roomImages;
        this.mAuth=mAuth;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_layout_my_rooms, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        holder.name.setText(String.valueOf(roomNames.get(position)));
        holder.textviewrefence.setText(String.valueOf(roomNames2.get(position)));

        holder.imageViewIconMyRoom.setImageResource((Integer)roomImages.get(position));
        // implement setOnClickListener event on item view.
        holder.imageViewRecycleViewMyRoomEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smain= holder.textviewrefence.getText().toString().trim();
                Toast.makeText(context, smain, Toast.LENGTH_SHORT).show();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailogue_predefine, null);
                //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
                bottomSheetDialog.show();

                canceldailogpredefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });

                buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.name.setEnabled(true);
                        s2 = editTextdailogpredefine.getText().toString();

                        if(TextUtils.isEmpty(s2)){
                             Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            holder.name.setText(s2);
                            // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(smain);
                            db.child("name").setValue(s2);
                            holder.name.setEnabled(false);
                            bottomSheetDialog.cancel();
                        }



                    }
                });

            }
        });
        holder.imageViewIconMyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s3=holder.textviewrefence.getText().toString();
                Fragment newFragment = new FragmentRoomInner();
                Bundle arguments = new Bundle();
                arguments.putString( "Roomname",s3);
                newFragment.setArguments(arguments);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container,newFragment).addToBackStack(null)
                        .commit();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }




    /*public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.name.setText((CharSequence) roomNames.get(position));
        holder.imageViewIconMyRoom.setImageResource((Integer)roomImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               *//* // open another activity on item click
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("image", personImages.get(position)); // put image data in Intent
                context.startActivity(intent); // start Intent*//*
            }
        });

    }*/



    public int getItemCount() {
        return roomNames.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        EditText name;
        TextView textviewrefence;
        ImageView imageViewRecycleViewMyRoomEdit,imageViewIconMyRoom;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (EditText) itemView.findViewById(R.id.EdittextRecycleViewMyRoom);
            imageViewRecycleViewMyRoomEdit = (ImageView)itemView.findViewById(R.id.imageViewRecycleViewMyRoomEdit);
            imageViewIconMyRoom = (ImageView)itemView.findViewById(R.id. imageViewIconMyRoom);
            textviewrefence=(TextView)itemView.findViewById(R.id.Textviewrefence);
        }
    }
}
