package com.example.barbershop.ui.view.sesionuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.barbershop.MainActivity
import com.example.barbershop.R
import com.example.barbershop.ui.Api.client.ApiClient
import com.example.barbershop.ui.Api.entity.ObjectUser
import com.example.barbershop.ui.files.Validaciones
import com.example.barbershop.ui.files.changeCursorColor
import retrofit2.Call
import retrofit2.Response

class CreatedAcount : AppCompatActivity() {
    private lateinit var txt_nombre: EditText
    private lateinit var txt_apellido: EditText
    private lateinit var txt_phone: EditText
    private lateinit var txt_email: EditText
    private lateinit var txt_user_name: EditText
    private lateinit var txt_password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_acount)
        txt_nombre = findViewById(R.id.txt_nombre)
        txt_apellido = findViewById(R.id.txt_apellido)
        txt_phone = findViewById(R.id.txt_phone)
        txt_email = findViewById(R.id.email)
        txt_user_name = findViewById(R.id.txt_user_name)
        txt_password = findViewById(R.id.password)
        val btn_button = findViewById<Button>(R.id.button)

        changeCursorColor(txt_nombre, this)
        changeCursorColor(txt_apellido, this)
        changeCursorColor(txt_phone, this)
        changeCursorColor(txt_email, this)
        changeCursorColor(txt_user_name, this)
        changeCursorColor(txt_password, this)

        btn_button.setOnClickListener{

            if(validateFields()){

                // Hasheo de contraseña
                val hashedPassword = hashPassword(txt_password.toString())

                val newUser = ObjectUser(
                    idUser = 0,
                    nombre = txt_nombre.text.toString(),
                    apellido = txt_apellido.text.toString(),
                    telefono = txt_phone.text.toString(),
                    correo = txt_email.text.toString(),
                    nombreUsuario = txt_user_name.text.toString(),
                    contrasena = hashedPassword,
                    avatar = "",
                    emailVerified = "",
                    rememberToken = "",
                    createdAt = "",
                    updatedAt = ""
                )

                val createUsers = ApiClient.consumirApi.createUser(newUser)

                createUsers.enqueue(object : retrofit2.Callback<ObjectUser>{
                    override fun onResponse(call: Call<ObjectUser>, response: Response<ObjectUser>) {
                        if (response.isSuccessful) {
                            //val userResponse = response.body()

                            val intent = Intent(this@CreatedAcount, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this@CreatedAcount, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Log.e("API Error", "Error durante la llamada a la API: ${response.message()}")
                            Toast.makeText(this@CreatedAcount, "Error durante la llamada a la API", Toast.LENGTH_SHORT).show()

                            // Agrega esta línea para imprimir la respuesta JSON
                            response.errorBody()?.string()?.let { it1 -> Log.e("API Response", it1) }
                        }
                    }

                    override fun onFailure(call: Call<ObjectUser>, t: Throwable) {
                        Log.e("API Error", "Error during API call", t)
                        Toast.makeText(this@CreatedAcount, "Error during API call: ${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

    }

    fun validateFields(): Boolean {

        val name = txt_nombre.text.toString()
        if (!Validaciones.validateText(name)) {
            Toast.makeText(this, "Solo texto", Toast.LENGTH_SHORT).show()
            return false
        }

        val last_name = txt_apellido.text.toString()
        if (!Validaciones.validateText(last_name)) {
            Toast.makeText(this, "Solo texto", Toast.LENGTH_SHORT).show()
            return false
        }

        val phone = txt_phone.text.toString()
        if (!Validaciones.validatePhone(phone)) {
            Toast.makeText(this, "Numero de telefono invalido", Toast.LENGTH_SHORT).show()
            return false
        }

        val correo = txt_email.text.toString()
        if (!Validaciones.validateEmail(correo)) {
            Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            return false
        }

        /*val password = txt_password.text.toString()
        if (!Validaciones.validatePassword(password)) {
            Toast.makeText(this, "Contraseña Invalida", Toast.LENGTH_SHORT).show()
            return false
        }*/
        return true
    }


    fun hashPassword(password: String): String {
        // Hashear la contraseña
        val password = txt_password.text.toString()
        //val hashedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray())
        //val hashedPassword = BCrypt.withDefaults().hashToString(10, BCrypt.Version.VERSION_2Y, password.toCharArray())
        return BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(10, password.toCharArray())

    }

}