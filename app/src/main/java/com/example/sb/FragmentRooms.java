package com.example.sb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentRooms extends Fragment {

ImageView imgeviewfragmentroom,noroom;
TextView textViewfragmentroom;
Button floatingbutton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_rooms,container,false);
       //main content

        imgeviewfragmentroom=(ImageView)v.findViewById(R.id.imgeviewfragmentroom);
        noroom=(ImageView)v.findViewById(R.id.noroom);
        textViewfragmentroom=(TextView)v.findViewById(R.id.textViewfragmentroom);
        floatingbutton=(Button)v.findViewById(R.id.floatigbutton);

        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newFragment = new FragmentRoomsSecond();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });


       return v;
    }
}
