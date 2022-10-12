package com.colosoft.recomiendame.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.recomiendame.databinding.FragmentRestaurantsBinding
import com.colosoft.recomiendame.server.model.Restaurant

class RestaurantsFragment : Fragment() {

    private lateinit var restaurantBinding: FragmentRestaurantsBinding
    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private var restaurantsList: ArrayList<Restaurant> = ArrayList()
    private lateinit var restaurantsAdapter: RestaurantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        restaurantsViewModel = ViewModelProvider(this)[RestaurantsViewModel::class.java]
        restaurantBinding = FragmentRestaurantsBinding.inflate(inflater,container,false)
        val root: View = restaurantBinding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("Hola mundo.")

        super.onViewCreated(view, savedInstanceState)

      //  restaurantsAdapter = RestaurantsAdapter(restaurantsList, onItemClicked = {onRestaurantItemClicked(it)})

        restaurantBinding.restaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RestaurantsFragment.requireContext())
            adapter = restaurantsAdapter
            setHasFixedSize(false)
        }

        restaurantsViewModel.restaurantsLoaded.observe(viewLifecycleOwner){ result ->
            println("En el fragment: "+ result.size)
            onRestaurantsLoadedSubscribe(result)
        }

        restaurantsViewModel.getRestaurants()
    }

  /* private fun onRestaurantItemClicked(restaurant: Restaurant) {
        findNavController().navigate(RestaurantsFragmentDirections.actionNavigationRestaurantsToNavigationDetails(restaurant))
    }*/
    private fun onRestaurantsLoadedSubscribe(restaurantsList: ArrayList<Restaurant>?) {
        restaurantsList?.let { restaurantsList ->
            restaurantsAdapter.appendItems(restaurantsList)
        }
    }
}