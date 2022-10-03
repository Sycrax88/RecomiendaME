package com.colosoft.recomiendame.ui.surprise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.recomiendame.data.RestaurantRepository
import com.colosoft.recomiendame.server.model.Restaurant
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch
import kotlin.random.Random

class SurpriseViewModel : ViewModel() {

    private val restaurantRepository = RestaurantRepository()

    private val _restaurantLoaded : MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    val restaurantLoaded: LiveData<ArrayList<Restaurant>> = _restaurantLoaded

    fun getSurpriseRestaurant() {
        viewModelScope.launch {
            val restaurantsList: ArrayList<Restaurant> = ArrayList()
            val restaurantsListAux: ArrayList<Restaurant> = ArrayList()
            val querySnapshot = restaurantRepository.getSurpriseRestaurant()
            if(restaurantsListAux.size == 0) {
                for (document in querySnapshot) {
                    val restaurant: Restaurant = document.toObject<Restaurant>()
                    restaurantsListAux.add(restaurant)
                }
            }
            println("En el viewmodel de Surprise: $restaurantsListAux")
            restaurantsList.add(restaurantsListAux.random())
            println("En el viewmodel de Surprise Restaurante Elegido: $restaurantsList")
            _restaurantLoaded.postValue(restaurantsList)
        }
    }
}