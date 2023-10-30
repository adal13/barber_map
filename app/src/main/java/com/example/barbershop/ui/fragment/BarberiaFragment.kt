package com.example.barbershop.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.ui.Api.client.ApiClient
import com.example.barbershop.ui.Api.entity.Locals
import com.example.barbershop.R
import com.example.barbershop.ui.adapter.LocalAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BarberiaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BarberiaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_barberia, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = LocalAdapter(locals) // Reemplaza "locals" con tu lista de datos
        recyclerView.adapter = adapter*/

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShortsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarberiaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}