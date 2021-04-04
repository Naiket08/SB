package com.example.sb;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FragmentInnerSwitchBoard extends Fragment {

    TextView textViewRoomNo;
    ImageView imageViewBrownJacket,imageViewWhiteJacket,imageViewAppliances,imageViewBulb;
    DialView customdial;
    Button speedcontrol;

    TextView textViewSwitchBoard1;
    Button buttonLight1,buttonLight2,buttonFan,buttonLight3,buttonSwitchBoardApppliance;
    public String Roomname,text3,fanspeed;
    ////////////////////////////////////
    RecyclerView recyclerViewInnerSwitchboard;
    //////////////////////
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    ////////////////////////
    public ArrayList<String> LightName = new ArrayList<String>();
    public ArrayList<Integer> LightType = new ArrayList<Integer>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_switchboard,container,false);
//////Done
        Roomname = getArguments().getString("Roomname");
      //  Toast.makeText(getContext(),Roomname, Toast.LENGTH_SHORT).show();
        text3= getArguments().getString("Switchname");
        Toast.makeText(getContext(),text3, Toast.LENGTH_SHORT).show();
///////
        textViewRoomNo = (TextView)view.findViewById(R.id.textViewRoomNo);
        textViewRoomNo.setText(Roomname);
        imageViewBrownJacket = (ImageView)view.findViewById(R.id.imageViewBrownJacket);
        imageViewWhiteJacket = (ImageView)view.findViewById(R.id.imageViewWhiteJacket);
        imageViewBulb = (ImageView)view.findViewById(R.id.imageViewBulb);

        imageViewAppliances = (ImageView)view.findViewById(R.id.imageViewAppliances);

        textViewSwitchBoard1 = (TextView)view.findViewById(R.id.textViewSwitchBoard1);
        textViewSwitchBoard1.setText(text3);
        buttonLight1 = (Button)view.findViewById(R.id.buttonLight1);
        buttonLight2 = (Button)view.findViewById(R.id.buttonLight2);
        buttonLight3 = (Button)view.findViewById(R.id.buttonLight3);
        buttonFan = (Button)view.findViewById(R.id.buttonFan);
        speedcontrol=(Button)view.findViewById(R.id.speedcontrol);
        customdial=(DialView)view.findViewById(R.id.dialView);


        buttonSwitchBoardApppliance = (Button)view.findViewById(R.id.buttonSwitchBoardAppliance);
        CustomAdapterInnerSwitchboard customAdapter2 = new CustomAdapterInnerSwitchboard(getActivity(),LightName,LightType,mAuth,Roomname,text3,imageViewBrownJacket,imageViewWhiteJacket,imageViewAppliances,imageViewBulb,customdial,speedcontrol);
        if((LightName!=null&&LightType!=null&&LightName.size()>0&&LightType.size()>0)){
            LightName.clear();
            LightType.clear();
        }

        //////////////////////////////////////////////////
        //firecloud
        //////////////////////////////////////////////////
        //firecloud
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text3);
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String s1 = dataSnapshot.getKey();
                Toast.makeText(getContext(),s1, Toast.LENGTH_SHORT).show();
                if(s1.equals("number")||s1.equals("roomtype")||s1.equals("")||s1.equals("SwitchBoardumber")||s1.equals("combination")||s1.equals("type")) {

                }
                else

                {
                     //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                    LightName.add(s1);
                    String s2 = dataSnapshot.child("category").getValue(String.class);
                      //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                    if(s2.equals("Light")){
                        LightType.add(R.drawable.ic_idea);
                       // Toast.makeText(getContext(),"Entered inside", Toast.LENGTH_SHORT).show();
                    }
                    else  if(s2.equals("Fan")){
                        LightType.add(R.drawable.fan_icon);
                    }
                    else  if(s2.equals("Appliance")) {
                        LightType.add(R.drawable.appliances_icon);
                    }
                    //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                    customAdapter2.notifyDataSetChanged();
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



      /*  buttonLight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.VISIBLE);
                imageViewBulb.setVisibility(View.VISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        buttonLight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.VISIBLE);
                imageViewBulb.setVisibility(View.VISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        buttonLight3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.VISIBLE);
                imageViewBulb.setVisibility(View.VISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        buttonFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.INVISIBLE);
                imageViewBulb.setVisibility(View.INVISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.VISIBLE);
                imageViewWhiteJacket.setVisibility(View.VISIBLE);
            }
        });

        buttonSwitchBoardApppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.INVISIBLE);
                imageViewBulb.setVisibility(View.INVISIBLE);
                imageViewAppliances.setVisibility(View.VISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });*/

        recyclerViewInnerSwitchboard = (RecyclerView)view.findViewById(R.id.recyclerViewInnerSwitchboard);
        recyclerViewInnerSwitchboard.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        recyclerViewInnerSwitchboard.setAdapter(customAdapter2); // set the Adapter to RecyclerView
        getFragmentManager().beginTransaction().commit();

        return view;
    }
}
