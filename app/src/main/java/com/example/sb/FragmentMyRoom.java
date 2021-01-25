package com.example.sb;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentMyRoom extends Fragment {

    Button buttonBackMyRoom,buttonHamburgerMyRoom,buttonKillMyRoom,buttonAddRoomsMyRoom;
    TextView textViewTopText;
    RecyclerView recyclerViewMyRoom;

    ArrayList roomNames = new ArrayList<>(Arrays.asList("Bed Room", "BathRoom", "Kitchen", "Dining Room", "Custom Room"));
    ArrayList roomImages = new ArrayList<>(Arrays.asList(R.drawable.double_bed_icon, R.drawable.bathtub_icon, R.drawable.kitchen_icon,
            R.drawable.dining_table_icon, R.drawable.house_icon));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rooms,container,false);

        buttonAddRoomsMyRoom = (Button)view.findViewById(R.id.buttonAddRoomsMyRoom);
        buttonBackMyRoom = (Button)view.findViewById(R.id.buttonBackMyRoom);
        buttonHamburgerMyRoom = (Button)view.findViewById(R.id.buttonHamburgerMyRoom);
        buttonKillMyRoom = (Button)view.findViewById(R.id.buttonKillMyRoom);
        textViewTopText = (TextView)view.findViewById(R.id.textViewTopText);
        recyclerViewMyRoom = (RecyclerView)view.findViewById(R.id.recyclerViewMyRoom);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewMyRoom.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), roomNames,roomImages);
        recyclerViewMyRoom.setAdapter(customAdapter); // set the Adapter to RecyclerView

        return view;
    }
}
