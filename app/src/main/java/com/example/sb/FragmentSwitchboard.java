package com.example.sb;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.sb.R.id.content;
import static com.example.sb.R.id.imageViewswitchboard;
import static com.example.sb.R.id.info;

public class FragmentSwitchboard extends DialogFragment {

    ImageButton switchinfo1, switchinfo2, switchinfo3, switchinfo4, switch1, switch2, switch3, switch4;
    LinearLayout llswitch1,llswitch2,llswitch3,llswitch4;
    public int i;
    ////////////////////////////////
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    public int k,f;
    public String num,num1,Roomname,text1,switchname;
    public ArrayList<String> list = new ArrayList<String>();
    /////////////////////////////////////

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_switch_board, container, false);

        switchinfo1 = (ImageButton) v.findViewById(R.id.switchinfo1);
        switchinfo2 = (ImageButton) v.findViewById(R.id.switchinfo2);
        switchinfo3 = (ImageButton) v.findViewById(R.id.switchinfo3);
        switchinfo4 = (ImageButton) v.findViewById(R.id.switchinfo4);
        switch1 = (ImageButton) v.findViewById(R.id.switch1);
        switch2 = (ImageButton) v.findViewById(R.id.switch2);
        switch3 = (ImageButton) v.findViewById(R.id.switch3);
        switch4 = (ImageButton) v.findViewById(R.id.switch4);
        llswitch1=(LinearLayout) v.findViewById(R.id.llswitch1);
        llswitch2=(LinearLayout) v.findViewById(R.id.llswitch2);
        llswitch3=(LinearLayout) v.findViewById(R.id.llswitch3);
        llswitch4=(LinearLayout) v.findViewById(R.id.llswitch4);
/////////////////////
        mAuth = FirebaseAuth.getInstance();

        Roomname = getArguments().getString("Roomname");
       // Toast.makeText(getContext(), Roomname, Toast.LENGTH_SHORT).show();

