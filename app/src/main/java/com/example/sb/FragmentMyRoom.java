package com.example.sb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

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
    public String rtext,rimg;
//////////////////////////////////////
    public ArrayList<String> roomNames = new ArrayList<String>();;
    public ArrayList<String> roomNames2 = new ArrayList<String>();;
    public ArrayList<Integer> roomImages = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rooms,container,false);

        ArrayList dynamicroomname = new ArrayList<>();
        CustomAdapterMyrooms customAdapterMyrooms = new CustomAdapterMyrooms(getActivity(),roomNames,roomNames2,roomImages,mAuth);
        if((roomNames!=null&&roomNames2!=null&&roomImages!=null&&roomNames.size()>0&&roomNames2.size()>0&&roomImages.size()>0)){
            roomNames.clear();
            roomNames2.clear();
            roomImages.clear();
        }

        //////////////////////////////////////////////////
        //firecloud
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
       itemsRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               String s1 = dataSnapshot.getKey();
               roomNames2.add(s1);
               String s3 = dataSnapshot.child("name").getValue(String.class);
               //Toast.makeText(getContext(), s3, Toast.LENGTH_SHORT).show();
               roomNames.add(s3);
               String s2 =dataSnapshot.child("roomtype").getValue(String.class);
               if(s2.equals("Bedroom")){
                   roomImages.add(R.drawable.double_bed_icon);
                   //Toast.makeText(getContext(),"Entered inside", Toast.LENGTH_SHORT).show();
               }
               else  if(s2.equals("Bathroom")){
                   roomImages.add(R.drawable.bathtub_icon);
               }
               else  if(s2.equals("Kitchen")){
                   roomImages.add(R.drawable.kitchen_icon);
               }
               else  if(s2.equals("Dinning Room")){
                   roomImages.add(R.drawable.dining_table_icon);
               }
               //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
              // Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
               customAdapterMyrooms.notifyDataSetChanged();
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

       /* DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference().child("rooms");
        ValueEventListener eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String stext = ds.getValue(String.class);
                    rimg=stext;
                    Toast.makeText(getContext(), stext, Toast.LENGTH_SHORT).show();
                    *//*if(rimg.equals("Bedroom")){
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
                    }*//*

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        };
        itemsRef2.addListenerForSingleValueEvent(eventListener2);*/
/////////////////////////////////////////////////////////////////////
        /*db1.collection("users").document(userId).collection("Rooms").document("RoomName").get()
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
                });*/


        //////////////////////////////////////////////////////

        buttonAddRoomsMyRoom = (Button)view.findViewById(R.id.buttonAddRoomsMyRoom);
        textViewTopText = (TextView)view.findViewById(R.id.textViewTopText);



                //Second fragment after 5 seconds appears
                recyclerViewMyRoom = (RecyclerView)view.findViewById(R.id.recyclerViewMyRoom);
                mAuth = FirebaseAuth.getInstance();

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                recyclerViewMyRoom.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
                //  call the constructor of CustomAdapterMyrooms to send the reference and data to Adapter
                recyclerViewMyRoom.setAdapter(customAdapterMyrooms); // set the Adapter to RecyclerView
                getFragmentManager().beginTransaction().commit();



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
                customAdapterMyrooms.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

*/

        return view;
    }
}
