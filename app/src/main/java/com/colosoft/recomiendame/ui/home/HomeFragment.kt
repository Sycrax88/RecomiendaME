package com.colosoft.recomiendame.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.recomiendame.databinding.FragmentHomeBinding
import com.colosoft.recomiendame.databinding.FragmentRestaurantsBinding
import com.colosoft.recomiendame.server.model.Opinion
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.ui.details.OpinionsAdapter
import com.colosoft.recomiendame.ui.restaurants.RestaurantsAdapter
import com.colosoft.recomiendame.ui.restaurants.RestaurantsFragmentDirections
import com.colosoft.recomiendame.ui.restaurants.RestaurantsViewModel

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private var opinionsList: ArrayList<Opinion> = ArrayList()
    private lateinit var opinionsHomeAdapter: OpinionsHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        val root: View = homeBinding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("Se creó el HomeFragment.")

        super.onViewCreated(view, savedInstanceState)

        opinionsHomeAdapter = OpinionsHomeAdapter(opinionsList, onItemClicked = {(it)})

        homeBinding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = opinionsHomeAdapter
            setHasFixedSize(false)
        }

        homeViewModel.opinionsLoaded.observe(viewLifecycleOwner){ result ->
            println("En el fragment de Home: "+ result.size)
            onOpinionsLoadedSubscribe(result)
        }

        homeViewModel.getOpinions()
    }

     /*private fun onOpinionItemClicked(opinion: Opinion) {
        homeViewModel.restaurantLoaded.observe(viewLifecycleOwner){ result ->
            println("En el fragment de Home: $result")
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationDetails(
                result!!
            ))
        }

    }*/
    private fun onOpinionsLoadedSubscribe(opinionsList: ArrayList<Opinion>?) {
        opinionsList?.let { opinionsList ->
            opinionsHomeAdapter.appendItems(opinionsList)
        }
    }
}