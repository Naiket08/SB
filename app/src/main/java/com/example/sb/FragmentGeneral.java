package com.example.sb;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentGeneral extends Fragment {

    TextView textViewFavourite,textViewOtherTitle;
    ImageView imageViewNoFavAdded;
    Button buttonAddRooms,buttonCategory,buttonAboutUs,buttonContactUs;
    RecyclerView recyclerViewGeneral;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    public ArrayList<String> Generalname = new ArrayList<String>();
    public ArrayList<Integer> GeneralType = new ArrayList<Integer>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general,container,false);

        textViewFavourite = (TextView)view.findViewById(R.id.textViewFavourite);
        textViewOtherTitle = (TextView)view.findViewById(R.id.textViewOtherTitle);
        buttonAddRooms = (Button)view.findViewById(R.id.buttonAddRooms);
        buttonCategory = (Button)view.findViewById(R.id.buttonCategory);
        buttonAboutUs = (Button)view.findViewById(R.id.buttonAboutUs);
        buttonContactUs = (Button)view.findViewById(R.id.buttonContactUs);
        imageViewNoFavAdded=(ImageView)view.findViewById(R.id.imageViewNoFavAdded);
        recyclerViewGeneral=(RecyclerView)view.findViewById(R.id.recycleviewGeneral);

        ///////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////
        mAuth = FirebaseAuth.getInstance();


        CustomAdapterGeneral customAdapter2 = new CustomAdapterGeneral(getActivity(),Generalname,GeneralType,mAuth);

        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites");
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String s1 = dataSnapshot.getKey();

                if(s1.equals("")) {

                    imageViewNoFavAdded.setVisibility(View.VISIBLE);
                    recyclerViewGeneral.setVisibility(View.INVISIBLE);

                    //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                    ////////////////////////               22222222222222222222222222222222222
                }
                else {

                    imageViewNoFavAdded.setVisibility(View.INVISIBLE);
                    recyclerViewGeneral.setVisibility(View.VISIBLE);

                    //////////////////////
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1);
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //  Toast.makeText(getContext(),num1, Toast.LENGTH_SHORT).show();

                            //This one

                            /////////////////////////////////////////////
                            DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1);
                            itemsRef2.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    String s2 = dataSnapshot.getKey();

                                    //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                    //////////////////////// 333333333333333333333333333333333333333333
                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2);
                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            String s3 = dataSnapshot.getKey();

                                           // Toast.makeText(getContext(), s3, Toast.LENGTH_SHORT).show();
                                                    String s5 = dataSnapshot.child("name").getValue(String.class);
                                                 //
                                          //  Toast.makeText(getContext(),s5, Toast.LENGTH_SHORT).show();

                                            Generalname.add(s5);
                                                   /* if(s6.equals("Light")) {
                                                        categoryname.add(s5);
                                                        customAdapter2.notifyDataSetChanged();
                                                    }

                                                */
                                            if(s3.contains("Light")){
                                                GeneralType.add(R.drawable.ic_idea);
                                                // Toast.makeText(getContext(),"Entered inside", Toast.LENGTH_SHORT).show();
                                            }
                                            else  if(s3.contains("Fan")){
                                                GeneralType.add(R.drawable.fan_icon);
                                            }
                                            else  if(s3.equals("Appliance")) {
                                                GeneralType.add(R.drawable.appliances_icon);
                                            }
                                            //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                                            // Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                            customAdapter2.notifyDataSetChanged();

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


///////////////////
// ////////////////////////////////////
                            ////////////////////////////////////////////

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }//First ending

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
        ////////////////////////////////////////////////////////////////////////////////////////////




        buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentCategory();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentAboutUs();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        buttonContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentContactUs();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        Handler handler4 = new Handler();
        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),3);
                recyclerViewGeneral.setLayoutManager(gridLayoutManager1); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                recyclerViewGeneral.setAdapter(customAdapter2); // set the Adapter to RecyclerView

            }
        };
        handler4.postDelayed(runnable4 ,50);

        return view;
    }
}
