package com.colosoft.recomiendame.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colosoft.recomiendame.R
import com.colosoft.recomiendame.databinding.MensajeItemBinding
import com.colosoft.recomiendame.databinding.OpinionHomeItemBinding
import com.colosoft.recomiendame.databinding.OpinionItemBinding
import com.colosoft.recomiendame.server.model.Mensaje
import com.colosoft.recomiendame.server.model.Opinion
import java.util.ArrayList

class MensajesHomeAdapter (
    private val mensajesList: ArrayList<Mensaje>,
    private val onItemClicked: (Mensaje) -> Unit
        ): RecyclerView.Adapter<MensajesHomeAdapter.MensajeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajesHomeAdapter.MensajeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mensaje_item, parent, false)
        return MensajeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MensajeViewHolder, position: Int) {
        val mensaje = mensajesList[position]
        holder.bindMensaje(mensaje)
        holder.itemView.setOnClickListener{ onItemClicked(mensajesList[position])}
    }

    override fun getItemCount(): Int = mensajesList.size

    fun appendItems(newList: ArrayList<Mensaje>){
        mensajesList.clear()
        mensajesList.addAll(newList)
        notifyDataSetChanged()
    }

    class MensajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = MensajeItemBinding.bind(itemView)

        fun bindMensaje(mensaje: Mensaje){
            with(binding){
                userNameTextView.text = mensaje.user_name
                userLastNameTextView.text = mensaje.user_last_name
                mensajeTextView.text = mensaje.mensaje_cifrado
            }
        }
    }
}