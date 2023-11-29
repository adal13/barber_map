package com.map.barbershop.ui.fragment.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.map.barbershop.R
import com.map.barbershop.ui.Api.client.ApiClient
import com.map.barbershop.ui.Api.entity.Services
import com.map.barbershop.ui.adapter.ServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomServicioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_servicio, container, false)
        activity?.title="Servicio"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Llamada a la API para obtener la lista de locales
        val retrofitTraer = ApiClient.consumirApi.getIdServices()
        retrofitTraer.enqueue(object : Callback<Services> {
            override fun onResponse(call: Call<Services>, response: Response<Services>) {
                if (response.isSuccessful) {
                    val services = response.body()
                    val recyclerView = view.findViewById<RecyclerView>(R.id.service_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    val adapter = services?.let { ServiceAdapter(it.`object`) } // Reemplaza "locals" con tu lista de datos
                    recyclerView.adapter = adapter

                } else {
                    Toast.makeText(context, "Error de conexión a la API 1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Services>, t: Throwable) {
                Toast.makeText(context, "Error de conexión a la API 2", Toast.LENGTH_SHORT).show()
            }
        })
    }
}