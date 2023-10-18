package com.example.barbershop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.Api.client.ApiClient
import com.example.barbershop.Api.entity.User
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.ui.view.Home
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        retrofitTraer.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    btn_login.setOnClickListener {
                        val users = response.body()?.`object`
                        val username = tx_username.text.toString()
                        val password = tx_password.text.toString()
                        var isPasswordCorrect = false

                        for (users in users.orEmpty()) {
                            //if (users.nombreUsuario == username && BCrypt.checkpw(password, users.contrasena)) {
                            if (users.nombreUsuario == username && users.contrasena == password) {
                                isPasswordCorrect = true
                                val intent = Intent(this@MainActivity, Home::class.java)
                                startActivity(intent)
                                Toast.makeText(this@MainActivity, "Bienvenido a su cuenta ${users.nombre}", Toast.LENGTH_SHORT).show()
                                break
                            }
                        }
                        if (!isPasswordCorrect) {
                            Toast.makeText(this@MainActivity,"Contraseña incorrecta",Toast.LENGTH_SHORT).show()
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

/*        btn_login.setOnClickListener {
            val username = tx_username.text.toString()
            val password = tx_password.text.toString()

            if (username == "adal" && password == "123"){
                val intent = Intent(this@MainActivity, Home::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@MainActivity, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }


        }


        menu.setOnClickListener {
            val send = Intent(this, Home::class.java)
            startActivity(send)
        }



    private fun isValid(username: String, password: String): Boolean {
        return true
    }*/
    }
}