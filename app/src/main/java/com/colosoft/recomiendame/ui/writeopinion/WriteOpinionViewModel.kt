package com.colosoft.recomiendame.ui.writeopinion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.recomiendame.data.OpinionRepository
import com.colosoft.recomiendame.data.ResourceRemote
import com.colosoft.recomiendame.data.UserRepository
import com.colosoft.recomiendame.server.model.Opinion
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.server.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WriteOpinionViewModel : ViewModel() {

    private var db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth
    private val opinionRepository = OpinionRepository()


    fun createOpinion(restaurant: Restaurant, rateByUser: Double, message: String) {
       // viewModelScope.launch {
            db = FirebaseFirestore.getInstance()
            val uid = auth.currentUser?.uid.toString()
            println("Current uid del ViewModel del opinion: $uid")
            /*val querySnapshot = userRepository.getUser(uid)
            if (userList.size == 0) {
                for (document in querySnapshot) {
                    val user: User = document.toObject<User>()
                    userList.add(user)
                }
            }
            val actualUser = userList[0]
            println("Actual user en print: $actualUser")
*/

            db.collection("users").document(uid).get().addOnSuccessListener { documentSnapshot ->
            val actualUser = documentSnapshot.toObject<User>()
            println("Actual user en print: $actualUser")
            val opinionLocal = hashMapOf(
                "rating" to rateByUser,
                "restaurant_id" to restaurant.id,
                "restaurant_name" to restaurant.name,
                "text" to message,
                "user_id" to auth.currentUser?.uid.toString(),
                "user_last_name" to actualUser?.lastName,
                "user_name" to actualUser?.name
            )
            db.collection("opinions").add(opinionLocal)
        }
    }

    fun updateRestaurantRating(restaurant: Restaurant){
        val restaurantId = restaurant.id.toString()
        val opinionsList: ArrayList<Opinion> = ArrayList()

        viewModelScope.launch {
            val querySnapshot = opinionRepository.getOpinions(restaurantId)
            if(opinionsList.size == 0) {
                for (document in querySnapshot) {
                    val opinion: Opinion = document.toObject<Opinion>()
                    opinionsList.add(opinion)
                }

            }
            val totalOpinions= opinionsList.size

                var ratingSummatory = 0.0
                for (item in opinionsList){
                    ratingSummatory += item.rating!!
                }
                val ratingMean = ratingSummatory/totalOpinions
                println("Nuevo promedio: $ratingMean")

                val ratingMeanMap = hashMapOf(
                    "rating" to ratingMean
                )
                db.collection("restaurant")
                    .document(restaurantId).update(ratingMeanMap as Map<String, Any>)

        }
    }
    }
