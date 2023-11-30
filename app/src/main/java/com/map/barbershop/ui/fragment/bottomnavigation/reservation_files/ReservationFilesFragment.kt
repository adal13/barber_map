package com.map.barbershop.ui.fragment.bottomnavigation.reservation_files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.map.barbershop.R
import com.map.barbershop.ui.Api.client.ApiClient
import com.map.barbershop.ui.Api.entity.Services
import com.map.barbershop.ui.Api.entity.User
import com.map.barbershop.ui.adapter.AdapterHorario
import com.map.barbershop.ui.adapter.Horario
import com.map.barbershop.ui.adapter.LocalAdapterReservation
import com.map.barbershop.ui.adapter.ServiceAdapterReservation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReservationFilesFragment : Fragment(), ServiceAdapterReservation.ServiceClickListener  {
    private lateinit var etDate: EditText
    private lateinit var listaHorarios: List<Horario>


    private var selectedServiceId: Int = -1
    private var selectedUserId: Int = -1

    companion object {
        private const val ARG_HORARIO = "horario"

        fun newInstance(horario: Horario): ReservationFilesFragment {
            val fragment = ReservationFilesFragment()
            val args = Bundle()
            args.putParcelable(ARG_HORARIO, horario)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservation_files, container, false)
        activity?.title="Reservacion"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etDate = view.findViewById(R.id.etDate)
        etDate.setOnClickListener { showDatePickerDialog() }

        // Obtén la lista de horarios del AdapterHorario
        val adapterHorario = AdapterHorario(requireActivity())
        val listaHorarios = adapterHorario.crearHorarios()

        val recyclerViewHorarios = view.findViewById<RecyclerView>(R.id.recyclerViewHorario)
        recyclerViewHorarios.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val horarioAdapter = HorarioAdapter(listaHorarios)
        recyclerViewHorarios.adapter = horarioAdapter

        val retrofitTraer = ApiClient.consumirApi.getIdServices()
        retrofitTraer.enqueue(object : Callback<Services> {
            override fun onResponse(call: Call<Services>, response: Response<Services>) {
                if (response.isSuccessful) {
                    val services = response.body()
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewServicio)
                    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    // Crea un adaptador con la lista de servicios y el listener
                    val adapter = services?.let { services ->
                        ServiceAdapterReservation(services.`object`, object : ServiceAdapterReservation.ServiceClickListener {
                            override fun onServiceClick(serviceId: Int) {
                                // Maneja el clic en el servicio aquí, obteniendo el ID del servicio
                                Toast.makeText(context, "Clic en servicio con ID: $serviceId", Toast.LENGTH_SHORT).show()
                                // Realiza las acciones necesarias con el ID del servicio
                            }
                        })
                    }

                    recyclerView.adapter = adapter

                } else {
                    Toast.makeText(context, "Error de conexión a la API 1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Services>, t: Throwable) {
                Toast.makeText(context, "Error de conexión a la API 2", Toast.LENGTH_SHORT).show()
            }
        })

        val retrofitTraerUsuario = ApiClient.consumirApi.getIdUser()
        retrofitTraerUsuario.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val local = response.body()
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewBarberos)
                    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    val adapterUser = local?.let { userbarber ->
                        LocalAdapterReservation(userbarber.`object`, object : LocalAdapterReservation.UserClickListener{
                            override fun onUserClick(userId: Int) {
                                // Maneja el clic en el servicio aquí, obteniendo el ID del servicio
                                Toast.makeText(context, "Clic en usuario con ID: $userId", Toast.LENGTH_SHORT).show()
                                // Realiza las acciones necesarias con el ID del servicio
                            }
                        })

                    }

                    recyclerView.adapter = adapterUser

                } else {
                    Toast.makeText(context, "Error de conexión a la API 1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Error de conexión a la API 2", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected( day as Int, month as Int, year as Int ) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year: Int){

        etDate.setText("$day / $month / $year")
    }

//    fun setHora(hora: String) {
//        textViewHora.text = hora
//    }

    class HorarioAdapter(private val listaHorarios: List<Horario>) :
        RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

        class HorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // Aquí puedes agregar referencias a las vistas que deseas mostrar para cada elemento del horario
            val textViewHora: TextView = itemView.findViewById(R.id.textViewHora)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horario, parent, false)
            return HorarioViewHolder(view)
        }

        override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
            val horario = listaHorarios[position]
            if (horario != null) {
                holder.textViewHora.text = horario.hora
            }
            holder.textViewHora.setOnClickListener {
                // Maneja el clic del elemento aquí
                // Puedes acceder a horario.id para obtener el ID y realizar acciones necesarias
                Toast.makeText(
                    holder.itemView.context,
                    "Clic en el horario con ID: ${horario.hora}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun getItemCount(): Int {
            return listaHorarios.size
        }
    }

    override fun onServiceClick(serviceId: Int) {
        Toast.makeText(requireContext(), "Clic en servicio con ID: $serviceId", Toast.LENGTH_SHORT).show()
    }
}