package com.example.barbershop.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barbershop.R

class Barber : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barber)
        (this as AppCompatActivity).supportActionBar?.title = "Notificacion"
    }
}