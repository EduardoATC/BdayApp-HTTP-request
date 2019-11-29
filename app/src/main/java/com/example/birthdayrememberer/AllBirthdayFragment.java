package com.example.birthdayrememberer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class AllBirthdayFragment extends Fragment {
    GridView gridOfBdays;
    ArrayAdapter adapter;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.all_birthday_fragment_layout,container,false);

        mainActivity = (MainActivity) getActivity();
        gridOfBdays = view.findViewById(R.id.bdays_grid);
        adapter = new AllBdaysAdapter(getContext(),R.layout.bday_item,mainActivity.bdaysArray);
        gridOfBdays.setAdapter(adapter);


        return  view;
    }



}
