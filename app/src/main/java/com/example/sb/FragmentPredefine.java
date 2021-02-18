package com.example.sb;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.util.Arrays;
import java.util.HashMap;

public class FragmentPredefine extends Fragment {

    public EditText predefineRoomedit;
    Button predefinebuttonedit,saveboardbutton;
    RecyclerView recyclerViewPredefine;
    public ArrayList<String> itemnames = new ArrayList<String>();;
    LinearLayout layout1;
    SharedPreferences roompref;
    public String Roomname,text1,s3="SwitchBoard",prenum;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_predefine, container, false);

        predefineRoomedit =(EditText)v.findViewById(R.id.predefineRoomedit);
        predefinebuttonedit=(Button)v.findViewById(R.id.predefinebuttonedit);
        recyclerViewPredefine = (RecyclerView)v.findViewById(R.id.recyclerViewPredefine);
        layout1=(LinearLayout)v.findViewById(R.id.ll1);
        saveboardbutton=(Button)v.findViewById(R.id.saveboardbutton);
        mAuth = FirebaseAuth.getInstance();


        Roomname = getArguments().getString("Roomname");
        Toast.makeText(getContext(),Roomname, Toast.LENGTH_SHORT).show();
        text1 = getArguments().getString("Switchname");
        Toast.makeText(getContext(),text1, Toast.LENGTH_SHORT).show();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                prenum = dataSnapshot.child("combination").getValue(String.class);
                Toast.makeText(getContext(), "Prenoun:"+prenum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //////////////////////////////////////////////////////////////////////




        //////////////////////////////////////////////////////////////////////

        predefinebuttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Main Logic

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine=(Button)parentView.findViewById(R.id.buttondailogpredefine);
                EditText editTextdailogpredefine=(EditText)parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
                bottomSheetDialog.show();

                canceldailogpredefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });

                buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        predefineRoomedit.setEnabled(true);
                        s3= editTextdailogpredefine.getText().toString().trim();
                        predefineRoomedit.setText(s3);
                        predefineRoomedit.setEnabled(false);
                        bottomSheetDialog.cancel();

                    }
                });



            }
        });


       /*predefineRoomedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    predefineRoomedit.setEnabled(false);
                    predefineRoomedit.requestFocus();
                    predefineRoomedit.setCursorVisible(false);
                }


            }
        });*/

        saveboardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1).child("type").setValue(s3).addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void aVoid) {
                    }
                });
                Fragment newFragment = new FragmentRoomInner();
                Bundle arguments = new Bundle();
                arguments.putString( "Roomname",Roomname);
                arguments.putString( "Switchname",text1);
                newFragment.setArguments(arguments);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(prenum.equals("4*1"))
                {

                    itemnames.add("Light 1");
                    itemnames.add("Light 2");
                    itemnames.add("Light 3");
                    itemnames.add("Light 4");
                    itemnames.add("Fan 1");
                }
                else if(prenum.equals("4*2"))
                {
                    itemnames.add("Light 1");
                    itemnames.add("Light 2");
                    itemnames.add("Light 3");
                    itemnames.add("Light 4");
                    itemnames.add("Fan 1");
                    itemnames.add("Fan 2");

                }else if(prenum.equals("2*1"))
                {
                    itemnames.add("Light 1");
                    itemnames.add("Light 2");
                    itemnames.add("Fan 1");
                }
                else if(prenum.equals("custom"))
                {

                }
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),1);
                recyclerViewPredefine.setLayoutManager(gridLayoutManager1); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                CustomAdapterPredefine customAdapterPredefine = new CustomAdapterPredefine(getActivity(),itemnames,Roomname,text1,mAuth);
                recyclerViewPredefine.setAdapter(customAdapterPredefine); // set the Adapter to RecyclerView
            }
        };
        handler.postDelayed(runnable, 500);



        return v;
    }

}
