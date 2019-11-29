package com.example.birthdayrememberer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RaspApi {

    @GET("today.php")
    Call<String> turnOnRasp();

}
