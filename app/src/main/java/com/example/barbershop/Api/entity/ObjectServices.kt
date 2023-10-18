package com.example.barbershop.Api.entity

data class ObjectServices(
    val created_at: String,
    val duracion: Double,
    val idServices: Int,
    val locals_id: ObjectLocals,
    val nombre: String,
    val precio: Double,
    val update_at: String
)