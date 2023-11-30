package com.map.barbershop.ui.Api.entity

import com.google.gson.annotations.SerializedName

data class ObjectLocals(
    @SerializedName("idLocal")
    val idLocal: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("estado")
    val estado: String,

    @SerializedName("municipio")
    val municipio: String,

    @SerializedName("calle")
    val calle: String,

    @SerializedName("ubicacion")
    val ubicacion: String,

    @SerializedName("logo")
    val logo: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("updated_at")
    val updated_at: String,

)
