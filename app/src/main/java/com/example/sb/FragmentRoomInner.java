package com.example.sb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRoomInner extends Fragment{

    ImageView imageViewRoomInnerLights,imageViewRoomInnerEditSB,imageViewRoomInnerInfo,imageViewRoomInnerEditSB2,
            imageViewRoomInnerInfo2,imageViewRoomInnerEditSB3,imageViewRoomInnerInfo3,imageViewRoomInnerAddSB;
    TextView textViewControlSB,textViewRoomInnerRoomNo,textViewRoomInnerSB1,textViewRoomInnerSBType,textViewRoomInnerSB2,
            textViewRoomInnerSBType2,textViewRoomInnerSB3,textViewRoomInnerSBType3,textViewRoomInnerAddNewSB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_inner,container,false);

        imageViewRoomInnerAddSB = (ImageView)view.findViewById(R.id.imageViewRoomInnerAddSB);
        imageViewRoomInnerEditSB = (ImageView)view.findViewById(R.id.imageViewRoomInnerEditSB);
        imageViewRoomInnerEditSB2 = (ImageView)view.findViewById(R.id.imageViewRoomInnerEditSB2);
        imageViewRoomInnerEditSB3 = (ImageView)view.findViewById(R.id.imageViewRoomInnerEditSB3);
        imageViewRoomInnerInfo = (ImageView)view.findViewById(R.id.imageViewRoomInnerInfo);
        imageViewRoomInnerInfo2 = (ImageView)view.findViewById(R.id.imageViewRoomInnerInfo2);
        imageViewRoomInnerInfo3 = (ImageView)view.findViewById(R.id.imageViewRoomInnerInfo3);
        imageViewRoomInnerLights = (ImageView)view.findViewById(R.id.imageViewRoomInnerLights);

        textViewControlSB = (TextView)view.findViewById(R.id.textViewControlSB);
        textViewRoomInnerAddNewSB = (TextView)view.findViewById(R.id.textViewRoomInnerAddNewSB);
        textViewRoomInnerRoomNo = (TextView)view.findViewById(R.id.textViewRoomInnerRoomNo);
        textViewRoomInnerSB1 = (TextView)view.findViewById(R.id.textViewRoomInnerSB1);
        textViewRoomInnerSB2 = (TextView)view.findViewById(R.id.textViewRoomInnerSB2);
        textViewRoomInnerSB3 = (TextView)view.findViewById(R.id.textViewRoomInnerSB3);
        textViewRoomInnerSBType = (TextView)view.findViewById(R.id.textViewRoomInnerSBType);
        textViewRoomInnerSBType2 = (TextView)view.findViewById(R.id.textViewRoomInnerSBType2);
        textViewRoomInnerSBType3 = (TextView)view.findViewById(R.id.textViewRoomInnerSBType3);

        return view;
    }
}
