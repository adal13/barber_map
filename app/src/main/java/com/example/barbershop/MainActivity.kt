package com.example.barbershop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.Api.services.ApiServices
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.view.Home

class MainActivity : AppCompatActivity() {

    private lateinit var mTextView: TextView;
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiServices
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