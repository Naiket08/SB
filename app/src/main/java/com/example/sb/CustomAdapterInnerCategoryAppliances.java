package com.example.sb;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;

public class CustomAdapterInnerCategoryAppliances extends RecyclerView.Adapter<CustomAdapterInnerCategoryAppliances.ViewHolder> {
    ArrayList categoryname3,categorytype3;

    Context context;
    private FirebaseAuth mAuth;
    String userId,num1,num2,num3;
    String status="false";
    DatabaseReference db;
    public ArrayList<String> namecheck = new ArrayList<String>();;


    public CustomAdapterInnerCategoryAppliances(Context context, ArrayList categoryname3, ArrayList categorytype3, FirebaseAuth mAuth) {


        this.context = context;
        this.categorytype3=categorytype3;
        this.categoryname3=categoryname3;
        this.mAuth=mAuth;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_inner_category, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder



        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewInnerCategoryLight1.setText((CharSequence) categoryname3.get(position));
        holder.buttonOnCategory1Light1.setBackgroundResource((Integer)categorytype3.get(position));
        // implement setOnClickListener event on item view.

        ////Demo
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////// EDIT TEXTVIEW
        holder.buttonEditCategory1Light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////////////////////////////////////
                String text = holder.textViewInnerCategoryLight1.getText().toString();
                ////////////////////////////////////////////////////////////////////////////////////
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
                                // Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            namecheck.add(s5);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("name").getValue(String.class);
                                                                        String favcheck = dataSnapshot.child("Favorite").getValue(String.class);

                                                                        //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();
                                                                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                                                                        View parentView = LayoutInflater.from(context).inflate(R.layout.dailoguebox_predefine, null);
                                                                        //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                                                                        ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                                                                        Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                                                                        EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                                                                        bottomSheetDialog.setContentView(parentView);
                                                                        // Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                                                                        bottomSheetDialog.show();
                                                                        editTextdailogpredefine.addTextChangedListener(new TextWatcher()
                                                                        {
                                                                            @Override
                                                                            public void beforeTextChanged(CharSequence s, int start, int count, int after)
                                                                            {
                                                                                if (s.length() == 15)
                                                                                {
                                                                                    // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                                                                                    Toast.makeText(context, "Character Limit Reached ", Toast.LENGTH_SHORT).show();
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
                                                                                num3=s5;
                                                                                bottomSheetDialog.cancel();
                                                                            }
                                                                        });

                                                                        buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                num3 = editTextdailogpredefine.getText().toString();
                                                                                //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                                                                                if(TextUtils.isEmpty(num3)){
                                                                                    // Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                                else{
                                                                                    if (namecheck.contains(num3)) {
                                                                                        Toast.makeText(context, "Name Already Exist", Toast.LENGTH_SHORT).show();
                                                                                    } else {
                                                                                        holder.textViewInnerCategoryLight1.setText(num3);
                                                                                        // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                                                                                        if (favcheck.equals("true")) {
                                                                                            db2.child("name").setValue(num3);
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        } else {
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        });



                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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

                                    //  Toast.makeText(context,"inside Bathroom", Toast.LENGTH_SHORT).show();
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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            namecheck.add(s5);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("name").getValue(String.class);
                                                                        String favcheck = dataSnapshot.child("Favorite").getValue(String.class);

                                                                        //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();
                                                                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                                                                        View parentView = LayoutInflater.from(context).inflate(R.layout.dailoguebox_predefine, null);
                                                                        //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                                                                        ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                                                                        Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                                                                        EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                                                                        bottomSheetDialog.setContentView(parentView);
                                                                        // Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                                                                        bottomSheetDialog.show();
                                                                        editTextdailogpredefine.addTextChangedListener(new TextWatcher()
                                                                        {
                                                                            @Override
                                                                            public void beforeTextChanged(CharSequence s, int start, int count, int after)
                                                                            {
                                                                                if (s.length() == 15)
                                                                                {
                                                                                    // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                                                                                    Toast.makeText(context, "Character Limit Reached ", Toast.LENGTH_SHORT).show();
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
                                                                                num3=s5;
                                                                                bottomSheetDialog.cancel();
                                                                            }
                                                                        });

                                                                        buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                num3 = editTextdailogpredefine.getText().toString();
                                                                                //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                                                                                if(TextUtils.isEmpty(num3)){
                                                                                    // Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                                else{
                                                                                    if (namecheck.contains(num3)) {
                                                                                        Toast.makeText(context, "Name Already Exist", Toast.LENGTH_SHORT).show();
                                                                                    } else {
                                                                                        holder.textViewInnerCategoryLight1.setText(num3);
                                                                                        // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                                                                                        if (favcheck.equals("true")) {
                                                                                            db2.child("name").setValue(num3);
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        } else {
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        });



                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            namecheck.add(s5);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("name").getValue(String.class);
                                                                        String favcheck = dataSnapshot.child("Favorite").getValue(String.class);

                                                                        //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();
                                                                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                                                                        View parentView = LayoutInflater.from(context).inflate(R.layout.dailoguebox_predefine, null);
                                                                        //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                                                                        ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                                                                        Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                                                                        EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                                                                        bottomSheetDialog.setContentView(parentView);
                                                                        // Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                                                                        bottomSheetDialog.show();
                                                                        editTextdailogpredefine.addTextChangedListener(new TextWatcher()
                                                                        {
                                                                            @Override
                                                                            public void beforeTextChanged(CharSequence s, int start, int count, int after)
                                                                            {
                                                                                if (s.length() == 15)
                                                                                {
                                                                                    // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                                                                                    Toast.makeText(context, "Character Limit Reached ", Toast.LENGTH_SHORT).show();
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
                                                                                num3=s5;
                                                                                bottomSheetDialog.cancel();
                                                                            }
                                                                        });

                                                                        buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                num3 = editTextdailogpredefine.getText().toString();
                                                                                //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                                                                                if(TextUtils.isEmpty(num3)){
                                                                                    // Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                                else{
                                                                                    if (namecheck.contains(num3)) {
                                                                                        Toast.makeText(context, "Name Already Exist", Toast.LENGTH_SHORT).show();
                                                                                    } else {
                                                                                        holder.textViewInnerCategoryLight1.setText(num3);
                                                                                        // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                                                                                        if (favcheck.equals("true")) {
                                                                                            db2.child("name").setValue(num3);
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        } else {
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        });



                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            namecheck.add(s5);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("name").getValue(String.class);
                                                                        String favcheck = dataSnapshot.child("Favorite").getValue(String.class);

                                                                        //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();
                                                                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                                                                        View parentView = LayoutInflater.from(context).inflate(R.layout.dailoguebox_predefine, null);
                                                                        //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                                                                        ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                                                                        Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                                                                        EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                                                                        bottomSheetDialog.setContentView(parentView);
                                                                        // Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                                                                        bottomSheetDialog.show();
                                                                        editTextdailogpredefine.addTextChangedListener(new TextWatcher()
                                                                        {
                                                                            @Override
                                                                            public void beforeTextChanged(CharSequence s, int start, int count, int after)
                                                                            {
                                                                                if (s.length() == 15)
                                                                                {
                                                                                    // new AlertDialog.Builder(getContext()).setTitle("Character limit exceeded").setMessage("Input cannot exceed more than " + 15 + " characters.").setPositiveButton(android.R.string.ok, null).show();
                                                                                    Toast.makeText(context, "Character Limit Reached ", Toast.LENGTH_SHORT).show();
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
                                                                                num3=s5;
                                                                                bottomSheetDialog.cancel();
                                                                            }
                                                                        });

                                                                        buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                num3 = editTextdailogpredefine.getText().toString();
                                                                                //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                                                                                if(TextUtils.isEmpty(num3)){
                                                                                    // Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                                else{
                                                                                    if (namecheck.contains(num3)) {
                                                                                        Toast.makeText(context, "Name Already Exist", Toast.LENGTH_SHORT).show();
                                                                                    } else {
                                                                                        holder.textViewInnerCategoryLight1.setText(num3);
                                                                                        // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                                                                                        if (favcheck.equals("true")) {
                                                                                            db2.child("name").setValue(num3);
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        } else {
                                                                                            db.child("name").setValue(num3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                @Override
                                                                                                public void onSuccess(Void aVoid) {
                                                                                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                                }
                                                                                            });
                                                                                            bottomSheetDialog.cancel();
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        });



                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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


