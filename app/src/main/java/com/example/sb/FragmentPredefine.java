package com.example.sb;

import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPredefine extends Fragment {

    EditText predefineRoomedit;
    Button predefinebuttonedit,buttonmain2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_predefine, container, false);

        predefineRoomedit =(EditText)v.findViewById(R.id.predefineRoomedit);
        predefinebuttonedit=(Button)v.findViewById(R.id.predefinebuttonedit);
        buttonmain2=(Button)v.findViewById(R.id.buttonmain2);



        predefinebuttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }
}
