package com.colosoft.recomiendame.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colosoft.recomiendame.R
import com.colosoft.recomiendame.databinding.OpinionHomeItemBinding
import com.colosoft.recomiendame.databinding.OpinionItemBinding
import com.colosoft.recomiendame.server.model.Opinion
import java.util.ArrayList

class OpinionsHomeAdapter (
    private val opinionsList: ArrayList<Opinion>,
    private val onItemClicked: (Opinion) -> Unit
        ): RecyclerView.Adapter<OpinionsHomeAdapter.OpinionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpinionsHomeAdapter.OpinionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.opinion_home_item, parent, false)
        return OpinionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OpinionViewHolder, position: Int) {
        val opinion = opinionsList[position]
        holder.bindOpinion(opinion)
        holder.itemView.setOnClickListener{ onItemClicked(opinionsList[position])}
    }

    override fun getItemCount(): Int = opinionsList.size

    fun appendItems(newList: ArrayList<Opinion>){
        opinionsList.clear()
        opinionsList.addAll(newList)
        notifyDataSetChanged()
    }

    class OpinionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = OpinionHomeItemBinding.bind(itemView)

        fun bindOpinion(opinion: Opinion){
            with(binding){
                restaurantNameHomeTextView.text = opinion.restaurant_name
                userNameTextView.text = opinion.user_name
                userLastNameTextView.text = opinion.user_last_name
                ratingLabelTextView.text = opinion.rating.toString()
                opinionTextView.text = opinion.text
            }
        }
    }
}