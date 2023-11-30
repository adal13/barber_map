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

    private fun showLocals(localsList: List<ObjectLocals>) {
        val adapter = LocalAdapter(localsList) { local ->
            val fragmentB = ReservationFilesFragment()
            val bundle = Bundle()
            bundle.putInt("localId", local.idLocal)

            replaceFragment(fragmentB, bundle)

        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    open fun replaceFragment(fragment: Fragment, clickBundle: Bundle) {

        fragment.arguments = clickBundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_reservation_files, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun handleError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}