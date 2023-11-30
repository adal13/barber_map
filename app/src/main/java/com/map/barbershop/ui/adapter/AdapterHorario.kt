package com.map.barbershop.ui.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.map.barbershop.ui.fragment.bottomnavigation.reservation_files.ReservationFilesFragment
import kotlinx.parcelize.Parcelize

@Parcelize
data class Horario(val hora: String, val ocupado: Boolean) : Parcelable

class AdapterHorario(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val listaHorarios: List<Horario> = crearHorarios()

    fun crearHorarios(): List<Horario> {
        val horarios = mutableListOf<Horario>()

        val inicioHora = 8
        val inicioMinuto = 30
        val finHora = 19
        val finMinuto = 30

        for (hora in inicioHora..finHora) {
            for (minuto in 0..30 step 30) {
                val horaFormateada = String.format("%02d:%02d", hora, minuto)
                val horario = Horario(
                    horaFormateada,
                    false
                ) // Inicialmente, todos los horarios no están ocupados
                horarios.add(horario)
            }
        }

        return horarios
    }

    override fun getItemCount(): Int {
        return listaHorarios.size
    }

/*    override fun createFragment(position: Int): Fragment {
        val horario = listaHorarios[position]
        val fragment = ReservationFilesFragment.newInstance(horario.hora)
        fragment.arguments = Bundle().apply {
            putParcelable("horario", horario)
        }
        return fragment
    }*/
    override fun createFragment(position: Int): Fragment {
        val horario = listaHorarios[position]
        return ReservationFilesFragment.newInstance(horario)
    }
}