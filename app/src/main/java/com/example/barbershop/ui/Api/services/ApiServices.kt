package com.example.barbershop.ui.Api.services

import com.example.barbershop.ui.Api.entity.Locals
import com.example.barbershop.ui.Api.entity.ObjectLocals
import com.example.barbershop.ui.Api.entity.Services
import com.example.barbershop.ui.Api.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("api/v1/usuarios")
    fun getIdUser(): Call<User>

    @GET("api/v1/locales")
    fun getIdLocals(): Call<Locals>

    @GET("api/v1/services")
    fun getIdServices(): Call<Services>


//    @GET("api/v1/usuarios")
//    fun getId(): Response<ApiResponse<List<Users>>>

//    @GET("api/v1/usuarios")
//    fun getUserId(@Query("IdUser") userId: Int): Call<List<Users?>?>?
//
//    @GET("api/v1/usuarios")
//    fun getUserById(@Query("id") id: Int): Call<List<Users?>?>?
}