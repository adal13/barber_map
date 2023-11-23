package com.example.barbershop.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.R

class Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        supportActionBar?.title = "Notificacion"
    }
}