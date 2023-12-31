package com.example.barbershop.ui.Api.entity

import com.google.gson.annotations.SerializedName

data class ObjectUser(
    @SerializedName("apellido")
    val apellido: String,

    @SerializedName("contrasena")
    val contrasena: String,

    @SerializedName("correo")
    val correo: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("emailVerified")
    val emailVerified: Any,

    @SerializedName("idUser")
    val idUser: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("nombreUsuario")
    val nombreUsuario: String,

    @SerializedName("rememberToken")
    val rememberToken: Any,

    @SerializedName("telefono")
    val telefono: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)