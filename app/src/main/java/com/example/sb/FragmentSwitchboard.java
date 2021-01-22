package com.example.sb;

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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import static com.example.sb.R.id.imageViewswitchboard;
import static com.example.sb.R.id.info;

public class FragmentSwitchboard extends DialogFragment {

    ImageButton switchinfo1, switchinfo2, switchinfo3, switchinfo4, switch1, switch2, switch3, switch4;
    public int i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_switch_board, container, false);

        switchinfo1 = (ImageButton) v.findViewById(R.id.switchinfo1);
        switchinfo2 = (ImageButton) v.findViewById(R.id.switchinfo2);
        switchinfo3 = (ImageButton) v.findViewById(R.id.switchinfo3);
        switchinfo4 = (ImageButton) v.findViewById(R.id.switchinfo4);
        switch1 = (ImageButton) v.findViewById(R.id.switch1);
        switch2 = (ImageButton) v.findViewById(R.id.switch2);
        switch3 = (ImageButton) v.findViewById(R.id.switch3);
        switch4 = (ImageButton) v.findViewById(R.id.switch4);

        switchinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
            switchboardinfo();
            }
        });

        switchinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=2;
                switchboardinfo();
            }
        });
        switchinfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=3;
                switchboardinfo();
            }
        });
        switchinfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=4;
                switchboardinfo();
            }
        });

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchoptionaldailgue();
            }
        });
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchoptionaldailgue();
            }
        });
        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchoptionaldailgue();
            }
        });
        switch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchoptionaldailgue();
            }
        });

        return v;
    }

    public void switchboardinfo() {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.switchboardinfo, null);
        ImageView cancelab = (ImageView) parentView.findViewById(R.id.cancelswitchboardinfo);
        TextView  info1 =(TextView)parentView.findViewById(R.id.textviewswitchboardinfo);
        TextView  info2 =(TextView)parentView.findViewById(R.id.textviewswitchboardinfo2);
        TextView  info3 =(TextView)parentView.findViewById(R.id.ideatextswitchboardinfo);
        TextView  info4 =(TextView)parentView.findViewById(R.id.ideatextswitchboardinfo2);
        if(i==1) {

            info1.setText("Switch Board 1");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("4 Lights");
            info4.setText("1 Fan");

        }
        else if(i==2)
        {
            info1.setText("Switch Board 2");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("4 Lights");
            info4.setText("2 Fans");


        }
        else if(i==3)
        {

            info1.setText("Switch Board 3");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText(" 2 Lights");
            info4.setText(" 1 Fan");

        }
        else if(i==4)
        {

            info1.setText("Custom");
            info2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, pulvinar facilisis justo mollis, auctor consequat urna.");
            info3.setText("  Lights");
            info4.setText("  Fan");
        }

        bottomSheetDialog.setContentView(parentView);
        bottomSheetDialog.show();

        cancelab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
    }

    public void switchoptionaldailgue() {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View parentView = getLayoutInflater().inflate(R.layout.switchoptiondailoge, null);
        ImageView cancelab = (ImageView) parentView.findViewById(R.id.cancelswitchdailog);


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
