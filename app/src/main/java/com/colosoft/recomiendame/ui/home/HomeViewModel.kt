package com.colosoft.recomiendame.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.recomiendame.data.OpinionRepository
import com.colosoft.recomiendame.data.RestaurantRepository
import com.colosoft.recomiendame.server.model.Opinion
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.server.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var db = Firebase.firestore
    private val opinionRepository = OpinionRepository()

    private val _opinionsLoaded : MutableLiveData<ArrayList<Opinion>> = MutableLiveData()
    val opinionsLoaded: LiveData<ArrayList<Opinion>> = _opinionsLoaded

    private val _restaurantLoaded : MutableLiveData<Restaurant?> = MutableLiveData()
    val restaurantLoaded: MutableLiveData<Restaurant?> = _restaurantLoaded

    fun getOpinions() {
        viewModelScope.launch {
            val opinionsList: ArrayList<Opinion> = ArrayList()
            val querySnapshot = opinionRepository.getAllOpinions()
            println("En el viewmodel Home antes del IF de opiniones: "+ opinionsList.size)
            if(opinionsList.size == 0) {
                for (document in querySnapshot) {
                    val opinion: Opinion = document.toObject<Opinion>()
                    opinionsList.add(opinion)
                }
            }
            println("En el viewmodel Home después del IF de opiniones: "+ opinionsList.size)

            _opinionsLoaded.postValue(opinionsList)
        }
    }

    fun getClickedRestaurant(restaurantId:String) {
        viewModelScope.launch {
            db.collection("restaurant").document(restaurantId).get()
                .addOnSuccessListener { documentSnapshot ->
                    val restaurant = documentSnapshot.toObject<Restaurant>()
                    println("Actual restaurant en print: $restaurant")
                    _restaurantLoaded.postValue(restaurant)
                }

        }
    }
}