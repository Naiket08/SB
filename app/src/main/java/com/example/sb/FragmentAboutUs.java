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

public class FragmentAboutUs extends Fragment {

    ImageView imageViewWaveTopAboutUs;
    TextView textViewAboutUs,textViewAboutUs1,textViewAboutUs2,textViewAboutUs3,textViewAboutUs4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_about_us,container,false);

        imageViewWaveTopAboutUs = (ImageView)view.findViewById(R.id.imageViewWaveTopAboutUs);
        textViewAboutUs = (TextView)view.findViewById(R.id.textViewAboutUs);
        textViewAboutUs1 = (TextView)view.findViewById(R.id.textViewAboutUs1);
        textViewAboutUs2 = (TextView)view.findViewById(R.id.textViewAboutUs2);
        textViewAboutUs3 = (TextView)view.findViewById(R.id.textViewAboutUs3);
        textViewAboutUs4 = (TextView)view.findViewById(R.id.textViewAboutUs4);

        return view;
    }
}
