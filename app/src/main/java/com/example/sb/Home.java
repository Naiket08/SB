package com.example.sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    Button buttonBack,buttonHamburger,buttonKill;
    ImageView imageViewUserProfile;
    TextView textViewNavHeaderUserName;

    private FirebaseAuth mAuth;

    FirebaseStorage storage;
    StorageReference storageReference;

    NavigationView navigationView;
    DrawerLayout drawer;
    FrameLayout fragment_container;
    ///
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    private FirebaseFirestore db  = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    public String roomcheck;
    Fragment fragment ;
    ////

    String UserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonHamburger = (Button)findViewById(R.id.buttonHamburger);
        buttonKill = (Button)findViewById(R.id.buttonKill);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        fragment_container = (FrameLayout)findViewById(R.id.fragment_container);

        /////
        userId = mAuth.getCurrentUser().getUid();
        ///calling

        DatabaseReference db  = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("User Details");
        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    roomcheck="notnull";
                   // Toast.makeText(Home.this, "Notnull", Toast.LENGTH_SHORT).show();
                }
                else {
                    roomcheck="";
                   // Toast.makeText(Home.this, "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      /*  db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText2 = documentSnapshot.getString("Roomfragment");
                        roomcheck=finalProfileText2;
                    }
                });*/

        //AppBar Button OnPressed
        buttonKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Done", Toast.LENGTH_SHORT).show();
                DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
                itemsRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String sn1 = dataSnapshot.getKey();

                        //Toast.makeText(getContext(), s1, Toast.LENGTH_SHORT).show();
                        ////////////////////////22222222222222222222222222222222222


                        //////////////////////
                        DatabaseReference itemsRef2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(sn1);
                        itemsRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                // Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                //This one

                                /////////////////////////////////////////////
                                DatabaseReference itemsRef3 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(sn1);
                                itemsRef3.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                        String sn2 = dataSnapshot.getKey();

                                        //Toast.makeText(getContext(), s2, Toast.LENGTH_SHORT).show();
                                        //////////////////////// 333333333333333333333333333333333333333333
                                        DatabaseReference itemsRef4 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(sn1).child(sn2);
                                        itemsRef4.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                                String sn3 = dataSnapshot.getKey();

                                                //String sn5 = dataSnapshot.child("mode").getValue(String.class);
                                                //String s6 = dataSnapshot.child("category").getValue(String.class);
                                                //Toast.makeText(context, sn5, Toast.LENGTH_SHORT).show();

                                                if(sn3.equals("SwitchBoardumber")||sn3.equals("combination")||sn3.equals("type")||sn3.equals("")) {
                                                }
                                                else {
                                                    itemsRef4.child(sn3).child("mode").setValue("off");

                                                    DatabaseReference itemsRef5 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(sn1).child(sn2).child(sn3);
                                                    itemsRef5.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            if(dataSnapshot.exists()){
                                                                itemsRef5.child("mode").setValue("off");
                                                                Fragment frag = new FragmentGeneral();
                                                                FragmentManager fragmentManager = getSupportFragmentManager();
                                                                fragmentManager.beginTransaction().replace(R.id.fragment_container, frag).commit();
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
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

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finishAffinity();
                onBackPressed();
            }
        });

        buttonHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(navigationView);
                //Profile Image Code
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                imageViewUserProfile = (ImageView)findViewById(R.id.user_profile);
                textViewNavHeaderUserName = (TextView)findViewById(R.id.navheaderUserName);

                StorageReference photoref = storageReference.child("images/"+mAuth.getCurrentUser().getUid().toString());
                photoref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getApplicationContext()).load(uri).into(imageViewUserProfile);
                        //Toast.makeText(UserProfile.this, "URI "+uri, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("User Details");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserEmail = dataSnapshot.child("EmailID").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.logout:
                        mAuth.signOut();
                        finishAffinity();
                        startActivity(new Intent(Home.this,Login.class));
                        finish();
                        break;
                    case R.id.change_pin:
                        startActivity(new Intent(Home.this,ConfirmPinToChange.class));
                        drawer.closeDrawer(navigationView,false);
                        break;
                    case R.id.forgot_password:
                        mAuth.sendPasswordResetEmail(UserEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Home.this, "Reset link sent to registered email id", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Home.this, "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        drawer.closeDrawer(navigationView,false);
                        break;
                    case R.id.profile:
                        startActivity(new Intent(Home.this, UserProfile.class));
                        drawer.closeDrawer(navigationView,false);
                        break;
                }

                return true;
            }
        });

        //loading the default fragment
        loadFragment(new FragmentGeneral());
        //BottomNavigationMenu
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 fragment = null;

                switch (item.getItemId()){
                    case R.id.navigation_general:
                            fragment = new FragmentGeneral();

                        break;
                    case R.id.navigation_rooms:
                        recheck();
                        if(roomcheck.equals("")) {

                            fragment = new FragmentRooms();
                        }
                        else
                        {
                            fragment = new FragmentMyRoom();
                        }
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    void  recheck(){
   DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms");
    db.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                roomcheck="notnull";
               // Toast.makeText(Home.this, "Notnull", Toast.LENGTH_SHORT).show();
            }
            else {
                roomcheck="";
               // Toast.makeText(Home.this, "null", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(navigationView)){
            drawer.closeDrawer(navigationView);
        }
        else{
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 0) {

                    new AlertDialog.Builder(this)
                            .setMessage("Are you sure you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Home.super.onBackPressed();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

            }
            else {
                super.onBackPressed();
            }
        }
    }
}