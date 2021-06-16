package com.example.sb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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

import java.util.ArrayList;

public class CustomAdapterMyrooms extends RecyclerView.Adapter<CustomAdapterMyrooms.ViewHolder> {
    FirebaseAuth mAuth;
    ArrayList roomNames;
    ArrayList roomNames2;
    ArrayList roomImages;
    Context context;
    public String s2,s3;
    EditText editTextdailogpredefine;
    public ArrayList<String> list = new ArrayList<String>();
    public CustomAdapterMyrooms(Context context, ArrayList roomNames, ArrayList roomNames2, ArrayList roomImages, FirebaseAuth mAuth) {


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
        dbchangemyroom();
        holder.name.setText(String.valueOf(roomNames.get(position)));
        holder.textviewrefence.setText(String.valueOf(roomNames2.get(position)));

        holder.imageViewIconMyRoom.setImageResource((Integer)roomImages.get(position));
        // implement setOnClickListener event on item view.
        holder.imageViewRecycleViewMyRoomEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smain= holder.textviewrefence.getText().toString().trim();
                //Toast.makeText(context, smain, Toast.LENGTH_SHORT).show();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailoguebox_predefine, null);
                //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
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
        //To delete
        holder.imageViewRecycleViewMyRoomdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites");
                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete This?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String mainname= holder.textviewrefence.getText().toString().trim();
                                        if(list.contains(mainname)) {
                                            db.child(mainname).removeValue();
                                            db2.child(mainname).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    roomNames.remove(position);
                                                    roomNames2.remove(position);
                                                    roomImages.remove(position);
                                                    notifyDataSetChanged();
                                                }
                                            });
                                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                                        }
                                        else
                                        {
                                            db2.child(mainname).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    roomNames.remove(position);
                                                    roomNames2.remove(position);
                                                    roomImages.remove(position);
                                                    notifyDataSetChanged();
                                                }
                                            });

                                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        }


                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
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


    public void dbchangemyroom(){
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites");
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String s1 = dataSnapshot.getKey();
                // Toast.makeText(getContext(),s1, Toast.LENGTH_SHORT).show();
                if(s1.equals("number")||s1.equals("roomtype")||s1.equals("")||s1.equals("name")) {

                }
                else
                {
                    list.add(s1);
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
    }
    public int getItemCount() {
        return roomNames.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ConstraintLayout clmyroom;
        EditText name;
        TextView textviewrefence;
        ImageView imageViewRecycleViewMyRoomEdit,imageViewIconMyRoom,imageViewRecycleViewMyRoomdelete;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (EditText) itemView.findViewById(R.id.EdittextRecycleViewMyRoom);
            imageViewRecycleViewMyRoomEdit = (ImageView)itemView.findViewById(R.id.imageViewRecycleViewMyRoomEdit);
            imageViewIconMyRoom = (ImageView)itemView.findViewById(R.id. imageViewIconMyRoom);
            textviewrefence=(TextView)itemView.findViewById(R.id.Textviewrefence);
            imageViewRecycleViewMyRoomdelete=(ImageView)itemView.findViewById(R.id.imageViewRecycleViewMyRoomdelete);
            clmyroom=(ConstraintLayout)itemView.findViewById(R.id.clmyroom);
        }
    }
}
