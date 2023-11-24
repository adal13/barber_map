package com.example.barbershop.ui.Api.entity

import com.google.gson.annotations.SerializedName

data class ObjectLocalUser(
    @SerializedName("idLocalUser")
    val idLocalUser: Int,

    @SerializedName("user_id")
    val user_id: ObjectUser,

    @SerializedName("locals_id")
    val locals_id: ObjectLocals,

    @SerializedName("id_user_register")
    val id_user_register: ObjectUser,

    @SerializedName("created_at")
    val created_at: Any,

    @SerializedName("updated_at")
    val updated_at: Any
)