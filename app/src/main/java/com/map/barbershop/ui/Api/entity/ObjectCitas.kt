package com.map.barbershop.ui.Api.entity

data class ObjectCitas(
    val barber_id: Int,
    val created_at: String,
    val fecha: String?,
    val hora: String?,
    val idCitas: Int,
    val service_id: Int,
    val status: Int,
    val updated_at: String,
    val user_id: Int
)