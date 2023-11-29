package com.map.barbershop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.map.barbershop.R
import com.map.barbershop.ui.Api.entity.ObjectLocals
import com.squareup.picasso.Picasso

class LocalAdapter(private val data: List<ObjectLocals>) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val image : TextView = itemView.findViewById(R.id.image)

        val cardView: CardView = itemView.findViewById(R.id.card_views)
        val logo_img : ImageView =itemView.findViewById(R.id.logo_local)
        val nombre : TextView =itemView.findViewById(R.id.nombre)
        val estado : TextView = itemView.findViewById(R.id.estado)
        val municipio : TextView =itemView.findViewById(R.id.municipio)
        val calle : TextView =itemView.findViewById(R.id.calle)
        val ubicacion : TextView = itemView.findViewById(R.id.ubicacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.local_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val local = data[position]

        val url_img = "https://proyecto-ti.com/appbarberweb/";
        val imageView = holder.logo_img
        val imageUrl = url_img + local.logo

        Picasso.get()
            .load(imageUrl)
            .into(imageView)

        holder.nombre.text = local.nombre
        holder.estado.text = "Estado: " + local.estado
        holder.municipio.text = "Municipio: " + local.municipio
        holder.calle.text = "Calle: " + local.calle
        holder.ubicacion.text = "Ubicación: " + local.ubicacion
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
