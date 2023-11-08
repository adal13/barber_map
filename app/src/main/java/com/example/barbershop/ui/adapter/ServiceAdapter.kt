package com.example.barbershop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.ui.Api.entity.ObjectServices
import com.example.barbershop.R
import com.squareup.picasso.Picasso

class ServiceAdapter(private val data: List<ObjectServices>) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val image : TextView = itemView.findViewById(R.id.image)


        val cardView: CardView = itemView.findViewById(R.id.service_card_views)
        val logo_img : ImageView =itemView.findViewById(R.id.service_image)
        val nombre : TextView =itemView.findViewById(R.id.service_nombre)
        val precio : TextView = itemView.findViewById(R.id.service_precio)
        val duracion : TextView =itemView.findViewById(R.id.service_duracion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_card_view, parent, false)
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
        holder.duracion.text = "Duracion: " + service.duracion
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
