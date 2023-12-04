package com.map.barbershop.ui.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.map.barbershop.ui.fragment.bottomnavigation.reservation_files.ReservationFilesFragment
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@Parcelize
data class Horario(val hora: LocalTime, val ocupado: Boolean) : Parcelable

class AdapterHorario(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val listaHorarios: List<Horario> = crearHorarios()

    fun crearHorarios(): List<Horario> {
        val inicioHora = 8
        val finHora = 19

        return (inicioHora..finHora).flatMap { hora ->
            listOf(
                Horario(LocalTime.of(hora, 0), false),
                Horario(LocalTime.of(hora, 30), false)
            )
        }
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