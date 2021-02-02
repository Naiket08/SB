package com.example.sb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentMyRoom extends Fragment {

    Button buttonAddRoomsMyRoom;
    TextView textViewTopText;
    RecyclerView recyclerViewMyRoom;
    private FirebaseAuth mAuth;
////////////////////////////////////////
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    public int i=0;
    public String rimg,rtext;
//////////////////////////////////////
    public ArrayList<String> roomNames = new ArrayList<String>();;
    public ArrayList<Integer> roomImages = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rooms,container,false);

        ArrayList dynamicroomname = new ArrayList<>();

        if((roomNames!=null&&roomImages!=null&&roomNames.size()>0&&roomImages.size()>0)){
            roomNames.clear();
            roomImages.clear();
        }

        //////////////////////////////////////////////////
        //firecloud
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        db1.collection("users").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String zz = documentSnapshot.getString("Renumbers");
                        i=Integer.parseInt(zz);
                    }
                });

        db1.collection("users").document(userId).collection("Rooms").document("RoomName").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        for(int j=0;j<i;j++) {
                            String txt = documentSnapshot.getString("Room"+j);
                            rtext=txt;
                            roomNames.add(rtext);
                            //Toast.makeText(getContext(),rtext, Toast.LENGTH_SHORT).show();
                            String ig = documentSnapshot.getString("RoomType"+j);
                            rimg=ig;
                           // Toast.makeText(getContext(),rimg, Toast.LENGTH_SHORT).show();
                            if(rimg.equals("Bedroom")){
                                roomImages.add(R.drawable.double_bed_icon);
                               // Toast.makeText(getContext(),"Entered inside", Toast.LENGTH_SHORT).show();
                            }
                            else  if(rimg.equals("BathRoom")){
                                roomImages.add(R.drawable.bathtub_icon);
                            }
                            else  if(rimg.equals("Kitchen")){
                                roomImages.add(R.drawable.kitchen_icon);
                            }
                            else  if(rimg.equals("DinningRoom")){
                                roomImages.add(R.drawable.dining_table_icon);
                            }

                        }
                    }
                });


        //////////////////////////////////////////////////////

        buttonAddRoomsMyRoom = (Button)view.findViewById(R.id.buttonAddRoomsMyRoom);
        textViewTopText = (TextView)view.findViewById(R.id.textViewTopText);


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Second fragment after 5 seconds appears
                recyclerViewMyRoom = (RecyclerView)view.findViewById(R.id.recyclerViewMyRoom);
                mAuth = FirebaseAuth.getInstance();

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                recyclerViewMyRoom.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                CustomAdapter customAdapter = new CustomAdapter(getActivity(),roomNames,roomImages);
                recyclerViewMyRoom.setAdapter(customAdapter); // set the Adapter to RecyclerView
                getFragmentManager().beginTransaction().commit();
            }
        };
        handler.postDelayed(runnable, 1000);

        buttonAddRoomsMyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentRoomsSecond();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


       /* DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid())
                .child("Rooms").child("Bedroom");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    dynamicroomname.add(snapshot.getKey().toString());
                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

*/

        return view;
    }
}
