package com.example.sb;

import android.os.Bundle;
import android.os.Handler;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class FragmentInnerCategoryLights extends Fragment {

    public String category;

    TextView textViewCategoryLights,textViewInnerCategory1,textViewCategory1Light1,textViewInnerCategory2,textViewInnerCategory3,textViewInnerCategory4;
    Button buttonOnCategory1Light1,buttonOnCat1Light2,buttonEditCategory1Light1;
    RecyclerView recycleviewinnercategory1,recycleviewinnercategory2,recycleviewinnercategory3,recycleviewinnercategory4;
    public ArrayList<String> categoryname = new ArrayList<String>();;
    public ArrayList<String> categoryname2= new ArrayList<String>();;
    public ArrayList<String> categoryname3 = new ArrayList<String>();;
    public ArrayList<String> categoryname4 = new ArrayList<String>();;
    private FirebaseAuth mAuth;
    public String num1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_category_lights,container,false);

        category = getArguments().getString("category");
        Toast.makeText(getContext(),category, Toast.LENGTH_SHORT).show();
        textViewCategoryLights = (TextView)view.findViewById(R.id.textViewCategoryLights);
        textViewInnerCategory1 = (TextView)view.findViewById(R.id.textViewInnerCategory1);
        textViewInnerCategory2 = (TextView)view.findViewById(R.id.textViewInnerCategory2);
        textViewInnerCategory3 = (TextView)view.findViewById(R.id.textViewInnerCategory3);
        buttonEditCategory1Light1 = (Button)view.findViewById(R.id.buttonEditCategory1Light1);
        buttonOnCategory1Light1 = (Button)view.findViewById(R.id.buttonOnCategory1Light1);
        recycleviewinnercategory1=(RecyclerView)view.findViewById(R.id.recycleviewinnercategory1);
        recycleviewinnercategory2=(RecyclerView)view.findViewById(R.id.recycleviewinnercategory2);
        recycleviewinnercategory3=(RecyclerView)view.findViewById(R.id.recycleviewinnercategory3);
        recycleviewinnercategory4=(RecyclerView)view.findViewById(R.id.recycleviewinnercategory4);
        textViewInnerCategory4=(TextView)view.findViewById(R.id.textViewInnerCategory4);
        mAuth = FirebaseAuth.getInstance();
       /* if((categoryname!=null&&categoryname.size()>0)){
            categoryname.clear();

        }*/

        CustomAdapterInnerCategory customAdapter2 = new CustomAdapterInnerCategory(getActivity(),categoryname,mAuth);
        CustomAdapterInnerCategory2 customAdapter3 = new CustomAdapterInnerCategory2(getActivity(),categoryname2,mAuth);
        CustomAdapterInnerCategory3 customAdapter4 = new CustomAdapterInnerCategory3(getActivity(),categoryname3,mAuth);
        CustomAdapterInnerCategory4 customAdapter5 = new CustomAdapterInnerCategory4(getActivity(),categoryname4,mAuth);
        ///for Light
