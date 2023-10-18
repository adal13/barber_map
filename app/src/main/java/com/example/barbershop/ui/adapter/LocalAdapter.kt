package com.example.barbershop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.Api.entity.ObjectLocals
import com.example.barbershop.R

class LocalAdapter(private val data: List<ObjectLocals>) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val image : TextView = itemView.findViewById(R.id.image)

        val cardView: CardView = itemView.findViewById(R.id.card_views)
        //val logo_img : TextView =itemView.findViewById(R.id.image)
        val nombre : TextView =itemView.findViewById(R.id.nombre)
        val direccion : TextView = itemView.findViewById(R.id.direccion)
        val ubicacion : TextView =itemView.findViewById(R.id.ubicacion)
        val created_at : TextView =itemView.findViewById(R.id.created_at)
        val update_at : TextView = itemView.findViewById(R.id.update_at)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.local_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val local = data[position]
        //holder.image.text = local.logo
        // holder.logo_img.text = local.logo
        holder.nombre.text = local.nombre
        holder.direccion.text = "Dirección: " + local.direccion
        holder.ubicacion.text = "Ubicacion: " + local.ubicacion
        holder.created_at.text = "Creacion: " + local.created_at
        holder.update_at.text = "Actualizacion: " + local.updated_at
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
