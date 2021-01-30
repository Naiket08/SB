package com.example.sb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sb.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentRoomsSecond  extends DialogFragment {
    Button bedroom,bathroom,kitchen,dinningroom,custom;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_rooms_second,container,false);


        bedroom=(Button)v.findViewById(R.id.bedroom);
        bathroom=(Button)v.findViewById(R.id.bathroom);
        kitchen=(Button)v.findViewById(R.id.kitchen);
        dinningroom=(Button)v.findViewById(R.id.dinningroom);
        custom=(Button)v.findViewById(R.id.custom);

        mAuth = FirebaseAuth.getInstance();

        //BottomSheet Dialog Box Declaration
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.addingroombox,null);
        EditText edittextab=(EditText)parentView.findViewById(R.id.editTextab);
        Button nextbutton=(Button)parentView.findViewById(R.id.buttonab);
        ImageView cancelab=(ImageView)parentView.findViewById(R.id.cancelab);
        bottomSheetDialog.setContentView(parentView);

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();

                cancelab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });

                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
                        String roomname = edittextab.getText().toString().trim();

                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            db.child(roomname).child("Room Type").setValue("Bedroom");
                            Fragment newFragment = new FragmentSwitchboard();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            bottomSheetDialog.cancel();
                        }
                    }
                });
            }
        });
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();

                cancelab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });

                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
                        String roomname = edittextab.getText().toString().trim();

                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            db.child(roomname).child("Room Type").setValue("Bathroom");
                            Fragment newFragment = new FragmentSwitchboard();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            bottomSheetDialog.cancel();
                        }
                    }
                });
            }
        });
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();

                cancelab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });

                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
                        String roomname = edittextab.getText().toString().trim();

                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            db.child(roomname).child("Room Type").setValue("Kitchen");
                            Fragment newFragment = new FragmentSwitchboard();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            bottomSheetDialog.cancel();
                        }
                    }
                });
            }
        });
        dinningroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();

                cancelab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });

                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
                        String roomname = edittextab.getText().toString().trim();

                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            db.child(roomname).child("Room Type").setValue("Dinning Room");
                            Fragment newFragment = new FragmentSwitchboard();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            bottomSheetDialog.cancel();
                        }
                    }
                });
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
}
