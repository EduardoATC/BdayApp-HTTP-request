package com.example.birthdayrememberer;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
     private ListView navigationDrawer;
     private   DrawerLayout drawerLayout;
     private ActionBarDrawerToggle drawerToggle;
   private  Fragment fragment;
   private Retrofit retrofit;
   private Retrofit retrofit2;
   private BirthdayApi birthdayApi;
   private RaspApi raspApi;
   protected ArrayList<Bday> bdaysArray;
   private AddBirthDayFragment addBirthDayFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBirthDayFragment = new AddBirthDayFragment();
        bdaysArray = new ArrayList<>();





        //Retrofit
        /**
         * Buildeo de las Api's para hacer las peticiones POST y GET utilizando la libreríºa Retrofit
         */

        retrofit = new Retrofit.Builder()
                .baseUrl ("https://tonterias.herokuapp.com/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        birthdayApi = retrofit.create(BirthdayApi.class);


        retrofit2 = new Retrofit.Builder()
                .baseUrl ("http://192.168.4.163/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         raspApi = retrofit2.create(RaspApi.class);

       birthdaysGET();




                //Navigation Drawer
        navigationDrawer = findViewById(R.id.navigationDrawerOptions);
        navigationDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeToDrawerOptionClicked(position);

            }
        });
        drawerLayout= findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.abrir_menu,R.string.cerrar_menu);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        changeToDrawerOptionClicked(0);

    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void changeToDrawerOptionClicked(int screen) {

        FragmentManager manager = getSupportFragmentManager();
        switch (screen){

            case 0:

                fragment = new AddBirthDayFragment();
                break;
            case 1:

                fragment = new AllBirthdayFragment();
                break;
            case 2:
                fragment = new ClosestBdayFragment();
                break;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
        drawerLayout.closeDrawer(Gravity.START);
    }


    /**
     * Método que se ejecuta al hacer una petición get en BirthdayApi
     */

    public void birthdaysGET() {
        Call<ArrayList<Bday>> call = birthdayApi.getBdays();
        call.enqueue(new Callback<ArrayList<Bday>>() {
            @Override

            public void onResponse(Call<ArrayList<Bday>> call, Response<ArrayList<Bday>> response) {
                ArrayList<Bday> bdays= response.body();

                for (Bday bday : bdays) {
                    bdaysArray.add(bday);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Bday>> call, Throwable t) {
                System.out.println("ha fallado");
            }
        });}

    /**
     * Método que se ejecuta al hacer una petición get en RaspApi
     */

    public void turnOnRasp() {
       Call <String> call = raspApi.turnOnRasp();
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               System.out.println("Ha llegado");
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
               System.out.println("No");
           }
       });

    }
    /**
     * Método que se ejecuta al hacer una petición post en BirthdayApi
     */

    void birthdayPOST() {

            Call<String> call = birthdayApi.birthdayPOST(addBirthDayFragment.getName(), addBirthDayFragment.getImage(),addBirthDayFragment.getDate());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Toast.makeText(MainActivity.this, "Birthday Added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();


                }
            });
        }

}









