package com.example.sb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList roomNames;
    ArrayList roomImages;
    Context context;
    public CustomAdapter(Context context, ArrayList roomNames, ArrayList roomImages) {


        this.context = context;
        this.roomNames = roomNames;
        this.roomImages = roomImages;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_layout_my_rooms, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText((CharSequence) roomNames.get(position));
        holder.imageViewIconMyRoom.setImageResource((Integer)roomImages.get(position));
        // implement setOnClickListener event on item view.
        holder.imageViewRecycleViewMyRoomEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
                holder.name.setEnabled(true);
                holder.name.setCursorVisible(true);
                holder.name.requestFocus();
                holder.name.setCursorVisible(true);
                holder.name.setImeOptions(EditorInfo.IME_ACTION_DONE);

            }
        });
        holder.imageViewRecycleViewMyRoomEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    holder.name.setEnabled(false);
                    holder.name.requestFocus();
                    holder.name.setCursorVisible(false);
                    holder.name.setImeOptions(EditorInfo.IME_ACTION_DONE);
                }


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
        return roomNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        EditText name;
        ImageView imageViewRecycleViewMyRoomEdit,imageViewIconMyRoom;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (EditText) itemView.findViewById(R.id.EdittextRecycleViewMyRoom);
            imageViewRecycleViewMyRoomEdit = (ImageView)itemView.findViewById(R.id.imageViewRecycleViewMyRoomEdit);
            imageViewIconMyRoom = (ImageView)itemView.findViewById(R.id. imageViewIconMyRoom);
        }
    }
}
