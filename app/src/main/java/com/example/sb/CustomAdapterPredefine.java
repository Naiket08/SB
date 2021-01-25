package com.example.sb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapterPredefine extends RecyclerView.Adapter<CustomAdapterPredefine.ViewHolder> {
    ArrayList itemnames;

    Context context;
    public CustomAdapterPredefine(Context context, ArrayList itemnames) {


        this.context = context;
        this.itemnames = itemnames;

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
        holder.edittextmain.setText((CharSequence)itemnames.get(position));
        holder.buttonmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
                holder.edittextmain.setEnabled(true);
                holder.edittextmain.setCursorVisible(true);
                holder.edittextmain.requestFocus();
                holder.edittextmain.setCursorVisible(true);
                //predefineRoomedit.setSelection(0);
            }
        });

        holder.edittextmain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    holder.edittextmain.setEnabled(false);
                    holder.edittextmain.requestFocus();
                    holder.edittextmain.setCursorVisible(false);
                }


            }
        });
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* // open another activity on item click
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("image", personImages.get(position)); // put image data in Intent
                context.startActivity(intent); // start Intent*/
            }
        });

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
        Button buttonmain;

        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            edittextmain = (EditText) itemView.findViewById(R.id.edittextmain);
            buttonmain =(Button)itemView.findViewById(R.id.buttonmain);

        }
    }
}
