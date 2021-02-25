package com.example.sb;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomAdapterInnerCategory extends RecyclerView.Adapter<CustomAdapterInnerCategory.ViewHolder> {
    ArrayList categoryname;

    Context context;
    private FirebaseAuth mAuth;
    String userId;
    String status="false";
    DatabaseReference db;


    public CustomAdapterInnerCategory(Context context, ArrayList categoryname,FirebaseAuth mAuth) {


        this.context = context;
        this.categoryname=categoryname;
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
        holder.textViewInnerCategoryLight1.setText((CharSequence) categoryname.get(position));
        // implement setOnClickListener event on item view.

        ////Demo

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
        return categoryname.size();
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
