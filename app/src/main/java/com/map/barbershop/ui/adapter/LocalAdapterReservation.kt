package com.map.barbershop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.map.barbershop.R
import com.map.barbershop.ui.Api.entity.ObjectUser
import com.squareup.picasso.Picasso

class LocalAdapterReservation(
    private val data: List<ObjectUser>,
    private val userClickListener: LocalAdapterReservation.UserClickListener
    ) : RecyclerView.Adapter<LocalAdapterReservation.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val image : TextView = itemView.findViewById(R.id.image)

        val cardView: CardView = itemView.findViewById(R.id.local_card_reservation)
        val logo_img : ImageView =itemView.findViewById(R.id.local_image_reservation)
        val nombre : TextView =itemView.findViewById(R.id.local_nombre_reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.local_card_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val local = data[position]

        val url_img = "https://proyecto-ti.com/appbarberweb/";
        val imageView = holder.logo_img
        val imageUrl = url_img + local.avatar
        Picasso.get()
            .load(imageUrl)
            .into(imageView)

        holder.nombre.text = local.nombre


        holder.cardView.setOnClickListener {
            // Obtiene el ID del servicio
            val userId = local.idUser
            // Llama al método de clic del escuchador
            userClickListener.onUserClick(userId)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface UserClickListener {
        fun onUserClick(userId: Int)
    }

}
