package com.colosoft.recomiendame.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.recomiendame.data.RestaurantRepository
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.server.model.RestaurantList
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class RestaurantsViewModel : ViewModel() {

    private val restaurantRepository = RestaurantRepository()

    private val _restaurantsLoaded : MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    val restaurantsLoaded: LiveData<ArrayList<Restaurant>> = _restaurantsLoaded
    var restaurantsList: ArrayList<Restaurant> = ArrayList()

    fun getRestaurants() {
        viewModelScope.launch {
            val querySnapshot = restaurantRepository.getRestaurants()
            println("En el repositorio antes del IF : "+ restaurantsList.size)
            if(restaurantsList.size == 0) {
                for (document in querySnapshot) {
                    val restaurant: Restaurant = document.toObject<Restaurant>()
                    restaurantsList.add(restaurant)
                }
            }
            println("En el viewmodel: $restaurantsList")
            _restaurantsLoaded.postValue(restaurantsList)
        }
    }
}