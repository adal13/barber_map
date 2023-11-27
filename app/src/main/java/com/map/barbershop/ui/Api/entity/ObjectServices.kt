package com.map.barbershop.ui.Api.entity

import com.google.gson.annotations.SerializedName

data class ObjectServices(
    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("duracion")
    val duracion: Double,

    @SerializedName("idServices")
    val idServices: Int,

    @SerializedName("imagen")
    val imagen: String,

    @SerializedName("locals_id")
    val locals_id: ObjectLocals,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("precio")
    val precio: Double,

    @SerializedName("update_at")
    val update_at: String
)