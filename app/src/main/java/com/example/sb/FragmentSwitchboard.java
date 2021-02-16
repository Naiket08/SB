package com.example.sb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.sb.R.id.content;
import static com.example.sb.R.id.imageViewswitchboard;
import static com.example.sb.R.id.info;

public class FragmentSwitchboard extends DialogFragment {

    ImageButton switchinfo1, switchinfo2, switchinfo3, switchinfo4, switch1, switch2, switch3, switch4;
    public int i;
    ////////////////////////////////
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    public int o,j,f;
    private String k,txt,demo1,demo2;
    /////////////////////////////////////

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
/////////////////////
        mAuth = FirebaseAuth.getInstance();





//////////////////////////////////////

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
                f=1;
                switchoptionaldailgue();
            }
        });
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=2;
                switchoptionaldailgue();
            }
        });
        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=3;
                switchoptionaldailgue();
            }
        });
        switch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=4;
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
        ImageView cancelab2 = (ImageView) parentView.findViewById(R.id.cancelswitchdailog);
        Button buttonswitchdailog2=(Button)parentView.findViewById(R.id.buttonswitchdailog);
        EditText editTextswitchdailog2=(EditText)parentView.findViewById(R.id.editTextswitchdailog);

        bottomSheetDialog.setContentView(parentView);

        bottomSheetDialog.show();

        cancelab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
        userId = mAuth.getCurrentUser().getUid();
        // DocumentReference documentReference = db1.collection("users").document(userId);

        db1.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String z1 = documentSnapshot.getString("Renumbers");
                        demo1=z1.toString();
                        o=Integer.valueOf(demo1);
                        Toast.makeText(getContext(),"value1 : "+ o , Toast.LENGTH_SHORT).show();
                    }
                });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ////////////////////////////////
                db1.collection("users").document(mAuth.getCurrentUser().getUid()).collection("Rooms").document(""+o).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String z2 = documentSnapshot.getString("switchcounter");
                                demo2=z2.toString();
                                j=Integer.valueOf(demo2);
                                Toast.makeText(getContext(),"value2 : "+ j , Toast.LENGTH_SHORT).show();
                            }
                        });
                ////////////////////////////////

            }
        };
        handler.postDelayed(runnable, 500);






               buttonswitchdailog2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(f==1) {
                            /////
                            String text1 = editTextswitchdailog2.getText().toString().trim();
                            Map<String, Object> user = new HashMap<>();
                            user.put("KeyName" + j, text1);
                            user.put("Switchname" + j, "Switch board 1");
                            user.put("E1", "Light 1");
                            user.put("E2", "Light 2");
                            user.put("E3", "Light 3");
                            user.put("E4", "Light 4");
                            user.put("E5", "Fan 1");
                            Map<String, Object> user2 = new HashMap<>();
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).collection("Switch" + j).document("switch").set(user, SetOptions.merge());
                            k = String.valueOf(++j);
                            user2.put("switchcounter", k);
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).set(user2, SetOptions.merge());
                            /////
                        }
                        else if(f==2){
                            String text1 = editTextswitchdailog2.getText().toString().trim();
                            Map<String, Object> user = new HashMap<>();
                            user.put("KeyName" + j, text1);
                            user.put("Switchname" + j, "Switch board 2");
                            user.put("E1", "Light 1");
                            user.put("E2", "Light 2");
                            user.put("E3", "Light 3");
                            user.put("E4", "Light 4");
                            user.put("E5", "Fan 1");
                            user.put("E6", "Fan 2");
                            Map<String, Object> user2 = new HashMap<>();
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).collection("Switch" + j).document("switch").set(user, SetOptions.merge());
                            k = String.valueOf(++j);
                            user2.put("switchcounter", k);
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).set(user2, SetOptions.merge());
                            /////

                        } else if(f==3){
                            String text1 = editTextswitchdailog2.getText().toString().trim();
                            Map<String, Object> user = new HashMap<>();
                            user.put("KeyName" + j, text1);
                            user.put("Switchname" + j, "Switch board 3");
                            user.put("E1", "Light 1");
                            user.put("E2", "Light 2");
                            user.put("E3", "Fan 1");
                            Map<String, Object> user2 = new HashMap<>();
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).collection("Switch" + j).document("switch").set(user, SetOptions.merge());
                            k = String.valueOf(++j);
                            user2.put("switchcounter", k);
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).set(user2, SetOptions.merge());
                            /////
                        } else if(f==4){
                            String text1 = editTextswitchdailog2.getText().toString().trim();
                            Map<String, Object> user = new HashMap<>();
                            user.put("KeyName" + j, text1);
                            user.put("Switchname" + j, "Switch board 4");
                            /*user.put("E1", "Light 1");
                            user.put("E2", "Light 2");
                            user.put("E3", "Light 3");
                            user.put("E4", "Light 4");
                            user.put("E5", "Fan 1");*/
                            Map<String, Object> user2 = new HashMap<>();
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).collection("Switch" + j).document("switch").set(user, SetOptions.merge());
                            k = String.valueOf(++j);
                            user2.put("switchcounter", k);
                            db1.collection("users").document(userId).collection("Rooms").document("" + o).set(user2, SetOptions.merge());
                            /////

                        }
                        Fragment newFragment = new FragmentPredefine();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container,newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        bottomSheetDialog.cancel();

                    }
                });





    }


}
