package com.map.barbershop.ui.fragment.bottomnavigation.reservation_files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.map.barbershop.R


class ReservationFilesFragment : Fragment() {
    private lateinit var etDate: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reservation_files, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etDate = view.findViewById(R.id.etDate)
        etDate.setOnClickListener { showDatePickerDialog() }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected( day as Int, month as Int, year as Int ) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year: Int){

        etDate.setText("Has seleccionado el dia $day del mes $month de $year")
    }
}