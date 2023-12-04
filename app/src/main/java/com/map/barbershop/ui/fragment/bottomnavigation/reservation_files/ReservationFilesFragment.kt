package com.map.barbershop.ui.fragment.bottomnavigation.reservation_files

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.map.barbershop.R
import com.map.barbershop.ui.Api.client.ApiClient
import com.map.barbershop.ui.Api.entity.ObjectCitas
import com.map.barbershop.ui.Api.entity.Services
import com.map.barbershop.ui.Api.entity.User
import com.map.barbershop.ui.adapter.AdapterHorario
import com.map.barbershop.ui.adapter.Horario
import com.map.barbershop.ui.adapter.LocalAdapterReservation
import com.map.barbershop.ui.adapter.ServiceAdapterReservation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*


class ReservationFilesFragment : Fragment(), ServiceAdapterReservation.ServiceClickListener {
    private lateinit var etDate: EditText
    private lateinit var listaHorarios: List<Horario>


    private var selectedServiceId: Int = -1
    private var selectedUserId: Int = -1
    private var horaInicioSeleccionada: String? = null
    private var fechaSeleccionada: String? = null
    private var idUsuario: Int? = null

    private lateinit var btn_agendar: Button



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
        activity?.title = "Reservacion"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etDate = view.findViewById(R.id.etDate)
        etDate.setOnClickListener { showDatePickerDialog() }

        btn_agendar = view.findViewById(R.id.btn_register_cita)


        val arguments = arguments
        if (arguments != null) {
            idUsuario = arguments.getInt("id_users")
            Log.d("Cita a insertar", "$idUsuario")
            Log.d(
                "Cita a insertar",
                "Hora: ${horaInicioSeleccionada} fecha: ${fechaSeleccionada} Id User: ${selectedUserId} Servicio: ${selectedServiceId} user:${idUsuario}"
            )

        }

        // Obtén la lista de horarios del AdapterHorario
        val adapterHorario = AdapterHorario(requireActivity())
        val listaHorarios = adapterHorario.crearHorarios()

        val recyclerViewHorarios = view.findViewById<RecyclerView>(R.id.recyclerViewHorario)
        recyclerViewHorarios.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val horarioAdapter = HorarioAdapter(listaHorarios)
        recyclerViewHorarios.adapter = horarioAdapter

