package com.example.barbershop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.barbershop.view.Home

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val menu = findViewById<Button>(R.id.btn_login)
        menu.setOnClickListener {
            val send = Intent(this, Home::class.java)
            startActivity(send)
        }


    }
}