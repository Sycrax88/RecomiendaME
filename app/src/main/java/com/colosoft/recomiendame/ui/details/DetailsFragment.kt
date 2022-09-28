package com.colosoft.recomiendame.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.colosoft.recomiendame.R
import com.colosoft.recomiendame.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var detailsBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = args.restaurant


        //detailsViewModel.searchRestaurant(restaurant.id)

            if (restaurant.rating!! >= 1.0 && restaurant.rating!! < 1.5){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
            }
            else if (restaurant.rating!! >= 1.5 && restaurant.rating!! < 2.0){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_half))
            }
            else if (restaurant.rating!! >= 2.0 && restaurant.rating!! < 2.5){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
            }
            else if (restaurant.rating!! >= 2.5 && restaurant.rating!! < 3.0){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_half))
            }
            else if (restaurant.rating!! >= 3.0 && restaurant.rating!! < 3.5){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
            }
            else if (restaurant.rating!! >= 3.5 && restaurant.rating!! < 4.0){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_half))
            }
            else if (restaurant.rating!! >= 4.0 && restaurant.rating!! < 4.5){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star4ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
            }
            else if (restaurant.rating!! >= 4.5 && restaurant.rating!! < 5.0){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star4ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star4ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_half))
            }
            else if (restaurant.rating!! == 5.0){
                detailsBinding.star1ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star2ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star3ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star4ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                detailsBinding.star4ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
            }



        with(detailsBinding){
            restaurantNameTextView.text = restaurant.name
            locationTextView.text = restaurant.restaurantLocation
            ratingTextView.text = restaurant.rating.toString()
            if (restaurant.urlPhoto != null)
                Picasso.get().load(restaurant.urlPhoto).into(posterImageView)

        }
    }
}