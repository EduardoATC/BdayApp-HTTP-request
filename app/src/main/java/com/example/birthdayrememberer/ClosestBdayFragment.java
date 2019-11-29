package com.example.birthdayrememberer;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClosestBdayFragment extends Fragment {
MainActivity mainActivity;
TextView name;
ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.closest_bday_layout,container,false);
            mainActivity = (MainActivity)getActivity();
            mainActivity.turnOnRasp();

            name = view.findViewById(R.id.closest_text_view);
            name.setText(mainActivity.bdaysArray.get(0).name);



         image = view.findViewById(R.id.closest_imageview);
        Picasso.get().load(mainActivity.bdaysArray.get(0).image).into(image);



        return  view;
    }




}
