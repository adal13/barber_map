package com.map.barbershop.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.map.barbershop.R
import com.map.barbershop.ui.Api.entity.ObjectLocals

class LocalAdapter(
    private val data: List<ObjectLocals>,
    private val onItemClickListener: (ObjectLocals) -> Unit
    ) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

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

//        val url_img = "https://proyecto-ti.com/appbarberweb/";
//        val imageView = holder.logo_img
//        val imageUrl = url_img + local.logo
        Glide.with(holder.itemView.context)
            .load(buildImageUrl(local.logo))
            .into(holder.logo_img)
//        Picasso.get()
//            .load(imageUrl)
//            .into(imageView)
        holder.itemView.setOnClickListener { onItemClickListener(local) }

        holder.nombre.text = local.nombre
        holder.estado.text = "Estado: " + local.estado
        holder.municipio.text = "Municipio: " + local.municipio
        holder.calle.text = "Calle: " + local.calle
        holder.ubicacion.text = "Ubicación: " + local.ubicacion
    }



    private fun buildImageUrl(imagePath: String?): String {
        val baseUrl = "https://proyecto-ti.com/appbarberweb/"
        return Uri.parse(baseUrl).buildUpon()
            .appendPath(imagePath)
            .toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
