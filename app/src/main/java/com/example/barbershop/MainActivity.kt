package com.example.barbershop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.ui.Api.client.ApiClient
import com.example.barbershop.ui.Api.entity.User
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.ui.view.Home
//import org.mindrot.jbcrypt.BCrypt
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.barbershop.ui.files.changeCursorColor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.content.SharedPreferences
import com.example.barbershop.ui.view.sesionuser.CreatedAcount

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val tx_username = findViewById<EditText>(R.id.tx_username)
        val tx_password = findViewById<EditText>(R.id.tx_password)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val createAccount = findViewById<TextView>(R.id.createAccount)

        changeCursorColor(tx_username, this)
        changeCursorColor(tx_password, this)

        val retrofitTraer = ApiClient.consumirApi.getIdUser()
        retrofitTraer.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    btn_login.setOnClickListener {
                        val users = response.body()?.`object`
                        val username = tx_username.text.toString()
                        val password = tx_password.text.toString()
                        var isPasswordCorrect = false

                        for (users in users.orEmpty()) {
                            if (users.nombreUsuario == username) {
                                if (BCrypt.verifyer().verify(password.toCharArray(), users.contrasena).verified) {
                                //if (users.nombreUsuario == username && BCrypt.checkpw(password, users.contrasena)) {
                                //if (users.nombreUsuario == username && users.contrasena == password) {
                                    isPasswordCorrect = true

                                    val intent = Intent(this@MainActivity, Home::class.java)
                                    val userId = users.idUser
                                    val sharedPref: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                                    val editor: SharedPreferences.Editor = sharedPref.edit()

                                    editor.putString("user_id", userId.toString())
                                    editor.apply()

                                    startActivity(intent)
                                    Toast.makeText(this@MainActivity, "Bienvenido a su cuenta ${users.nombre}", Toast.LENGTH_SHORT).show()
                                    break
                                }
                            }
                        }
                        if (!isPasswordCorrect) {
                            Toast.makeText(this@MainActivity,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity,"Error de conexión a la API",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consultar", Toast.LENGTH_SHORT).show()
            }
        })


        createAccount.setOnClickListener{
            val intent = Intent(this@MainActivity, CreatedAcount::class.java)
            startActivity(intent)
        }

    }
}