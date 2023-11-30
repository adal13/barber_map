package com.map.barbershop.ui.fragment.bottomnavigation

import android.os.Bundle
import android.util.Log
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
import com.map.barbershop.ui.Api.entity.ObjectLocals
import com.map.barbershop.ui.adapter.LocalAdapter
import com.map.barbershop.ui.fragment.bottomnavigation.reservation_files.ReservationFilesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomBarberiaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_barberia, container, false)
        activity?.title="Barberia"
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitTraer = ApiClient.consumirApi.getIdLocals()
        retrofitTraer.enqueue(object : Callback<Locals> {
            override fun onResponse(call: Call<Locals>, response: Response<Locals>) {

                if (response.isSuccessful) {
                    handleApiResponse(response.body())
                } else {
                    handleError("Error de conexión a la API 1")
                }
            }

            override fun onFailure(call: Call<Locals>, t: Throwable) {
                Toast.makeText(context, "Error de conexión a la API 2", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleApiResponse(locals: Locals?) {
        locals?.let {
            if (it.`object`.isNotEmpty()) {
                showLocals(it.`object`)
            } else {
                // Handle case where the list is empty
            }
        }
    }

    open fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun handleError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }


    private fun showLocals(localsList: List<ObjectLocals>) {
        val adapter = LocalAdapter(localsList) { local ->
//            val fragmentB = ReservationFilesFragment()
//            val bundle = Bundle()
//            bundle.putInt("id_local", local.idLocal)

            val id_local = local.idLocal
            val nombre_local = local.nombre
            val txt_logo = local.logo
            val status_local = local.status

            val fragmentB = newInstance(id_local, nombre_local, txt_logo, status_local)

            Log.d("fragmentB" ,"id local: ${id_local}, nombre local: ${nombre_local}")

            replaceFragment(fragmentB)

        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    companion object {
        fun newInstance(idLocal: Int, nombre_local: String, txt_logo: String, status_local: String): ReservationFilesFragment {
            val fragment = ReservationFilesFragment()
            val args = Bundle()
            args.putInt("idLocal", idLocal)
            args.putString("nombre", nombre_local)
            args.putString("logo", txt_logo)
            args.putString("status", status_local)
            fragment.arguments = args
            return fragment
        }
    }
}