                ////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////


            }
        });


        holder.buttonOnCategory1Light1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = holder.textViewInnerCategoryLight1.getText().toString();
                ////////////////////////////////////////////////////////////////////////////////////
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
                                // Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("mode").getValue(String.class);
                                                                        //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                                                        if(num2.equals("on"))
                                                                        {
                                                                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });



                                                                        }
                                                                        else
                                                                        {
                                                                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powergreen);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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

                                    //  Toast.makeText(context,"inside Bathroom", Toast.LENGTH_SHORT).show();
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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("mode").getValue(String.class);
                                                                        //    Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                                                        if(num2.equals("on"))
                                                                        {
                                                                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });



                                                                        }
                                                                        else
                                                                        {
                                                                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powergreen);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("mode").getValue(String.class);
                                                                        //  Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                                                        if(num2.equals("on"))
                                                                        {
                                                                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });



                                                                        }
                                                                        else
                                                                        {
                                                                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powergreen);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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

                                                            String s5 = dataSnapshot.child("name").getValue(String.class);
                                                            String s6 = dataSnapshot.child("category").getValue(String.class);

                                                            if(s5.equals(text)) {
                                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(s1).child(s2).child(s3);
                                                                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("favorites").child(s1).child(s2).child(s3);
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        num2 = dataSnapshot.child("mode").getValue(String.class);
                                                                        // Toast.makeText(context,num1, Toast.LENGTH_SHORT).show();

                                                                        if(num2.equals("on"))
                                                                        {
                                                                            db.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powerred);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("off").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });



                                                                        }
                                                                        else
                                                                        {
                                                                            db.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
                                                                                    holder.buttonOnCategory1Light1.setBackgroundResource(R.drawable.powergreen);
                                                                                }
                                                                            });
                                                                            db2.child("mode").setValue("on").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                }
                                                                            });
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });


                                                            }
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


                ////////////////////////////////////////////////////////////////////////////////////
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* // open another activity on item click
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("image", personImages.get(position)); // put image data in Intent
                context.startActivity(intent); // start Intent*/



            }
        });

        //if not working displaying the popup


    }




    /*public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.name.setText((CharSequence) roomNames.get(position));
        holder.imageViewIconMyRoom.setImageResource((Integer)roomImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               *//* // open another activity on item click
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("image", personImages.get(position)); // put image data in Intent
                context.startActivity(intent); // start Intent*//*
            }
        });

    }*/


    public int getItemCount() {
        return categoryname3.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView textViewInnerCategoryLight1;
        Button buttonEditCategory1Light1,buttonOnCategory1Light1;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            textViewInnerCategoryLight1=(TextView)itemView.findViewById(R.id.textViewInnerCategoryLight1);
            buttonEditCategory1Light1=(Button)itemView.findViewById(R.id.buttonEditCategory1Light1);
            buttonOnCategory1Light1=(Button)itemView.findViewById(R.id.buttonOnCategory1Light1);
        }
    }

}