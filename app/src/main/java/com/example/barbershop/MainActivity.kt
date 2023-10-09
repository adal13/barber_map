package com.example.barbershop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.Api.client.ApiClient
import com.example.barbershop.Api.entity.User
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.view.Home
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val apiClient = ApiClient()
        Log.d("MainActivity", "Antes de obtener apiService")
        val apiService: ApiServices? = apiClient.getApiService()
        Log.d("MainActivity", "Después de obtener apiService")

        lifecycleScope.launch {
            try {
                val response = apiService?.getId()
                if (response != null) {
                    if (response.isSuccessful) {
                        val userList = response.body()?.`object`
                        if (userList != null) {
                            for (user in userList) {
                                println("Nombre: ${user.nombre}, Apellido: ${user.apellido}")
                                // Realiza las operaciones necesarias con cada usuario
                            }
                        }
                    } else {
                        // Maneja errores de respuesta HTTP aquí
                    }
                }
            } catch (e: Exception) {
                // Maneja cualquier excepción que pueda ocurrir durante la llamada a la API
                println("Error: ${e.message}")
            }
        }
*/



        val retrofitTraer = ApiClient.consumirApi.getIdUser()
        retrofitTraer.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                binding.jwtApi.text = response.body().toString()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consultar", Toast.LENGTH_SHORT).show()
            }

        })

        val menu = findViewById<Button>(R.id.btn_login)
        menu.setOnClickListener {
            val send = Intent(this, Home::class.java)
            startActivity(send)
        }

    }
}