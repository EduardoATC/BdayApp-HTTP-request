package com.example.birthdayrememberer;

import android.content.Context;
import android.graphics.Picture;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AllBdaysAdapter extends ArrayAdapter {

    /**
     * Adapter para el AllBirthdayFragment
     */
    Context context;
    int item_layout;
    private Fragment fragment;
    List <Bday> bdays;
    Calendar calendar;


    public AllBdaysAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context =context;
        this.item_layout =resource;
        this.bdays = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(item_layout, parent,false);

        }

        String name = bdays.get(position).getName();
        String picture = bdays.get(position).getImage();
        long date= bdays.get(position).getDate();
     //   DatePicker datePicker;

        calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date);
       int mes =  calendar.get(Calendar.DAY_OF_MONTH);


       TextView personName = convertView.findViewById(R.id.item_text_view);
       personName.setText(name);
       ImageView personImage = convertView.findViewById(R.id.item_imageview);
       Picasso.get().load(picture).into(personImage);
       TextView bday = convertView.findViewById(R.id.item_date_text_view);


        return convertView;

    }


}
