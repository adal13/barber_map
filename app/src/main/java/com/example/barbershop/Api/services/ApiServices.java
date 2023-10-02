package com.example.barbershop.Api.services;

import com.example.barbershop.Api.entity.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("api/v1/user/{id}")
    Call<Users> getId(@Path("id") int id);

    @GET("api/v1/usuarios")
    Call<List<Users>> getUserId(@Query("userId") int userId);

    @GET("api/v1/usuarios")
    Call<List<Users>> getUserById(@Query("id") int id);
}
