package com.example.sb;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class CustomAdapterPredefine extends RecyclerView.Adapter<CustomAdapterPredefine.ViewHolder> {
    ArrayList itemnames;

    Context context;
    private FirebaseAuth mAuth;
    String userId;
    String status="false";
    public String Roomname,text1,s2,s3;
    public String[] s4=new String[10];
    public int o=1,p=1,m=1;
    EditText editTextdailogpredefine;
    public PopupMenu popup;
    DatabaseReference db;


    public CustomAdapterPredefine(Context context, ArrayList itemnames,String Roomname,String text1,FirebaseAuth mAuth) {


        this.context = context;
        this.itemnames = itemnames;
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
        // implement setOnClickListener event on item view.

        ////Demo
        holder.buttonmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailogue_predefine, null);
                //View parentView = getLayoutInflater().inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
                s2=holder.edittextmain.getText().toString();
               // Toast.makeText(context,s2, Toast.LENGTH_SHORT).show();
                db =  FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("rooms").child(Roomname).child(text1).child(s2);
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
                        holder.edittextmain.setEnabled(true);
                        s3 = editTextdailogpredefine.getText().toString();
                        holder.edittextmain.setEnabled(false);
                        //  Toast.makeText(context, s3, Toast.LENGTH_SHORT).show();
                        if(TextUtils.isEmpty(s3)){
                            // Toast.makeText(context, "Enter Text", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            holder.edittextmain.setText(s3);
                            s2=s3;
                            // Toast.makeText(context, "new s2"+s2, Toast.LENGTH_SHORT).show();
                            db.setValue(s2);
                            bottomSheetDialog.cancel();
                        }
                    }
                });
                holder.buttonmain2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.show();

                    }
                });
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
                                String q=String.valueOf(o);
                                user.put("UID",l);
                                user.put("name",s2);
                                user.put("mode","on");
                                user.put("number",q);
                                user.put("category","Light");
                                holder.imageviewmain1.setImageResource(R.drawable.idea);
                                db.setValue(user);
                                o++;
                                break;
                            case R.id.fanpredefine:
                                //handle menu2 click
                                Map<String, Object> user1 = new HashMap<>();
                                String l1 = mAuth.getCurrentUser().getUid();
                                String q1=String.valueOf(p);
                                user1.put("UID",l1);
                                user1.put("name",s2);
                                user1.put("mode","on");
                                user1.put("number",q1);
                                user1.put("category","Fan");
                                holder.imageviewmain1.setImageResource(R.drawable.fan_icon);
                                db.setValue(user1);
                                p++;
                                break;
                            case R.id.appliancePredefine:
                                //handle menu3 click
                                //handle menu2 click
                                Map<String, Object> user3 = new HashMap<>();
                                String l3 = mAuth.getCurrentUser().getUid();
                                String q3=String.valueOf(m);
                                user3.put("UID",l3);
                                user3.put("name",s2);
                                user3.put("mode","on");
                                user3.put("number",q3);
                                user3.put("category","Fan");
                                holder.imageviewmain1.setImageResource(R.drawable.appliances_icon);
                                db.setValue(user3);
                                m++;
                                break;
                        }
                        return false;
                    }
                });




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
        EditText edittextmain;
        Button buttonmain, buttonmain2;
        ImageView imageviewmain1;

        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            edittextmain = (EditText) itemView.findViewById(R.id.edittextmain);
            buttonmain = (Button) itemView.findViewById(R.id.buttonmain);
            buttonmain2 = (Button) itemView.findViewById(R.id.buttonmain2);
            imageviewmain1=(ImageView)itemView.findViewById(R.id.imageviewmain1);

        }
    }

}