//////////////////////////////////////
            ///database calling
                     dbchange();
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname);
        db3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                num1 = dataSnapshot.child("number").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            ///////////////

        switchinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
            switchboardinfo();
            }
        });

        switchinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=2;
                switchboardinfo();
            }
        });
        switchinfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=3;
                switchboardinfo();
            }
        });
        switchinfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=4;
                switchboardinfo();
            }
        });

        llswitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=1;
                switchoptionaldailgue();
            }
        });
        llswitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=2;
                switchoptionaldailgue();
            }
        });
        llswitch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=3;
                switchoptionaldailgue();
            }
        });
        llswitch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=4;
                switchoptionaldailgue();
            }
        });

        ////////////////Repeated
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=1;
                switchoptionaldailgue();
            }
        });
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=2;
                switchoptionaldailgue();
            }
        });
        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=3;
                switchoptionaldailgue();
            }
        });
        switch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=4;
                switchoptionaldailgue();
            }
        });

        return v;
    }

    public void switchboardinfo() {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.switchboardinfo, null);
        ImageView cancelab = (ImageView) parentView.findViewById(R.id.cancelswitchboardinfo);
        TextView  info1 =(TextView)parentView.findViewById(R.id.textviewswitchboardinfo);
        TextView  info2 =(TextView)parentView.findViewById(R.id.textviewswitchboardinfo2);
        TextView  info3 =(TextView)parentView.findViewById(R.id.ideatextswitchboardinfo);
        TextView  info4 =(TextView)parentView.findViewById(R.id.ideatextswitchboardinfo2);
        if(i==1) {

            info1.setText("Switch Board 1");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("4 Lights");
            info4.setText("1 Fan");

        }
        else if(i==2)
        {
            info1.setText("Switch Board 2");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("4 Lights");
            info4.setText("2 Fans");


        }
        else if(i==3)
        {

            info1.setText("Switch Board 3");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText(" 2 Lights");
            info4.setText(" 1 Fan");

        }
        else if(i==4)
        {

            info1.setText("Custom");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("  Lights");
            info4.setText("  Fan");
        }

        bottomSheetDialog.setContentView(parentView);
        bottomSheetDialog.show();

        cancelab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
    }

    public void switchoptionaldailgue() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.switchoptiondailoge, null);
        ImageView cancelab2 = (ImageView) parentView.findViewById(R.id.cancelswitchdailog);
        Button buttonswitchdailog2=(Button)parentView.findViewById(R.id.buttonswitchdailog);
        EditText editTextswitchdailog2=(EditText)parentView.findViewById(R.id.editTextswitchdailog);

        bottomSheetDialog.setContentView(parentView);

        bottomSheetDialog.show();
        editTextswitchdailog2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                if (s.length() == 15)
                {
                    // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                    Toast.makeText(getContext(), "Character Limit Reached ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cancelab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
        userId = mAuth.getCurrentUser().getUid();

        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
        db2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                num = dataSnapshot.child("Key").getValue(String.class);
              //  Toast.makeText(getContext(), num, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


               buttonswitchdailog2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        text1 = editTextswitchdailog2.getText().toString().trim();
                        if(TextUtils.isEmpty(text1)){
                            Toast.makeText(getContext(), "Enter Key", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            if(text1.equals(num)){
                                bottomSheetDialog.cancel();
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                //main logic
                                BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(getContext());
                                View parentView = getLayoutInflater().inflate(R.layout.addswitchdailogue, null);
                                ImageView cancelbutton = (ImageView) parentView.findViewById(R.id.canceldailogaddswitch);
                                Button buttonaddswitch=(Button)parentView.findViewById(R.id.buttondailogaddswitch);
                                EditText editTextaddswitch=(EditText)parentView.findViewById(R.id.editTextdailogaddswitch);

                                bottomSheetDialog2.setContentView(parentView);

                                bottomSheetDialog2.show();
                                editTextswitchdailog2.addTextChangedListener(new TextWatcher()
                                {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after)
                                    {
                                        if (s.length() == 15)
                                        {
                                            // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                                            Toast.makeText(getContext(), "Character Limit Reached ", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count)
                                    {
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }
                                });
                                cancelbutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        bottomSheetDialog2.cancel();
                                    }
                                });

                                buttonaddswitch.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        switchname= editTextaddswitch.getText().toString().trim();

                                        if(TextUtils.isEmpty(switchname)){
                                            Toast.makeText(getContext(), "Enter Switchname", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            /////////////////////////////////////////////////////////////////
                                            //Database logic

                                            if(list.contains(switchname)){
                                                Toast.makeText(getContext(), "Switch already Exist", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                ////////////////////////////////////////
                                                if(f==1) {
                                                    /////
                                                    k = Integer.valueOf(num1);
                                                    ++k;
                                                    Map<String, Object> user = new HashMap<>();
                                                    user.put("SwitchBoardumber",num1);
                                                    user.put("combination","4*1");
                                                    user.put("type","SwitchBoard");
                                                    switchname= editTextaddswitch.getText().toString().trim();
                                                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(switchname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                        num1=String.valueOf(k);
                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child("number").setValue(num1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                 Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        Fragment newFragment = new FragmentPredefine();
                                                        Bundle arguments = new Bundle();
                                                        arguments.putString( "Roomname",Roomname);
                                                        arguments.putString( "Switchname",switchname);
                                                        newFragment.setArguments(arguments);
                                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.fragment_container,newFragment);
                                                        transaction.addToBackStack(null);
                                                        transaction.commit();
                                                        bottomSheetDialog2.cancel();


                                                    /////
                                                }
                                                else if(f==2){
                                                    /////
                                                    k = Integer.valueOf(num1);
                                                    ++k;
                                                    Map<String, Object> user = new HashMap<>();
                                                    user.put("SwitchBoardumber",num1);
                                                    user.put("combination","4*2");
                                                    user.put("type","SwitchBoard");
                                                    switchname= editTextaddswitch.getText().toString().trim();
                                                      FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(switchname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                        num1=String.valueOf(k);
                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child("number").setValue(num1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                //  Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        Fragment newFragment = new FragmentPredefine();
                                                        Bundle arguments = new Bundle();
                                                        arguments.putString( "Roomname",Roomname);
                                                        arguments.putString( "Switchname",switchname);
                                                        newFragment.setArguments(arguments);
                                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.fragment_container,newFragment);
                                                        transaction.addToBackStack(null);
                                                        transaction.commit();
                                                        bottomSheetDialog2.cancel();


                                                } else if(f==3){
                                                    /////
                                                    k = Integer.valueOf(num1);
                                                    ++k;
                                                    Map<String, Object> user = new HashMap<>();
                                                    user.put("SwitchBoardumber",num1);
                                                    user.put("combination","2*1");
                                                    user.put("type","SwitchBoard");
                                                    switchname= editTextaddswitch.getText().toString().trim();
                                                     FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(switchname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                        num1=String.valueOf(k);
                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child("number").setValue(num1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                //   Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        Fragment newFragment = new FragmentPredefine();
                                                        Bundle arguments = new Bundle();
                                                        arguments.putString( "Roomname",Roomname);
                                                        arguments.putString( "Switchname",switchname);
                                                        newFragment.setArguments(arguments);
                                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.fragment_container,newFragment);
                                                        transaction.addToBackStack(null);
                                                        transaction.commit();
                                                        bottomSheetDialog2.cancel();

                                                } else if(f==4){
                                                    /////
                                                    k = Integer.valueOf(num1);
                                                    ++k;
                                                    Map<String, Object> user = new HashMap<>();
                                                    user.put("SwitchBoardumber",num1);
                                                    user.put("combination","custom");
                                                    user.put("type","SwitchBoard");
                                                    switchname= editTextaddswitch.getText().toString().trim();
                                                     FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(switchname).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child("number").setValue(num1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                //   Toast.makeText(getContext(), "Room added", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        Fragment newFragment = new FragmentPredefine();
                                                        Bundle arguments = new Bundle();
                                                        arguments.putString( "Roomname",Roomname);
                                                        arguments.putString( "Switchname",switchname);
                                                        newFragment.setArguments(arguments);
                                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.fragment_container,newFragment);
                                                        transaction.addToBackStack(null);
                                                        transaction.commit();
                                                        bottomSheetDialog2.cancel();


                                                }


                                                ////////////////////////////////////////////////////////////////
                                            }

                                            }

                                    }
                                });

                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                ///////////////////Important

                                ////////////////////////////////////////////

                            }
                            else
                            {
                                Toast.makeText(getContext(), "Wrong Key", Toast.LENGTH_SHORT).show();
                            }

                        }



                    }
                });





    }

    public void dbchange(){
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname);
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String s1 = dataSnapshot.getKey();
               // Toast.makeText(getContext(),s1, Toast.LENGTH_SHORT).show();
                if(s1.equals("number")||s1.equals("roomtype")||s1.equals("")||s1.equals("name")) {

                }
                else
                {
                    list.add(s1);
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
    }


}
