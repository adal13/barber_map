package com.example.barbershop.ui.view.sesionuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.barbershop.R
import com.example.barbershop.ui.Api.client.ApiClient
import com.example.barbershop.ui.Api.entity.User
import com.example.barbershop.ui.files.changeCursorColor

class CreatedAcount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_acount)

        val txt_nombre = findViewById<EditText>(R.id.txt_nombre)
        val txt_apellido = findViewById<EditText>(R.id.txt_apellido)
        val txt_phone = findViewById<EditText>(R.id.txt_phone)
        val txt_email = findViewById<EditText>(R.id.email)
        val txt_user_name = findViewById<EditText>(R.id.txt_user_name)
        val txt_password = findViewById<EditText>(R.id.password)
        val btn_button = findViewById<Button>(R.id.button)

        changeCursorColor(txt_nombre, this)
        changeCursorColor(txt_apellido, this)
        changeCursorColor(txt_phone, this)
        changeCursorColor(txt_email, this)
        changeCursorColor(txt_user_name, this)
        changeCursorColor(txt_password, this)
    }
}