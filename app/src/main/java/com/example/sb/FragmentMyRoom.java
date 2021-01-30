package com.example.sb;

import android.app.Activity;
import android.os.Bundle;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    //ArrayList roomNames = new ArrayList<>(Arrays.asList("Bedroom", "Bathroom", "Kitchen", "Dining Room", "Custom Room"));
    ArrayList roomImages = new ArrayList<>(Arrays.asList(R.drawable.double_bed_icon, R.drawable.bathtub_icon, R.drawable.kitchen_icon,
            R.drawable.dining_table_icon /*R.drawable.house_icon*/));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rooms,container,false);

        ArrayList dynamicroomname = new ArrayList<>();

        buttonAddRoomsMyRoom = (Button)view.findViewById(R.id.buttonAddRoomsMyRoom);
        textViewTopText = (TextView)view.findViewById(R.id.textViewTopText);
        recyclerViewMyRoom = (RecyclerView)view.findViewById(R.id.recyclerViewMyRoom);
        mAuth = FirebaseAuth.getInstance();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewMyRoom.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(getActivity(),dynamicroomname,roomImages);
        recyclerViewMyRoom.setAdapter(customAdapter); // set the Adapter to RecyclerView

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid())
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

        return view;
    }
}
