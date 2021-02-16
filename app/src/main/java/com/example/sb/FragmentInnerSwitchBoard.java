package com.example.sb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FragmentInnerSwitchBoard extends Fragment {

    TextView textViewRoomNo;
    ImageView imageViewBrwonJacket,imageViewWhiteJacket,imageViewRegulator,imageViewAppliances,imageViewBulb;

    TextView textViewSwitchBoard1;
    Button buttonLight1,buttonLight2,buttonFan,buttonLight3,buttonSwitchBoardApppliance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_switchboard,container,false);

        textViewRoomNo = (TextView)view.findViewById(R.id.textViewRoomNo);
        imageViewBrwonJacket = (ImageView)view.findViewById(R.id.imageViewBrownJacket);
        imageViewWhiteJacket = (ImageView)view.findViewById(R.id.imageViewWhiteJacket);
        imageViewBulb = (ImageView)view.findViewById(R.id.imageViewBulb);
        imageViewRegulator = (ImageView)view.findViewById(R.id.imageViewRegulator);
        imageViewAppliances = (ImageView)view.findViewById(R.id.imageViewAppliances);

        textViewSwitchBoard1 = (TextView)view.findViewById(R.id.textViewSwitchBoard1);
        buttonLight1 = (Button)view.findViewById(R.id.buttonLight1);
        buttonLight2 = (Button)view.findViewById(R.id.buttonLight2);
        buttonLight3 = (Button)view.findViewById(R.id.buttonLight3);
        buttonFan = (Button)view.findViewById(R.id.buttonFan);
        buttonSwitchBoardApppliance = (Button)view.findViewById(R.id.buttonSwitchBoardAppliance);


        buttonLight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.VISIBLE);
                imageViewBulb.setVisibility(View.VISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        buttonLight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.VISIBLE);
                imageViewBulb.setVisibility(View.VISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        buttonLight3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.VISIBLE);
                imageViewBulb.setVisibility(View.VISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        buttonFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.INVISIBLE);
                imageViewBulb.setVisibility(View.INVISIBLE);
                imageViewAppliances.setVisibility(View.INVISIBLE);
                imageViewRegulator.setVisibility(View.VISIBLE);
                imageViewWhiteJacket.setVisibility(View.VISIBLE);
            }
        });

        buttonSwitchBoardApppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewBrwonJacket.setVisibility(View.INVISIBLE);
                imageViewBulb.setVisibility(View.INVISIBLE);
                imageViewAppliances.setVisibility(View.VISIBLE);
                imageViewRegulator.setVisibility(View.INVISIBLE);
                imageViewWhiteJacket.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}
