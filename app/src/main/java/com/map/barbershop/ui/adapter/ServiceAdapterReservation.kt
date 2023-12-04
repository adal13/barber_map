package com.map.barbershop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.map.barbershop.R
import com.map.barbershop.ui.Api.entity.ObjectServices
import com.squareup.picasso.Picasso

class ServiceAdapterReservation(
    private val data: List<ObjectServices>,
    private val serviceClickListener: ServiceClickListener
    ) : RecyclerView.Adapter<ServiceAdapterReservation.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val image : TextView = itemView.findViewById(R.id.image)


        val cardView: CardView = itemView.findViewById(R.id.service_card_reservation)
        val logo_img : ImageView =itemView.findViewById(R.id.service_image_reservation)
        val nombre : TextView =itemView.findViewById(R.id.service_nombre_reservation)
        val precio : TextView = itemView.findViewById(R.id.service_precio_reservation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_card_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = data[position]
        //holder.image.text = local.logo
        val url_img = "https://proyecto-ti.com/appbarberweb/";
        val imageView = holder.logo_img
        val imageUrl = url_img + service.imagen

        Picasso.get()
            .load(imageUrl)
            .into(imageView)

        holder.nombre.text = service.nombre
        holder.precio.text = "Precio: " + service.precio

        holder.cardView.setOnClickListener {
            // Obtiene el ID del servicio
            val serviceId = service.idServices
            val serviceName = service.nombre
            // Llama al método de clic del escuchador
            serviceClickListener.onServiceClick(serviceId, serviceName)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ServiceClickListener {
        fun onServiceClick(serviceId: Int, serviceName: String)
    }

}
