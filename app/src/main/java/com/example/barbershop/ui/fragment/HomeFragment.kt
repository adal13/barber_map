package com.example.barbershop.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.barbershop.R
import com.example.barbershop.ui.Api.client.ApiClient
import com.example.barbershop.ui.Api.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txt_name = view.findViewById<TextView>(R.id.txt_name)

        val retrofitTraer = ApiClient.consumirApi.getIdUser()

        retrofitTraer.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val objects = response.body()?.`object`?.get(0)
                    val dato = objects?.nombre
                    txt_name.text = dato
                } else {
                    // Manejar la respuesta no exitosa
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Manejar errores de red u otros errores
            }
        })
    }
}