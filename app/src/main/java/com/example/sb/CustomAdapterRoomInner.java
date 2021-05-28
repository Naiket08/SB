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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class CustomAdapterRoomInner extends RecyclerView.Adapter<CustomAdapterRoomInner.ViewHolder> {
    ArrayList SwitchName,SwitchName2;
    ArrayList SwitchType;
    String Roomname1,s3,text3,s4,s5,s6,s2;
    private FirebaseAuth mAuth;
    Context context;
    public CustomAdapterRoomInner(Context context, ArrayList SwitchName,ArrayList SwitchName2, ArrayList SwitchType,FirebaseAuth mAuth,String Roomname1,String text3) {


        this.context = context;
        this.SwitchName = SwitchName;
        this.SwitchName2 = SwitchName2;
        this.SwitchType = SwitchType;
        this.Roomname1=Roomname1;
        this.text3=text3;
        this.mAuth=mAuth;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_roominner, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewRoomInnerSB1.setText((CharSequence) SwitchName.get(position));
        holder.impmain.setText((CharSequence) SwitchName2.get(position));
        holder.textViewRoomInnerSBType.setText((CharSequence) SwitchType.get(position));
        /////////
        holder.imageViewRoomInnerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s4=holder.textViewRoomInnerSBType.getText().toString();
                s5=holder.textViewRoomInnerSB1.getText().toString();
                //Toast.makeText(context,s4, Toast.LENGTH_SHORT).show();
                switchboardinfo();




            }
        });
        holder.imageViewRoomInnerEditSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
                s3=holder.textViewRoomInnerSB1.getText().toString();
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
                        holder.textViewRoomInnerSB1.setEnabled(true);
                        s2 = editTextdailogpredefine.getText().toString();
                        holder.textViewRoomInnerSB1.setText(s2);
                        s3=s2;
                        holder.textViewRoomInnerSB1.setEnabled(false);
                        if(TextUtils.isEmpty(s2)){
                            Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Toast.makeText(context,Roomname1, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(context,s3, Toast.LENGTH_SHORT).show();
                            //  write code here
                            s6 = holder.impmain.getText().toString();

                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname1).child(s6).child("type").setValue(s2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                public void onSuccess(Void aVoid) {
                                }
                            });
                            bottomSheetDialog.cancel();
                        }
                    }
                });
            }
        });

        holder.imageViewdeleteroominner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deletes1=holder.impmain.getText().toString();
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete This?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseReference dbmain = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname1).child(deletes1);
                                DatabaseReference dbmain2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(Roomname1).child(deletes1);
                                dbmain2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){
                                            dbmain2.removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                dbmain.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        SwitchName.remove(position);
                                        SwitchName2.remove(position);
                                        SwitchType.remove(position);
                                        notifyDataSetChanged();

                                    }
                                });

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });

        holder.clroominner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ///////////IMP
                s6 = holder.impmain.getText().toString();
                ///////////////IMP
                Fragment newFragment = new FragmentInnerSwitchBoard();
                Bundle arguments = new Bundle();
                s3=holder.textViewRoomInnerSB1.getText().toString();
                arguments.putString( "Roomname",Roomname1);
                arguments.putString( "Switchname",s6);
                arguments.putString( "Switchnamemain",s3);
                newFragment.setArguments(arguments);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container,newFragment).addToBackStack(null)
                        .commit();
            }
        });

        //////////////////////Repeated
        holder.roominnerbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////IMP
                s6 = holder.impmain.getText().toString();
                ///////////////IMP
                Fragment newFragment = new FragmentInnerSwitchBoard();
                Bundle arguments = new Bundle();
                s3=holder.textViewRoomInnerSB1.getText().toString();
                arguments.putString( "Roomname",Roomname1);
                arguments.putString( "Switchname",s6);
                arguments.putString( "Switchnamemain",s3);
                newFragment.setArguments(arguments);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container,newFragment).addToBackStack(null)
                        .commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return SwitchName.size();
    }
    public void switchboardinfo() {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View parentView = inflater.inflate(R.layout.switchboardinfo, null);
        ImageView cancelab = (ImageView) parentView.findViewById(R.id.cancelswitchboardinfo);
        TextView  info1 =(TextView)parentView.findViewById(R.id.textviewswitchboardinfo);
        TextView  info2 =(TextView)parentView.findViewById(R.id.textviewswitchboardinfo2);
        TextView  info3 =(TextView)parentView.findViewById(R.id.ideatextswitchboardinfo);
        TextView  info4 =(TextView)parentView.findViewById(R.id.ideatextswitchboardinfo2);
        if(s4.equals("4 Lights and 1 Fan")) {

            info1.setText(s5);
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("4 Lights");
            info4.setText("1 Fan");

        }
        else if(s4.equals("4 Lights and 2 Fan"))
        {
            info1.setText(s5);
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("4 Lights");
            info4.setText("2 Fans");


        }
        else if(s4.equals("2 Lights and 1 Fan"))
        {

            info1.setText(s5);
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText(" 2 Lights");
            info4.setText(" 1 Fan");

        }
        else if(s4.equals("custom"))
        {

            info1.setText(s5);
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("  Lights");
            info4.setText("  Fan");
        }

        bottomSheetDialog.setContentView(parentView);
        bottomSheetDialog.show();

        cancelab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ConstraintLayout clroominner;
       ImageView imageViewRoomInnerInfo,imageViewRoomInnerEditSB,imageViewdeleteroominner;
       TextView textViewRoomInnerSBType,impmain;
        EditText textViewRoomInnerSB1;
       ImageButton roominnerbutton1;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            imageViewRoomInnerInfo=(ImageView)itemView.findViewById(R.id.imageViewRoomInnerInfo);
            imageViewRoomInnerEditSB=(ImageView)itemView.findViewById(R.id.imageViewRoomInnerEditSB);
            imageViewdeleteroominner=(ImageView)itemView.findViewById(R.id.imageViewdeleteroominner);
            textViewRoomInnerSB1=(EditText) itemView.findViewById(R.id.textViewRoomInnerSB1);
            textViewRoomInnerSBType=(TextView)itemView.findViewById(R.id.textViewRoomInnerSBType);
            roominnerbutton1=(ImageButton)itemView.findViewById(R.id.roominnerbutton1);
            impmain=(TextView)itemView.findViewById(R.id.impmain);
            clroominner=(ConstraintLayout)itemView.findViewById(R.id.clroominner);


        }
    }
}
