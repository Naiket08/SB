package com.example.sb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FragmentRoomInner extends Fragment{

    ImageView imageViewRoomInnerLights,imageViewRoomInnerEditSB,imageViewRoomInnerInfo;
    TextView textViewControlSB,textViewRoomInnerRoomNo,textViewRoomInnerSB1,textViewRoomInnerSBType;
    ////////////////////////////////////
    RecyclerView recyclerViewRoominner;
    ////////////////////////
    public ArrayList<String> SwitchName = new ArrayList<String>();
    public ArrayList<Integer> SwitchType = new ArrayList<Integer>();
    //////////////////////
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();;
    String userId;
    String status="false";
    public int i=0;
    public String Stext,Stype;
    ///////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_inner,container,false);



        imageViewRoomInnerInfo = (ImageView)view.findViewById(R.id.imageViewRoomInnerInfo);
        imageViewRoomInnerLights = (ImageView)view.findViewById(R.id.imageViewRoomInnerLights);

        textViewControlSB = (TextView)view.findViewById(R.id.textViewControlSB);
        textViewRoomInnerRoomNo = (TextView)view.findViewById(R.id.textViewRoomInnerRoomNo);
        textViewRoomInnerSB1 = (TextView)view.findViewById(R.id.textViewRoomInnerSB1);
        textViewRoomInnerSBType = (TextView)view.findViewById(R.id.textViewRoomInnerSBType);

        if((SwitchName!=null&&SwitchType!=null&&SwitchName.size()>0&&SwitchType.size()>0)){
            SwitchName.clear();
            SwitchType.clear();
        }

        //////////////////////////////////////////////////
        //firecloud
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        db1.collection("users").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String zz = documentSnapshot.getString("Renumbers");
                        i=Integer.parseInt(zz);
                    }
                });

        db1.collection("users").document(userId).collection("Rooms").document("RoomName").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        for(int j=0;j<i;j++) {
                            String txt = documentSnapshot.getString("Room"+j);
                            Stext=txt;
                            SwitchName.add(Stext);
                            //Toast.makeText(getContext(),rtext, Toast.LENGTH_SHORT).show();
                            String ig = documentSnapshot.getString("RoomType"+j);
                            Stype=ig;
                            // Toast.makeText(getContext(),rimg, Toast.LENGTH_SHORT).show();
                            if(Stype.equals("1")){
                                //SwitchType.add();
                                // Toast.makeText(getContext(),"Entered inside", Toast.LENGTH_SHORT).show();
                            }
                            else  if(Stype.equals("2")){
                               // SwitchType.add();
                            }
                            else  if(Stype.equals("3")){
                               // SwitchType.add();
                            }
                            else  if(Stype.equals("4")){
                               // SwitchType.add();
                            }

                        }
                    }
                });


        //////////////////////////////////////////////////////

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Second fragment after 5 seconds appears
                recyclerViewRoominner = (RecyclerView)view.findViewById(R.id.recyclerViewRoomInner);
                mAuth = FirebaseAuth.getInstance();

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),0);
                recyclerViewRoominner.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                CustomAdapterRoomInner customAdapter1 = new CustomAdapterRoomInner(getActivity(),SwitchName,SwitchType);
                recyclerViewRoominner.setAdapter(customAdapter1); // set the Adapter to RecyclerView
                getFragmentManager().beginTransaction().commit();
            }
        };
        handler.postDelayed(runnable, 1000);

        return view;
    }
}
