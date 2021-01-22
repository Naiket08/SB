package com.example.sb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.sb.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class FragmentRoomsSecond  extends DialogFragment {
    Button bedroom,bathroom,kitchen,dinningroom,custom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_rooms_second,container,false);


        bedroom=(Button)v.findViewById(R.id.bedroom);
        bathroom=(Button)v.findViewById(R.id.bathroom);
        kitchen=(Button)v.findViewById(R.id.kitchen);
        dinningroom=(Button)v.findViewById(R.id.dinningroom);
        custom=(Button)v.findViewById(R.id.custom);

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showAlert();

            }
        });
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        dinningroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });





        return v;



    }
    public void showAlert()
    {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.addingroombox,null);
        EditText edittextab=(EditText)parentView.findViewById(R.id.editTextab);
        Button nextbutton=(Button)parentView.findViewById(R.id.buttonab);
        ImageView cancelab=(ImageView)parentView.findViewById(R.id.cancelab);



        bottomSheetDialog.setContentView(parentView);
        bottomSheetDialog.show();

        cancelab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });


    }
}
