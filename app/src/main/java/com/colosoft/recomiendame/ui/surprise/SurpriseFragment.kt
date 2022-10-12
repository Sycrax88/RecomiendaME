package com.colosoft.recomiendame.ui.surprise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.recomiendame.databinding.FragmentRestaurantsBinding
import com.colosoft.recomiendame.databinding.FragmentSurpriseBinding
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.ui.restaurants.RestaurantsAdapter
import com.colosoft.recomiendame.ui.restaurants.RestaurantsFragmentDirections
import com.colosoft.recomiendame.ui.restaurants.RestaurantsViewModel

class SurpriseFragment : Fragment() {

    private lateinit var surpriseBinding: FragmentSurpriseBinding
    private lateinit var surpriseViewModel: SurpriseViewModel
    private var restaurantsList: ArrayList<Restaurant> = ArrayList()
    private lateinit var surpriseRestaurantAdapter: SurpriseRestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        surpriseViewModel = ViewModelProvider(this)[SurpriseViewModel::class.java]
        surpriseBinding = FragmentSurpriseBinding.inflate(inflater,container,false)
        val root: View = surpriseBinding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("Se creó el Fragment Surprise.")

        super.onViewCreated(view, savedInstanceState)

       // surpriseRestaurantAdapter = SurpriseRestaurantAdapter(restaurantsList, onItemClicked = {onRestaurantItemClicked(it)})

        surpriseBinding.surpriseRestaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SurpriseFragment.requireContext())
            adapter = surpriseRestaurantAdapter
            setHasFixedSize(false)
        }

        surpriseViewModel.restaurantLoaded.observe(viewLifecycleOwner){ result ->
            println("En el fragment de Surprise: "+ result.size)
            onRestaurantLoadedSubscribe(result)
        }

        surpriseViewModel.getSurpriseRestaurant()
    }

    /*private fun onRestaurantItemClicked(restaurant: Restaurant) {
        findNavController().navigate(SurpriseFragmentDirections.actionNavigationSurpriseToNavigationDetails(restaurant))
    }*/
    private fun onRestaurantLoadedSubscribe(restaurantsList: ArrayList<Restaurant>?) {
        restaurantsList?.let { restaurantsList ->
            surpriseRestaurantAdapter.appendItems(restaurantsList)
        }
    }
}