if(category.equals("Light")) {

    Toast.makeText(getContext(), "Inside main", Toast.LENGTH_SHORT).show();
    ////////////////////////

    DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
    itemsRef.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            String s1 = dataSnapshot.getKey();

            //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
            ////////////////////////               22222222222222222222222222222222222


        //////////////////////
            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    num1 = dataSnapshot.child("roomtype").getValue(String.class);
                    Toast.makeText(getContext(),num1, Toast.LENGTH_SHORT).show();

                    //This one
                    if(num1.equals("Bedroom")) {
                        /////////////////////////////////////////////
                        DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
                        itemsRef2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                String s2 = dataSnapshot.getKey();
                                if (s2.equals("roomtype") || s2.equals("number") || s2.equals("SwitchBoardumber") || s2.equals("combination") || s2.equals("type") || s2.equals("")) {
                                } else {
                                    //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                    //////////////////////// 333333333333333333333333333333333333333333
                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2);
                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            String s3 = dataSnapshot.getKey();
                                            if (s3.equals("roomtype") || s3.equals("number") || s3.equals("SwitchBoardumber") || s3.equals("combination") || s3.equals("type") || s3.equals("")) {

                                            } else {

                                                categoryname.add(s3);
                                                Toast.makeText(getContext(), s3, Toast.LENGTH_SHORT).show();


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
                    else if(num1.equals("Bathroom")){

                        Toast.makeText(getContext(),"inside Bathroom", Toast.LENGTH_SHORT).show();
                        ////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////
                        DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
                        itemsRef2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                String s2 = dataSnapshot.getKey();
                                if (s2.equals("roomtype") || s2.equals("number") || s2.equals("SwitchBoardumber") || s2.equals("combination") || s2.equals("type") || s2.equals("")) {
                                } else {
                                    //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                    //////////////////////// 333333333333333333333333333333333333333333
                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2);
                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            String s3 = dataSnapshot.getKey();
                                            if (s3.equals("roomtype") || s3.equals("number") || s3.equals("SwitchBoardumber") || s3.equals("combination") || s3.equals("type") || s3.equals("")) {

                                            } else {

                                                categoryname2.add(s3);
                                                Toast.makeText(getContext(), s3, Toast.LENGTH_SHORT).show();


                                                customAdapter3.notifyDataSetChanged();
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
                        ////////////////////////////////////////////////////////////////////////////

                    }
                    else if(num1.equals("Kitchen")){


                        ////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////
                        DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
                        itemsRef2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                String s2 = dataSnapshot.getKey();
                                if (s2.equals("roomtype") || s2.equals("number") || s2.equals("SwitchBoardumber") || s2.equals("combination") || s2.equals("type") || s2.equals("")) {
                                } else {
                                    //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                    //////////////////////// 333333333333333333333333333333333333333333
                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2);
                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            String s3 = dataSnapshot.getKey();
                                            if (s3.equals("roomtype") || s3.equals("number") || s3.equals("SwitchBoardumber") || s3.equals("combination") || s3.equals("type") || s3.equals("")) {

                                            } else {

                                                categoryname3.add(s3);
                                                Toast.makeText(getContext(), s3, Toast.LENGTH_SHORT).show();


                                                customAdapter4.notifyDataSetChanged();
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
                        ////////////////////////////////////////////////////////////////////////////

                    }
                    else if(num1.equals("Dinning Room")){

                        /////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////
                        DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1);
                        itemsRef2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                String s2 = dataSnapshot.getKey();
                                if (s2.equals("roomtype") || s2.equals("number") || s2.equals("SwitchBoardumber") || s2.equals("combination") || s2.equals("type") || s2.equals("")) {
                                } else {
                                    //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                    //////////////////////// 333333333333333333333333333333333333333333
                                    DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2);
                                    itemsRef2.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            String s3 = dataSnapshot.getKey();
                                            if (s3.equals("roomtype") || s3.equals("number") || s3.equals("SwitchBoardumber") || s3.equals("combination") || s3.equals("type") || s3.equals("")) {

                                            } else {

                                                categoryname4.add(s3);
                                                Toast.makeText(getContext(), s3, Toast.LENGTH_SHORT).show();


                                                customAdapter5.notifyDataSetChanged();
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
                        ////////////////////////////////////////////////////////////////////////////

                    }
///////////////////
// ////////////////////////////////////
                    ////////////////////////////////////////////

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





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

    ////////////////////////////////////////
}



////////////////////////////  MAIN CALL
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),1);
                recycleviewinnercategory1.setLayoutManager(gridLayoutManager1); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                recycleviewinnercategory1.setAdapter(customAdapter2); // set the Adapter to RecyclerView
            }
        };
        handler.postDelayed(runnable, 50);

        Handler handler2 = new Handler();
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),1);
                recycleviewinnercategory2.setLayoutManager(gridLayoutManager2); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                recycleviewinnercategory2.setAdapter(customAdapter3); // set the Adapter to RecyclerView

            }
        };
        handler2.postDelayed(runnable2, 50);

        Handler handler3 = new Handler();
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getContext(),1);
                recycleviewinnercategory3.setLayoutManager(gridLayoutManager3); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                recycleviewinnercategory3.setAdapter(customAdapter4); // set the Adapter to RecyclerView

            }
        };
        handler3.postDelayed(runnable3 ,50);

        Handler handler4 = new Handler();
        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                GridLayoutManager gridLayoutManager4 = new GridLayoutManager(getContext(),1);
                recycleviewinnercategory4.setLayoutManager(gridLayoutManager4); // set LayoutManager to RecyclerView
      /*        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                 layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                recycleviewinnercategory4.setAdapter(customAdapter5); // set the Adapter to RecyclerView

            }
        };
        handler4.postDelayed(runnable4 ,50);







        return view;
    }
}
