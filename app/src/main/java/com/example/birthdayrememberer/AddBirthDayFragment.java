package com.example.birthdayrememberer;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class AddBirthDayFragment extends Fragment {
    protected TextView title;
    protected ImageView picture;
    protected Button saveButton;
    private Spinner spinner;
    private EditText nameEditText;
    private DatePicker datePicker;
    protected ArrayList<String> predefinedImages;
    private MainActivity mainActivity;



    private String image;
    private  String name;
    private long date;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.add_birthday_fragment_layout, container, false);
        /**
         * Iniciliazación de las variables.
         */
        mainActivity = (MainActivity) getActivity();
        title = view.findViewById(R.id.add_birthday_fragment_title);
        picture = view.findViewById(R.id.add_birthday_fragment_image);
        saveButton = view.findViewById(R.id.add_birthday_fragment_button);
        spinner = view.findViewById(R.id.add_birthday_fragment_spinner);
        nameEditText = view.findViewById(R.id.add_birthday_fragment_edit_text);
        datePicker = view.findViewById(R.id.add_birthday_frament_date_picker);

        predefinedImages();

        /**
         * Asignación de adapter(con listado de imágenes predifinidad) al spinner .
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.images, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


                                //Spinner
    /**
     * Se vincúla un listener al spinner
     * Utilizando la posición que ofrece el listener se almacena la url de la imagen elegida(en función de la
     * posición )en una variable de tipo string
     */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSelection();
                image = predefinedImages.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /**
         * Se recogen los datos del formulario y se ejecuta la petición post para guardar un nuevo cumpleaños
         */
        // Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameEditText.getText().toString();
                mainActivity.birthdayPOST();

            }
        });

/**
 * Se elige la  fecha a través de un listener
 */
                                    //Date picker
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                    date = calendar.getTimeInMillis();


                }
            });
        }
        return view;
    }

    /**
     * Dada la posición del elemento seleccionado de un Spinner carga la imagen en esa misma posición de un
     * array de imagenes en un imageView
     */
    void imageSelection() {
        switch (spinner.getSelectedItemPosition()) {

            case 0:
                Picasso.get().load(predefinedImages.get(0)).into(picture);
                break;
            case 1:
                Picasso.get().load(predefinedImages.get(1)).into(picture);
                break;
            case 2:
                Picasso.get().load(predefinedImages.get(2)).into(picture);
                break;
            case 3:
                Picasso.get().load(predefinedImages.get(3)).into(picture);
                break;
            case 4:
                Picasso.get().load(predefinedImages.get(4)).into(picture);
                break;
            default:

        }

    }


    /**
     * Inicaliza un arraylist de String y guarda las url de las imagenes en el arraylist
     */
    void predefinedImages() {

        predefinedImages = new ArrayList<>();
        predefinedImages.add("http://docs.google.com/uc?export=open&id=17vlrZbE7z5vc-MAK-C8SBNQsNLhOcoRe");
        predefinedImages.add("http://docs.google.com/uc?export=open&id=1x8yH36yVP22_C7TX-w28-EOG4KuzKWTn");
        predefinedImages.add("http://docs.google.com/uc?export=open&id=1UJLP6L_7NnjSA4vA-_7knyofc-M9FWwX");
        predefinedImages.add("http://docs.google.com/uc?export=open&id=1_vV36NlPnEgIaJLxVPnOiQzxZtROLljE");
        predefinedImages.add("http://docs.google.com/uc?export=open&id=1NUEe6F52qnkBc7lFUXubCbLZdtj2h_Qi");


    }

    /**
     * Devuelve el valor de la imagen del formulario del AddBirthdayFRagment
     * @return
     */

    public String getImage() {
        return image;
    }

    /**
     * Devuelve el valor del nombre del formulario del AddBirthdayFRagment
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve el valor de la fecha del formulario del AddBirthdayFRagment
     * @return
     */
    public long getDate() {
        return date;
    }





}





