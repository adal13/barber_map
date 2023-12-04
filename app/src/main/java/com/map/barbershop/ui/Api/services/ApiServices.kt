package com.map.barbershop.ui.Api.services

import com.map.barbershop.ui.Api.entity.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    // Se llama toda la informacion de usuarios que esta filtrando la api
    @GET("api/v1/usuarios")
    fun getIdUser(): Call<User>

    // Esta endpoint hace el llamado al metodo POST para la insercion de datos
    @POST("api/v1/user")
    fun createUser(@Body objectUser: ObjectUser): Call<ObjectUser>

    // Para obtener el usuario mediante id, esto siempre que se haga alguna actualizacdion de datos del usuario
    @GET("api/v1/user/{userId}")
    fun getUserId(@Path("userId") userId: String?): Call<User>


    // Se obtienen todos los locales en este endpoint
    @GET("api/v1/locales")
    fun getIdLocals(): Call<Locals>

    // Se obtiene todos los servicios que tenemos en la API
    @GET("api/v1/services")
    fun getIdServices(): Call<Services>

    @Headers("Content-Type: application/json")
    @POST("api/v1/insertCita")
    fun insertCita(@Body objectCitas: ObjectCitas): Call<ObjectCitas>

}