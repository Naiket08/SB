package com.example.sb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentCategory extends Fragment {

    TextView textViewCategory,textViewAll;
    Button buttonLights,buttonFans,buttonAppliances;
    public String ab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);

        textViewCategory = (TextView)view.findViewById(R.id.textViewCategory);
        textViewAll = (TextView)view.findViewById(R.id.textViewAll);
        buttonLights = (Button)view.findViewById(R.id.buttonLights);
        buttonFans = (Button)view.findViewById(R.id.buttonFans);
        buttonAppliances = (Button)view.findViewById(R.id.buttonAppliances);

        buttonLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ab="Light";
                Fragment newFragment = new FragmentInnerCategoryLights();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle arguments = new Bundle();
                arguments.putString("category",ab);
                newFragment.setArguments(arguments);
                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        buttonFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ab="Fan";
                Fragment newFragment = new FragmentInnerCategoryLights();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle arguments = new Bundle();
                arguments.putString("category",ab);
                newFragment.setArguments(arguments);

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        buttonAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ab="Appliance";
                Fragment newFragment = new FragmentInnerCategoryLights();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle arguments = new Bundle();
                arguments.putString("category",ab);
                newFragment.setArguments(arguments);

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }
}
