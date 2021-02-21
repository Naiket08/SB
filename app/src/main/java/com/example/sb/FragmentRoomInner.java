package com.example.sb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FragmentRoomInner extends Fragment{

    private static final String TAG =  null;
    ImageView imageViewRoomInnerLights,imageViewRoomInnerEditSB,imageViewRoomInnerInfo,imageViewRoomInnerAddSB;
    TextView textViewControlSB,textViewRoomInnerRoomNo,textViewRoomInnerSB1,textViewRoomInnerSBType;
    ////////////////////////////////////
    RecyclerView recyclerViewRoominner;
    ////////////////////////
    public ArrayList<String> SwitchName = new ArrayList<String>();
    public ArrayList<String> SwitchType = new ArrayList<String>();

    //////////////////////
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    public int i=0,k=2;
    public String Roomname1,text3;
    public String innerd1,innerd2;
    public int o,j;
    ///////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_inner,container,false);

///////////////////////////////////////////////////////////////////
        Roomname1 = getArguments().getString("Roomname");
      //  Toast.makeText(getContext(),"Roomname " + Roomname1, Toast.LENGTH_SHORT).show();
        text3 = getArguments().getString("Switchname");

/////////////////////////////////////////////////////////////////////////
        imageViewRoomInnerInfo = (ImageView)view.findViewById(R.id.imageViewRoomInnerInfo);
        imageViewRoomInnerLights = (ImageView)view.findViewById(R.id.imageViewRoomInnerLights);

        textViewControlSB = (TextView)view.findViewById(R.id.textViewControlSB);
        textViewRoomInnerRoomNo = (TextView)view.findViewById(R.id.textViewRoomInnerRoomNo);
        textViewRoomInnerSB1 = (TextView)view.findViewById(R.id.textViewRoomInnerSB1);
        textViewRoomInnerSBType = (TextView)view.findViewById(R.id.textViewRoomInnerSBType);
        imageViewRoomInnerAddSB=(ImageView)view.findViewById(R.id.imageViewRoomInnerAddSB);
        if((SwitchName!=null&&SwitchType!=null&&SwitchName.size()>0&&SwitchType.size()>0)){
            SwitchName.clear();
            SwitchType.clear();
        }


        //////////////////////////////////////////////////
        //firecloud
        //////////////////////////////////////////////////
        //firecloud
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        CustomAdapterRoomInner customAdapter1 = new CustomAdapterRoomInner(getActivity(),SwitchName,SwitchType,mAuth,Roomname1);
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname1);
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String s1 = dataSnapshot.getKey();
                if(s1.equals("number")||s1.equals("roomtype")||s1.equals("")) {

                }
                else

                {
                   // Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                    SwitchName.add(s1);
                    String s2 = dataSnapshot.child("combination").getValue(String.class);
                 //  Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
               if(s2.equals("4*1")){
                    SwitchType.add("4 Lights and 1 Fan");
                    //Toast.makeText(getContext(),"Entered inside", Toast.LENGTH_SHORT).show();
                }
                else  if(s2.equals("4*2")){
                    SwitchType.add("4 Lights and 2 Fan");
                }
                else  if(s2.equals("2*1")){
                    SwitchType.add("2 Lights and 1 Fan");
                }
                else  if(s2.equals("custom")){
                    SwitchType.add("Custom");
                }
                    //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                    customAdapter1.notifyDataSetChanged();
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


       /* db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String z1 = documentSnapshot.getString("Renumbers");
                        innerd1=z1;
                        o=Integer.valueOf(innerd1);
                        Toast.makeText(getContext(),"value1 : "+ o , Toast.LENGTH_SHORT).show();
                    }
                });*/


                ////////////////////////////////
               /* db1.collection("users").document(mAuth.getCurrentUser().getUid()).collection("Rooms").document(""+o).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String z2 = documentSnapshot.getString("switchcounter");
                                Toast.makeText(getContext(),"value2 : "+ z2, Toast.LENGTH_SHORT).show();
                            }
                        });*/
                ////////////////////////////////
                //static values fior testing




        /*Handler handler3 = new Handler();
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                db1.collection("users").document(userId).collection("Rooms").document("" + 2).collection("Switch"+k).document("switch").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Toast.makeText(getContext(),"entered", Toast.LENGTH_SHORT).show();
                                String txt = documentSnapshot.getString( "Switchname"+k);
                                SwitchName.add(txt);
                                Toast.makeText(getContext(),txt, Toast.LENGTH_SHORT).show();
                                String ig = documentSnapshot.getString("type");
                                Stype = ig;
                                SwitchType.add("4 Lights and 1 Fan");

                                *//*if (Stype.equals("1")) {
                                    SwitchType.add("4 Lights and 1 Fan");
                                } else if (Stype.equals("2")) {
                                    SwitchType.add("4 Lights and 2 Fan");
                                } else if (Stype.equals("3")) {
                                    SwitchType.add("2 Lights and 1 Fan");
                                } else if (Stype.equals("4")) {
                                    SwitchType.add("Custom");
                                }*//*

                            }
                        });

            }
        };
        handler3.postDelayed(runnable3, 500);
*/


        //////////////////////////////////////////////////////
        imageViewRoomInnerAddSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(),"Clicked", Toast.LENGTH_SHORT).show();
                Fragment newFragment = new FragmentSwitchboard();
                Bundle arguments = new Bundle();
                arguments.putString( "Roomname",Roomname1);
                newFragment.setArguments(arguments);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });





       /* Handler handler1 = new Handler();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {*/


                //Second fragment after 5 seconds appears
                recyclerViewRoominner = (RecyclerView)view.findViewById(R.id.recyclerViewRoomInner);
                recyclerViewRoominner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                recyclerViewRoominner.setAdapter(customAdapter1); // set the Adapter to RecyclerView
                getFragmentManager().beginTransaction().commit();
         /*   }
        };
        handler1.postDelayed(runnable1, 50);
*/


        return view;
    }
}
