package com.example.barbershop.Api.client;

import com.example.barbershop.Api.services.ApiServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String URL_BASE = "http://localhost:8093/";
    private static Retrofit retrofit;

    public static ApiServices getApiService(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiServices.class);
    }

}
