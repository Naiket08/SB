package com.example.sb;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class CustomAdapterPredefine extends RecyclerView.Adapter<CustomAdapterPredefine.ViewHolder> {
    ArrayList itemnames,itemtypes;

    Context context;
    private FirebaseAuth mAuth;
    String userId;
    String status="false";
    public String Roomname,text1,s2,s3,s5,s6,s9;
    public String[] s4=new String[10];
    public int o=1,p=1,m=1;
    EditText editTextdailogpredefine;
    public PopupMenu popup;
    DatabaseReference db,db2,db3;
    public ArrayList<String>  switchnamecheck = new ArrayList<String>();

    public CustomAdapterPredefine(Context context, ArrayList itemnames,ArrayList itemtypes,String Roomname,String text1,FirebaseAuth mAuth) {


        this.context = context;
        this.itemnames = itemnames;
        this.itemtypes=itemtypes;
        this.Roomname=Roomname;
        this.text1=text1;
        this.mAuth=mAuth;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_layout_predefine, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder



        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.edittextmain.setText((CharSequence) itemnames.get(position));
        holder.edittexthint.setText((CharSequence) itemnames.get(position));
        holder. imageviewmain1.setImageResource((Integer)itemtypes.get(position));
        // implement setOnClickListener event on item view.
        String ss=holder.edittexthint.getText().toString();
        db =  FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1).child(ss);
        if(ss.contains("Light"))
        {
            Map<String, Object> user = new HashMap<>();
            String l = mAuth.getCurrentUser().getUid();
            String q=String.valueOf(o);
            user.put("UID",l);
            user.put("name",ss);
            user.put("mode","off");
            user.put("number",q);
            user.put("category","Light");
            user.put("Favorite","false");
            db.setValue(user);
            o++;
        }
        else
            if(ss.contains("Fan"))
            {
                Map<String, Object> user1 = new HashMap<>();
                String l1 = mAuth.getCurrentUser().getUid();
                String q1=String.valueOf(p);
                user1.put("UID",l1);
                user1.put("name",ss);
                user1.put("mode","off");
                user1.put("number",q1);
                user1.put("category","Fan");
                user1.put("Favorite","false");
                user1.put("speed","0");
                db.setValue(user1);
                p++;

            }



        ////Demo
        holder.buttonmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseevent();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailogue_predefine, null);
                //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
                s5=holder.edittextmain.getText().toString();
                s9=holder.edittexthint.getText().toString();
               // Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                db3 =  FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1).child(s9);
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
                        s5=ss;
                    //    Toast.makeText(context,s3, Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.cancel();
                    }
                });

                buttondailogprtedefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.edittextmain.setEnabled(true);
                        s3 = editTextdailogpredefine.getText().toString();
                        holder.edittextmain.setEnabled(false);
                        //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                        if(TextUtils.isEmpty(s3)){
                            Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(switchnamecheck.contains(s3)){
                                Toast.makeText(context, "Name Already Exist", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                holder.edittextmain.setText(s3);
                                s5 = s3;
                                Toast.makeText(context, "Name Changed to : " + s5, Toast.LENGTH_SHORT).show();
                                // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                                db3.child("name").setValue(s5);
                                bottomSheetDialog.cancel();
                            }
                        }
                    }
                });


            }

        });
                holder.buttonmain2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        s2=holder.edittexthint.getText().toString();
                        s6=holder.edittextmain.getText().toString();
                        //Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                        db2 =  FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1).child(s2);
                        popup = new PopupMenu(context, holder.buttonmain2);
                        popup.inflate(R.menu.menu_predefine);
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.lightpredefine:
                                        //handle menu1 click
                                        Map<String, Object> user = new HashMap<>();
                                        String l = mAuth.getCurrentUser().getUid();
                                        String q=s2.replaceAll("[^0-9]", "");;
                                        Toast.makeText(context,q, Toast.LENGTH_SHORT).show();
                                        user.put("UID",l);
                                        user.put("name",s6);
                                        user.put("mode","off");
                                        user.put("number",q);
                                        user.put("category","Light");
                                        user.put("Favorite","false");
                                        holder.imageviewmain1.setImageResource(R.drawable.idea);
                                        db2.setValue(user);
                                        break;
                                    case R.id.fanpredefine:
                                        //handle menu2 click
                                        Map<String, Object> user1 = new HashMap<>();
                                        String l1 = mAuth.getCurrentUser().getUid();
                                        String q2=s2.replaceAll("[^0-9]", "");;
                                        user1.put("UID",l1);
                                        user1.put("name",s6);
                                        user1.put("mode","off");
                                        user1.put("number",q2);
                                        user1.put("category","Fan");
                                        user1.put("Favorite","false");
                                        user1.put("speed","0");
                                        holder.imageviewmain1.setImageResource(R.drawable.fan_icon);
                                        db2.setValue(user1);
                                        break;
                                    case R.id.appliancePredefine:
                                        //handle menu3 click
                                        //handle menu2 click
                                        Map<String, Object> user3 = new HashMap<>();
                                        String l3 = mAuth.getCurrentUser().getUid();
                                        String q3=s2.replaceAll("[^0-9]", "");;
                                        user3.put("UID",l3);
                                        user3.put("name",s6);
                                        user3.put("mode","off");
                                        user3.put("number",q3);
                                        user3.put("category","Appliance");
                                        user3.put("Favorite","false");
                                        holder.imageviewmain1.setImageResource(R.drawable.appliances_icon);
                                        db2.setValue(user3);
                                        m++;
                                        break;
                                    case R.id.Airconditioner:
                                        //handle menu2 click
                                        Map<String, Object> user4 = new HashMap<>();
                                        String l4 = mAuth.getCurrentUser().getUid();
                                        String q4=s2.replaceAll("[^0-9]", "");;
                                        user4.put("UID",l4);
                                        user4.put("name",s6);
                                        user4.put("mode","off");
                                        user4.put("number",q4);
                                        user4.put("category","AC");
                                        user4.put("Favorite","false");
                                        user4.put("Temperature","24");
                                        holder.imageviewmain1.setImageResource(R.drawable.airconditioner);
                                        db2.setValue(user4);
                                        break;
                                    case R.id.Unselect:
                                        holder.imageviewmain1.setImageResource(R.drawable.ideaicon);
                                        db2.removeValue();
                                        break;
                                }
                                return false;
                            }
                        });
                        popup.show();

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
        return itemnames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        EditText edittextmain,edittexthint;
        Button buttonmain, buttonmain2;
        ImageView imageviewmain1;

        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            edittextmain = (EditText) itemView.findViewById(R.id.edittextmain);
            edittexthint=(EditText)itemView.findViewById(R.id.edittexthint);
            buttonmain = (Button) itemView.findViewById(R.id.buttonmain);
            buttonmain2 = (Button) itemView.findViewById(R.id.buttonmain2);
            imageviewmain1=(ImageView)itemView.findViewById(R.id.imageviewmain1);

        }
    }

    public void databaseevent(){

                                DatabaseReference itemsRef4 = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1);
                                itemsRef4.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                                        String sn3 = dataSnapshot.getKey();



                                        if(sn3.equals("SwitchBoardumber")||sn3.equals("combination")||sn3.equals("type")||sn3.equals("")){

                                        }
                                        else
                                        {
                                            String sn5 = dataSnapshot.child("name").getValue(String.class);

                                          //  Toast.makeText(context, sn5, Toast.LENGTH_SHORT).show();

                                            switchnamecheck.add(sn5);

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
