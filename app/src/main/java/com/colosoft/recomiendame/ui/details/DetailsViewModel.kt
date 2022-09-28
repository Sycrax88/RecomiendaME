package com.colosoft.recomiendame.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.recomiendame.data.OpinionRepository
import com.colosoft.recomiendame.server.model.Opinion
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val opinionRepository = OpinionRepository()

    private val _opinionsLoaded : MutableLiveData<ArrayList<Opinion>> = MutableLiveData()
    val opinionsLoaded: LiveData<ArrayList<Opinion>> = _opinionsLoaded
    var opinionsList: ArrayList<Opinion> = ArrayList()

    fun getOpinions(restaurantId: String) {
        viewModelScope.launch {
            val querySnapshot = opinionRepository.getOpinions(restaurantId)
            println("En el viewmodel antes del IF de opiniones: "+ opinionsList.size)
            if(opinionsList.size == 0) {
                for (document in querySnapshot) {
                    val opinion: Opinion = document.toObject<Opinion>()
                    opinionsList.add(opinion)
                }
            }
            println("En el viewmodel después del IF de opiniones: "+ opinionsList.size)

            _opinionsLoaded.postValue(opinionsList)
        }
    }
}