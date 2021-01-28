package com.example.sb;

import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentPredefine extends Fragment {

   public EditText predefineRoomedit;
    Button predefinebuttonedit,buttonmain2;
    RecyclerView recyclerViewPredefine;
    ArrayList itemnames= new ArrayList<>(Arrays.asList("Light 1", "Light 2", "Light 3", "Light 4", "Fan 1"));
    LinearLayout layout1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_predefine, container, false);

        predefineRoomedit =(EditText)v.findViewById(R.id.predefineRoomedit);
        predefinebuttonedit=(Button)v.findViewById(R.id.predefinebuttonedit);
        buttonmain2=(Button)v.findViewById(R.id.buttonmain2);
        recyclerViewPredefine = (RecyclerView)v.findViewById(R.id.recyclerViewPredefine);
        layout1=(LinearLayout)v.findViewById(R.id.ll1);

        layout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                predefineRoomedit.setEnabled(false);
                predefineRoomedit.requestFocus();
                predefineRoomedit.setCursorVisible(false);
                return false;
            }
        });

        predefinebuttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
                predefineRoomedit.setEnabled(true);
                predefineRoomedit.setCursorVisible(true);
                predefineRoomedit.requestFocus();
                predefineRoomedit.setCursorVisible(true);
                //predefineRoomedit.setSelection(0);
                predefineRoomedit.setImeOptions(EditorInfo.IME_ACTION_DONE);


            }
        });


       /*predefineRoomedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    predefineRoomedit.setEnabled(false);
                    predefineRoomedit.requestFocus();
                    predefineRoomedit.setCursorVisible(false);
                }


            }
        });*/


        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(),1);
        recyclerViewPredefine.setLayoutManager(gridLayoutManager1); // set LayoutManager to RecyclerView
      /*  final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPredefine.setLayoutManager(layoutManager); // set LayoutManager to RecyclerView*/
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapterPredefine customAdapterPredefine = new CustomAdapterPredefine(getActivity(),itemnames);
        recyclerViewPredefine.setAdapter(customAdapterPredefine); // set the Adapter to RecyclerView

        return v;
    }

}
