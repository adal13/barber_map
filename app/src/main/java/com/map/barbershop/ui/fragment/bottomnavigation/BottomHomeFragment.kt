package com.map.barbershop.ui.fragment.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.map.barbershop.R

class BottomHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        activity?.title="Home"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().runOnUiThread {
            val txt_name = view.findViewById<TextView>(R.id.txt_name)
            val databundle = arguments
            if (databundle != null) {
                val nameUser = databundle?.getString("name_user")
                if (nameUser != null) {
                        txt_name.text = nameUser
                }
            }
        }
    }

}