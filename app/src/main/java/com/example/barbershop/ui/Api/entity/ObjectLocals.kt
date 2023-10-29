package com.example.barbershop.ui.Api.entity

import com.google.gson.annotations.SerializedName

data class ObjectLocals(
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("direccion")
    val direccion: String,
    @SerializedName("idLocal")
    val idLocal: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("ubicacion")
    val ubicacion: String,
    @SerializedName("updated_at")
    val updated_at: String
)