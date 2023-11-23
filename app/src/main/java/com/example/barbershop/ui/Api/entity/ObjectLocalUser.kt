package com.example.barbershop.ui.Api.entity

data class ObjectLocalUser(
    val created_at: Any,
    val idLocalUser: Int,
    val id_user_register: ObjectUser,
    val locals_id: ObjectLocals,
    val updated_at: Any,
    val user_id: ObjectUser
)