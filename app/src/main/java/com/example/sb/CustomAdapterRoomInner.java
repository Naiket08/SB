package com.example.sb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class CustomAdapterRoomInner extends RecyclerView.Adapter<CustomAdapterRoomInner.ViewHolder> {
    ArrayList SwitchName;
    ArrayList SwitchType;
    Context context;
    public CustomAdapterRoomInner(Context context, ArrayList SwitchName, ArrayList SwitchType) {


        this.context = context;
        this.SwitchName = SwitchName;
        this.SwitchType = SwitchType;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_roominner, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewRoomInnerSB1.setText((CharSequence) SwitchName.get(position));
        holder.textViewRoomInnerSBType.setText((CharSequence) SwitchType.get(position));
        holder.imageViewRoomInnerEditSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View parentView = LayoutInflater.from(context).inflate(R.layout.dailogue_predefine, null);
                ImageView canceldailogpredefine = (ImageView) parentView.findViewById(R.id.canceldailogpredefine);
                Button buttondailogprtedefine = (Button) parentView.findViewById(R.id.buttondailogpredefine);
                EditText editTextdailogpredefine = (EditText) parentView.findViewById(R.id.editTextdailogpredefine);
                bottomSheetDialog.setContentView(parentView);
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
                        holder.textViewRoomInnerSB1.setEnabled(true);
                        String s2 = editTextdailogpredefine.getText().toString();
                        holder.textViewRoomInnerSB1.setText(s2);
                        holder.textViewRoomInnerSB1.setEnabled(false);
                        bottomSheetDialog.cancel();

                    }
                });


            }
        });

    }


    @Override
    public int getItemCount() {
        return SwitchName.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
       ImageView imageViewRoomInnerInfo,imageViewRoomInnerEditSB;
       TextView textViewRoomInnerSBType;
        EditText textViewRoomInnerSB1;
       ImageButton roominnerbutton1;
        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            imageViewRoomInnerInfo=(ImageView)itemView.findViewById(R.id.imageViewRoomInnerInfo);
            imageViewRoomInnerEditSB=(ImageView)itemView.findViewById(R.id.imageViewRoomInnerEditSB);
            textViewRoomInnerSB1=(EditText) itemView.findViewById(R.id.textViewRoomInnerSB1);
            textViewRoomInnerSBType=(TextView)itemView.findViewById(R.id.textViewRoomInnerSBType);
            roominnerbutton1=(ImageButton)itemView.findViewById(R.id.roominnerbutton1);



        }
    }
}
