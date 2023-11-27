package com.map.barbershop.ui.Api.services

import com.map.barbershop.ui.Api.entity.Locals
import com.map.barbershop.ui.Api.entity.ObjectUser
import com.map.barbershop.ui.Api.entity.Services
import com.map.barbershop.ui.Api.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @GET("api/v1/usuarios")
    fun getIdUser(): Call<User>

    @GET("api/v1/user/{userId}")
    fun getUserId(@Path("userId") userId: String?): Call<User>

    @GET("api/v1/locales")
    fun getIdLocals(): Call<Locals>

    @GET("api/v1/services")
    fun getIdServices(): Call<Services>

    @POST("api/v1/user")
    fun createUser(@Body objectUser: ObjectUser): Call<ObjectUser>




/*    @GET("api/v1/usuarios")
    fun getId(): Response<ApiResponse<List<Users>>>

    @GET("api/v1/usuarios")
    fun getUserId(@Query("IdUser") userId: Int): Call<List<Users?>?>?

    @GET("api/v1/usuarios")
    fun getUserById(@Query("id") id: Int): Call<List<Users?>?>?*/
}