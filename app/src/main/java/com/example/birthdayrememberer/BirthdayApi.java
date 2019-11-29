package com.example.birthdayrememberer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BirthdayApi {

    /**
     * Endpoint para hacer una petición post que añade uncumpleaños
     * @param name
     * @param image
     * @param date
     * @return
     */
    @FormUrlEncoded
    @POST("bday")
    Call<String> birthdayPOST (@Field("name") String name, @Field("image") String image, @Field("date") long date);

    /**
     * Endpoint para hacer una petición get que devuelve todos los cumpleaños almacenados
     * @return
     */
    @GET("bdays")
    Call<ArrayList<Bday>> getBdays();



}
