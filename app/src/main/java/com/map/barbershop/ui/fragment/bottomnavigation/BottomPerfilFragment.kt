package com.map.barbershop.ui.fragment.bottomnavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.map.barbershop.R
import com.map.barbershop.ui.Api.client.ApiClient
import com.map.barbershop.ui.Api.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomPerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
        activity?.title="Perfil"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nombre = view.findViewById<TextView>(R.id.txt_nombre_perfil)
        val email = view.findViewById<TextView>(R.id.txt_email_perfil)
        val users = view.findViewById<TextView>(R.id.txt_nombre_user)

        val retrofitTraer = ApiClient.consumirApi.getIdUser()

        retrofitTraer.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val objects = response.body()?.`object`?.get(0)
                    val dato = objects?.nombre

                    val databundle = arguments
                    if (databundle != null) {
                        //val id_user = databundle.getInt("id_user", 0)
                        val nameUser = databundle.getString("name_user")
                        val email_user = databundle.getString("email_user")
                        val usuario = databundle.getString("usuario")
                        if (nameUser != null && email_user != null && usuario != null) {
                            nombre.text = nameUser
                            email.text = email_user
                            users.text = usuario
                            Log.d("PerfilFragment", "Nombre: $nameUser, Email: $email_user, Usuario: $usuario")
                        } else {
                            // Manejar el caso en el que "name_user" es nulo
                        }
                    } else {
                        // Manejar el caso en el que "arguments" es nulo
                    }


                } else {
                    // Manejar la respuesta no exitosa
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Manejar errores de red u otros errores
            }
        })

//        val databundle = arguments
//        val idUser = databundle!!.getInt("id_user", 0)
//        val nameUser = databundle.getString("name_user")
//        val emailUser = databundle.getString("email_user")
//        val usuario = databundle.getString("usuario")
//
//        Log.d("PerfilFragment", "ID: $idUser, Nombre: $nameUser, Email: $emailUser, Usuario: $usuario")

/*        nombre.text = nameUser
        email.text = emailUser
        users.text = usuario*/
    }
}