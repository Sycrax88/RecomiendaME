package com.colosoft.recomiendame.ui.restaurants

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.colosoft.recomiendame.R
import com.colosoft.recomiendame.databinding.RestaurantItemBinding
import com.colosoft.recomiendame.server.model.Restaurant
import com.squareup.picasso.Picasso
import java.util.ArrayList

class RestaurantsAdapter (
    private val restaurantsList: ArrayList<Restaurant>,
    private val onItemClicked: (Restaurant) -> Unit
    ) : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item,parent,false)
            return RestaurantViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
            val restaurant = restaurantsList[position]
            holder.bindRestaurant(restaurant)
            holder.itemView.setOnClickListener{ onItemClicked(restaurantsList[position])}
        }

        override fun getItemCount(): Int = restaurantsList.size

        fun appendItems(newList: ArrayList<Restaurant>){
            restaurantsList.clear()
            restaurantsList.addAll(newList)
            notifyDataSetChanged()
        }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = RestaurantItemBinding.bind(itemView)

        fun bindRestaurant(restaurant: Restaurant){
            with(binding){
                restaurantTitleTextView.text = restaurant.name
                locationTextView.text = restaurant.restaurantLocation
                ratingLabelTextView.text = restaurant.rating.toString()
                if (restaurant.urlPhoto != null)
                    Picasso.get().load(restaurant.urlPhoto).into(posterImageView)
            }
        }
    }
}