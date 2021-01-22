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

public class FragmentInnerCategoryLights extends Fragment {

    TextView textViewCategoryLights,textViewInnerCategory1,textViewCategory1Light1,textViewCategory1Light2,
            textViewInnerCategory2,textViewCategory2Light1,textViewCategory2Light2,
            textViewInnerCategory3,textViewCategory3Light1,textViewCategory3Light2;
    Button buttonOnCategory1Light1,buttonOnCat1Light2,buttonEditCategory1Light1,buttonEditCat1Light2,buttonOnCat2Light1,buttonOnCat2Light2,
            buttonOnCat3Light1,buttonOnCat3Light2,buttonEditCat2Light1,buttonEditCat2Light2,buttonEditCat3Light1,buttonEditCat3Light2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inner_category_lights,container,false);

        textViewCategory1Light1 = (TextView)view.findViewById(R.id.textViewCategory1Light1);
        textViewCategory1Light2 = (TextView)view.findViewById(R.id.textViewCategory1Light2);
        textViewCategory2Light1 = (TextView)view.findViewById(R.id.textViewCategory2Light1);
        textViewCategory2Light2 = (TextView)view.findViewById(R.id.textViewCategory2Light2);
        textViewCategory3Light1 = (TextView)view.findViewById(R.id.textViewCategory3Light1);
        textViewCategory3Light2 = (TextView)view.findViewById(R.id.textViewCategory3Light2);
        textViewCategoryLights = (TextView)view.findViewById(R.id.textViewCategoryLights);
        textViewInnerCategory1 = (TextView)view.findViewById(R.id.textViewInnerCategory1);
        textViewInnerCategory2 = (TextView)view.findViewById(R.id.textViewInnerCategory2);
        textViewInnerCategory3 = (TextView)view.findViewById(R.id.textViewInnerCategory3);

        buttonEditCategory1Light1 = (Button)view.findViewById(R.id.buttonEditCategory1Light1);
        buttonEditCat1Light2 = (Button)view.findViewById(R.id.buttonEditCat1Light2);
        buttonOnCategory1Light1 = (Button)view.findViewById(R.id.buttonOnCategory1Light1);
        buttonOnCat1Light2 = (Button)view.findViewById(R.id.buttonOnCat1Light2);
        buttonEditCat2Light1 = (Button)view.findViewById(R.id.buttonEditCat2Light1);
        buttonOnCat2Light1 = (Button)view.findViewById(R.id.buttonOnCat2Light1);
        buttonEditCat2Light2 = (Button)view.findViewById(R.id.buttonEditCat2Light2);
        buttonOnCat2Light2 = (Button)view.findViewById(R.id.buttonOnCat2Light2);
        buttonEditCat3Light1 = (Button)view.findViewById(R.id.buttonEditCat3Light1);
        buttonOnCat3Light1 = (Button)view.findViewById(R.id.buttonOnCat3Light1);
        buttonEditCat3Light2 = (Button)view.findViewById(R.id.buttonEditCat3Light2);
        buttonOnCat3Light2 = (Button)view.findViewById(R.id.buttonOnCat3Light2);

        return view;
    }
}
