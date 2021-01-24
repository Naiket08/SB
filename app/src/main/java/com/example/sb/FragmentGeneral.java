package com.example.sb;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class FragmentGeneral extends Fragment {

    TextView textViewFavourite,textViewOtherTitle;
    Button buttonAddRooms,buttonCategory,buttonAboutUs,buttonContactUs,buttonBackGeneral,buttonHamburgerGeneral,buttonKillGeneral;
    DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general,container,false);

        drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);

        textViewFavourite = (TextView)view.findViewById(R.id.textViewFavourite);
        textViewOtherTitle = (TextView)view.findViewById(R.id.textViewOtherTitle);
        buttonAddRooms = (Button)view.findViewById(R.id.buttonAddRooms);
        buttonCategory = (Button)view.findViewById(R.id.buttonCategory);
        buttonAboutUs = (Button)view.findViewById(R.id.buttonAboutUs);
        buttonContactUs = (Button)view.findViewById(R.id.buttonContactUs);

        //AppBar Buttons
        buttonBackGeneral = (Button)view.findViewById(R.id.buttonBackGeneral);
        buttonHamburgerGeneral = (Button)view.findViewById(R.id.buttonHamburgerGeneral);
        buttonKillGeneral = (Button)view.findViewById(R.id.buttonKillGeneral);

        buttonBackGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentCategory();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentAboutUs();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        buttonContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new FragmentContactUs();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }
}