        val retrofitTraer = ApiClient.consumirApi.getIdServices()
        retrofitTraer.enqueue(object : Callback<Services> {
            override fun onResponse(call: Call<Services>, response: Response<Services>) {
                if (response.isSuccessful) {
                    val services = response.body()
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewServicio)
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    // Crea un adaptador con la lista de servicios y el listener
                    val adapter = services?.let { services ->
                        ServiceAdapterReservation(
                            services.`object`,
                            object : ServiceAdapterReservation.ServiceClickListener {
                                override fun onServiceClick(serviceId: Int, serviceName: String) {
                                    selectedServiceId = serviceId
                                    // Maneja el clic en el servicio aquí, obteniendo el ID del servicio
                                    Toast.makeText(
                                        context,
                                        "Seleccionaste el servicio de: $serviceName",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Realiza las acciones necesarias con el ID del servicio
                                }
                            })
                    }

                    recyclerView.adapter = adapter

                } else {
                    Toast.makeText(context, "Error de conexión a la API 1", Toast.LENGTH_SHORT)
                        .show()
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
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    val adapterUser = local?.let { userbarber ->
                        LocalAdapterReservation(
                            userbarber.`object`,
                            object : LocalAdapterReservation.UserClickListener {
                                override fun onUserClick(userId: Int, userName: String) {
                                    selectedUserId = userId

                                    // Maneja el clic en el servicio aquí, obteniendo el ID del servicio
                                    Toast.makeText(
                                        context,
                                        "Seleccionaste al barbero: $userName",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Realiza las acciones necesarias con el ID del servicio
                                }
                            })

                    }

                    recyclerView.adapter = adapterUser

                } else {
                    Toast.makeText(context, "Error de conexión a la API 1", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Error de conexión a la API 2", Toast.LENGTH_SHORT).show()
            }
        })

        val adapter = HorarioAdapter(listaHorarios)
        recyclerViewHorarios.adapter = adapter

        adapter.setOnItemClickListener(object : HorarioAdapter.OnItemClickListener {
            override fun onItemClick(horario: Horario) {
                horaInicioSeleccionada = horario.hora.toString()
            }
        })

        btn_agendar.setOnClickListener {
            Log.d(
                "Cita a insertar",
                "Hora: ${horaInicioSeleccionada} fecha: ${fechaSeleccionada} Id User: ${selectedUserId} Servicio: ${selectedServiceId} user:${idUsuario}"
            )

                    if (!horaInicioSeleccionada.isNullOrEmpty()) {
                        val dataString = horaInicioSeleccionada
                        val formatoEntrada = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

                        try {
                            val localTime = LocalTime.parse(dataString, formatoEntrada)

                            // Verifica si idUsuario no es nulo antes de continuar
                            idUsuario?.let { usuario ->
                                // Crea el objeto ObjectCitas
                                val newCita = ObjectCitas(
                                    idCitas = 0,
                                    hora = localTime.toString(),
                                    fecha = fechaSeleccionada,
                                    barber_id = selectedUserId,
                                    service_id = selectedServiceId,
                                    user_id = usuario,
                                    status = 1,
                                    created_at = "",
                                    updated_at = ""
                                )

                                // Llama a la API para insertar la cita
                                ApiClient.consumirApi.insertCita(newCita).enqueue(object : Callback<ObjectCitas> {
                                    override fun onResponse(call: Call<ObjectCitas>, response: Response<ObjectCitas>) {
                                        if (response.isSuccessful) {
                                            // La respuesta fue exitosa
                                            Toast.makeText(
                                                context,
                                                "Tu cita ha sido asignada correctamente",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            // Hubo un error en la respuesta
                                            Log.e("API Error", "Error durante la llamada a la API: ${response.message()}")
                                            Toast.makeText(
                                                context,
                                                "Error durante la llamada a la API",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            // Agrega esta línea para imprimir la respuesta JSON
                                            response.errorBody()?.string()?.let { Log.e("API Response", it) }
                                        }
                                    }

                                    override fun onFailure(call: Call<ObjectCitas>, t: Throwable) {
                                        // Hubo un error en la llamada a la API
                                        Log.e("API Error", "Error durante la llamada a la API", t)
                                        Toast.makeText(
                                            context,
                                            "Error durante la llamada a la API: ${t.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        // Imprimir el cuerpo de la respuesta en caso de error
                                        try {
                                            Log.e("API Error", "Error body: ${call.execute().errorBody()?.string()}")
                                        } catch (e: IOException) {
                                            e.printStackTrace()
                                        }
                                    }
                                })
                            } ?: run {
                                // idUsuario es nulo
                                Log.e("Error", "idUsuario es nulo")
                                Toast.makeText(context, "Error: idUsuario es nulo", Toast.LENGTH_SHORT).show()
                            }

                        } catch (e: DateTimeParseException) {
                            e.printStackTrace()
                            // Manejar el error de formato de hora
                        }
                    }

        }

    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year ->
            onDateSelected(
                day as Int,
                month as Int,
                year as Int
            )
        }
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        // Crear un objeto Calendar y establecer la fecha seleccionada
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        // Formatear la fecha como un string en el formato deseado
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        fechaSeleccionada = dateFormat.format(calendar.time)

        // Establecer el texto en el EditText
        etDate.setText(fechaSeleccionada)
    }

//    fun setHora(hora: String) {
//        textViewHora.text = hora
//    }

    class HorarioAdapter(private val listaHorarios: List<Horario>) :
        RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

        private var onItemClickListener: OnItemClickListener? = null

        fun setOnItemClickListener(listener: OnItemClickListener) {
            onItemClickListener = listener
        }

        interface OnItemClickListener {
            fun onItemClick(horario: Horario)
        }

        class HorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // Aquí puedes agregar referencias a las vistas que deseas mostrar para cada elemento del horario
            val textViewHora: TextView = itemView.findViewById(R.id.textViewHora)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_horario, parent, false)
            return HorarioViewHolder(view)
        }

        override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
            val horario = listaHorarios[position]
            if (horario != null) {
                holder.textViewHora.text = horario.hora.toString()
            }
            holder.textViewHora.setOnClickListener {

                onItemClickListener?.onItemClick(horario)
                // Maneja el clic del elemento aquí
                // Puedes acceder a horario.id para obtener el ID y realizar acciones necesarias
                Toast.makeText(
                    holder.itemView.context,
                    "Seleccionaste la hora de tu cita a las: ${horario.hora}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun getItemCount(): Int {
            return listaHorarios.size
        }
    }

    override fun onServiceClick(serviceId: Int, serviceName: String) {
        Toast.makeText(requireContext(), "Clic en servicio con ID: $serviceId", Toast.LENGTH_SHORT)
            .show()
    }
}