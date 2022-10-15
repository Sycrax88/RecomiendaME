package com.colosoft.recomiendame.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.recomiendame.databinding.FragmentHomeBinding
import com.colosoft.recomiendame.server.model.Mensaje
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.ui.restaurants.RestaurantsFragmentDirections

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private var mensajesList: ArrayList<Mensaje> = ArrayList()
    private lateinit var mensajesHomeAdapter: MensajesHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = homeBinding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("Se creó el HomeFragment.")

        super.onViewCreated(view, savedInstanceState)

        mensajesHomeAdapter = MensajesHomeAdapter(mensajesList, onItemClicked = { onMensajeItemClicked(it) })

        homeBinding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = mensajesHomeAdapter
            setHasFixedSize(false)
        }

        homeViewModel.mensajesLoaded.observe(viewLifecycleOwner) { result ->
            println("En el fragment de Home: " + result.size)
            onMensajesLoadedSubscribe(result)
        }

        homeViewModel.getMensajes()

        homeBinding.refreshButton.setOnClickListener{
            homeViewModel.mensajesLoaded.observe(viewLifecycleOwner) { result ->
                println("En el fragment de Home: " + result.size)
                onMensajesLoadedSubscribe(result)
            }

            homeViewModel.getMensajes()
        }

        homeBinding.createMessageButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationWriteOpinion())
        }
    }
    private fun onMensajeItemClicked(mensaje: Mensaje) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationDetails(mensaje))
    }
    private fun onMensajesLoadedSubscribe(mensajesList: ArrayList<Mensaje>?) {
        mensajesList?.let { mensajesList ->
            mensajesHomeAdapter.appendItems(mensajesList)
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}