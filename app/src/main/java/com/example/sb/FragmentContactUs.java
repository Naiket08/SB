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

public class FragmentContactUs extends Fragment {
    ImageView imageViewWaveTopContactUs,imageViewCallIcon1,imageViewCallIcon2,imageViewEmail;
    TextView textViewContactUs,textViewContactUs1,textViewContactUs2,textViewContactUs3,textViewContactUs4,
            textViewPhoneNo1,textViewPhoneNo2,textViewEmailID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us,container,false);

        imageViewWaveTopContactUs = (ImageView)view.findViewById(R.id.imageViewWaveTopContactUs);
        imageViewCallIcon1 = (ImageView)view.findViewById(R.id.imageViewCallIcon1);
        imageViewCallIcon2 = (ImageView)view.findViewById(R.id.imageViewCallIcon2);
        imageViewEmail = (ImageView)view.findViewById(R.id.imageViewEmail);

        return view;
    }
}
