package com.example.sb;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class FragmentPredefine extends Fragment {

    public EditText predefineRoomedit;
    Button predefinebuttonedit,saveboardbutton;
    RecyclerView recyclerViewPredefine;
    public ArrayList<String> itemnames = new ArrayList<String>();
    public ArrayList<Integer> itemtypes = new ArrayList<Integer>();
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

        if((itemnames!=null&&itemtypes!=null&&itemnames.size()>0&&itemtypes.size()>0)){
            itemnames.clear();
            itemtypes.clear();
        }



        Roomname = getArguments().getString("Roomname");
       // Toast.makeText(getContext(),Roomname, Toast.LENGTH_SHORT).show();
        text1 = getArguments().getString("Switchname");
        predefineRoomedit.setText(text1);
       // Toast.makeText(getContext(),text1, Toast.LENGTH_SHORT).show();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                prenum = dataSnapshot.child("combination").getValue(String.class);
              //  Toast.makeText(getContext(), "Prenoun:"+prenum, Toast.LENGTH_SHORT).show();
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
                View parentView = getLayoutInflater().inflate(R.layout.dailoguebox_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine=(Button)parentView.findViewById(R.id.buttondailogpredefine);
                EditText editTextdailogpredefine=(EditText)parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
                bottomSheetDialog.show();
                editTextdailogpredefine.addTextChangedListener(new TextWatcher()
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
                String s4=predefineRoomedit.getText().toString().trim();
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1).child("type").setValue(s4).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 2");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 3");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 4");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Fan 1");
                    itemtypes.add(R.drawable.fan_icon);
                }
                else if(prenum.equals("4*2"))
                {
                    itemnames.add("Light 1");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 2");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 3");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 4");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Fan 1");
                    itemtypes.add(R.drawable.fan_icon);
                    itemnames.add("Fan 2");
                    itemtypes.add(R.drawable.fan_icon);

                }else if(prenum.equals("2*1"))
                {
                    itemnames.add("Light 1");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Light 2");
                    itemtypes.add(R.drawable.idea);
                    itemnames.add("Fan 1");
                    itemtypes.add(R.drawable.fan_icon);
                }
                else if(prenum.equals("custom"))
                {

                }
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),1);
                recyclerViewPredefine.setLayoutManager(gridLayoutManager1); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapterMyrooms to send the reference and data to Adapter
                CustomAdapterPredefine customAdapterPredefine = new CustomAdapterPredefine(getActivity(),itemnames,itemtypes,Roomname,text1,mAuth);
                recyclerViewPredefine.setAdapter(customAdapterPredefine); // set the Adapter to RecyclerView
            }
        };
        handler.postDelayed(runnable, 50);



        return v;
    }

}
