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
import com.map.barbershop.ui.Api.entity.Locals
import com.map.barbershop.ui.adapter.LocalAdapter
import com.map.barbershop.ui.fragment.bottomnavigation.reservation_files.ReservationFilesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomBarberiaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_barberia, container, false)
        activity?.title="Barberia"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = LocalAdapter(locals) // Reemplaza "locals" con tu lista de datos
        recyclerView.adapter = adapter*/

        val cardView = view.findViewById<androidx.cardview.widget.CardView>(R.id.card_views)

        // Agrega un OnClickListener al CardView
        cardView?.setOnClickListener {
            val fragmentB = ReservationFilesFragment();
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.reservation_files, fragmentB)
            transaction.addToBackStack(null)
            transaction.commit()
            // Tu lógica para manejar el clic aquí
        }
        // Llamada a la API para obtener la lista de locales
        val retrofitTraer = ApiClient.consumirApi.getIdLocals()
        retrofitTraer.enqueue(object : Callback<Locals> {
            override fun onResponse(call: Call<Locals>, response: Response<Locals>) {
                if (response.isSuccessful) {
                    val locals = response.body()
                    //val locals = response.body()?.`object`

                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    val adapter = locals?.let { LocalAdapter(it.`object`) } // Reemplaza "locals" con tu lista de datos
                    recyclerView.adapter = adapter



                    //val listLocal = response.body()?.`object`
                    /*var topMargin = 0
                    var previousCardView: View? = null

                    val container = view.findViewById<RelativeLayout>(com.google.android.material.R.id.container)

                    for (locals in locals.orEmpty()) {
                        //Inflando la vista
                        val cardView = layoutInflater.inflate(R.layout.local_card_view, container, false)

                        //Vista dentro de la tarjeta
                        val imagen = cardView.findViewById<ImageView>(R.id.image)
                        val direccion = cardView.findViewById<TextView>(R.id.direccion)
                        val nombre = cardView.findViewById<TextView>(R.id.title)
                        val hora = cardView.findViewById<TextView>(R.id.horario)
                        val telefono = cardView.findViewById<TextView>(R.id.telefono)
                        val tipoNegocio = cardView.findViewById<TextView>(R.id.tipoNegocio)

                        direccion.text = locals.direccion
                        nombre.text = locals.nombre
                        hora.text = locals.created_at
                        telefono.text = locals.updated_at
                        tipoNegocio.text = locals.ubicacion

                        val params = RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                        )

                        if (previousCardView != null) {
                            params.addRule(RelativeLayout.BELOW, previousCardView.id)
                            params.topMargin = topMargin
                        }
                        cardView.layoutParams = params

                        if (container.childCount > 0) {
                            params.addRule(RelativeLayout.BELOW, container.getChildAt(container.childCount - 1).id)
                            params.topMargin = topMargin
                        }

                        container.addView(cardView)
                        previousCardView = cardView

                        topMargin += resources.getDimensionPixelSize(androidx.cardview.R.dimen.cardview_default_elevation)

                    }*/
                } else {
                    Toast.makeText(context, "Error de conexión a la API 1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Locals>, t: Throwable) {
                Toast.makeText(context, "Error de conexión a la API 2", Toast.LENGTH_SHORT).show()
            }
        })
    }
}