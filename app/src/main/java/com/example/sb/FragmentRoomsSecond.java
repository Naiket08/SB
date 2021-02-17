package com.example.sb;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentRoomsSecond  extends DialogFragment {
    Button bedroom,bathroom,kitchen,dinningroom,custom;

    private FirebaseAuth mAuth;
    SharedPreferences roompref;
//cloudbase

    private FirebaseFirestore db = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    private String k;

    ///
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
                ///////////////////////////////////////////////////////
                userId = mAuth.getCurrentUser().getUid();
               /* DocumentReference documentReference = db1.collection("users").document(userId);

                db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String zz = documentSnapshot.getString("Renumbers");
                                i=Integer.parseInt(zz);
                            }
                        });*/
////////////////////////////////////////////////////////////////////////////////

                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //cloud
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid())
                                .child("Rooms").child("Bedroom");
                        String roomname = edittextab.getText().toString().trim();

                        /////
                        Map<String, Object> user = new HashMap<>();
                        user.put("roomtype","Bedroom");
                        user.put("number","1");
                        /////

                        roompref = getContext().getSharedPreferences("roomPreference", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = roompref.edit();
                        editor.putString("RoomName",roomname);
                        editor.commit();

                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(roomname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("User Details").child("Roomfragment").setValue("false").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });

                            Fragment newFragment = new FragmentSwitchboard();
                            Bundle arguments = new Bundle();
                            arguments.putString( "Roomname",roomname);
                            newFragment.setArguments(arguments);
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
                ///////////////////////////////////////////////////////
               /* userId = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db1.collection("users").document(userId);

                db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String zz = documentSnapshot.getString("Renumbers");
                                i=Integer.parseInt(zz);
                            }
                        });*/
////////////////////////////////////////////////////////////////////////////////


                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid())
                                .child("Rooms").child("Bathroom");
                        String roomname = edittextab.getText().toString().trim();
                        /////
                        Map<String, Object> user = new HashMap<>();
                        user.put("roomtype","Bathroom");
                        user.put("number","1");

                        /////


                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{  FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(roomname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                            }
                        });
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("User Details").child("Roomfragment").setValue("false").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Fragment newFragment = new FragmentSwitchboard();
                            Bundle arguments = new Bundle();
                            arguments.putString( "Roomname",roomname);
                            newFragment.setArguments(arguments);
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
                ///////////////////////////////////////////////////////
                userId = mAuth.getCurrentUser().getUid();
               /* DocumentReference documentReference = db1.collection("users").document(userId);

                db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String zz = documentSnapshot.getString("Renumbers");
                                i=Integer.parseInt(zz);
                            }
                        });*/
////////////////////////////////////////////////////////////////////////////////


                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid())
                                .child("Rooms").child("Kitchen");
                        String roomname = edittextab.getText().toString().trim();
                        /////
                        Map<String, Object> user = new HashMap<>();
                        user.put("roomtype","Kitchen");
                        user.put("number","1");


                        /////


                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(roomname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("User Details").child("Roomfragment").setValue("false").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Fragment newFragment = new FragmentSwitchboard();
                            Bundle arguments = new Bundle();
                            arguments.putString( "Roomname",roomname);
                            newFragment.setArguments(arguments);
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
                ///////////////////////////////////////////////////////
                userId = mAuth.getCurrentUser().getUid();
               /* DocumentReference documentReference = db1.collection("users").document(userId);

                db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String zz = documentSnapshot.getString("Renumbers");
                                i=Integer.parseInt(zz);
                            }
                        });*/
////////////////////////////////////////////////////////////////////////////////

                nextbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid())
                                .child("Rooms").child("Dining Room");
                        String roomname = edittextab.getText().toString().trim();
                        /////
                        Map<String, Object> user = new HashMap<>();
                        user.put("roomtype","Dinning Room");
                        user.put("number","1");
                        /////


                        if(TextUtils.isEmpty(roomname)){
                            Toast.makeText(getContext(), "Enter Roomname", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(roomname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("User Details").child("Roomfragment").setValue("false").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Fragment newFragment = new FragmentSwitchboard();
                            Bundle arguments = new Bundle();
                            arguments.putString( "Roomname",roomname);
                            newFragment.setArguments(arguments);
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
