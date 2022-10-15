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


    fun createMessage(message: String, keyByUser: Int) {
       // viewModelScope.launch {
            db = FirebaseFirestore.getInstance()
            val uid = auth.currentUser?.uid.toString()
            println("Current uid del ViewModel del opinion: $uid")

            db.collection("users").document(uid).get().addOnSuccessListener { documentSnapshot ->
            val actualUser = documentSnapshot.toObject<User>()
            println("Actual user en print: $actualUser")
            val codedMessage = codeMessage(message,keyByUser)
            val opinionLocal = hashMapOf(
                "clave" to keyByUser,
                "mensaje_cifrado" to codedMessage,
                "user_id" to auth.currentUser?.uid.toString(),
                "user_last_name" to actualUser?.lastName,
                "user_name" to actualUser?.name
            )
            db.collection("mensajes").add(opinionLocal)
        }
    }

    private fun codeMessage(message: String, key: Int): String{
        val alphabetListLowerCase = listOf("a", "b", "c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u"
            ,"v","w","x","y","z")
        val alphabetListCapitalLetter = listOf("A", "B", "C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U"
            ,"V","W","X","Y","Z")
        println("Tamaño alfabeto: " + alphabetListLowerCase.size)

        var finalMessage = ""
        //PARA CIFRAR
        for (c in message){
            var isCapitalLetter = false
            var indexAlphabet = alphabetListLowerCase.indexOf(c.toString())
            if (indexAlphabet == null || indexAlphabet == -1){
                indexAlphabet = alphabetListCapitalLetter.indexOf(c.toString())
                isCapitalLetter = true
            }
            println("Caracter: $c")
            val newCharIndex = indexAlphabet + (key%27)
            println("Índice caracter: $indexAlphabet")
            println("Índice del nuevo caracter: $newCharIndex")
            if (isCapitalLetter){
                finalMessage += alphabetListCapitalLetter[newCharIndex]
            }else{
                finalMessage += alphabetListLowerCase[newCharIndex]
            }
            println("Mensaje: $finalMessage")
        }
        return finalMessage
    }

  /*  fun updateRestaurantRating(restaurant: Restaurant, rateByUser: Double){
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
            val totalOpinions= opinionsList.size + 1
            println("Cantidad de opiniones: $totalOpinions")

                var ratingSummatory = 0.0
                for (item in opinionsList){
                    ratingSummatory += item.rating!!
                }
            ratingSummatory += rateByUser
            println("Sumatoria de opiniones: $ratingSummatory")
                val ratingMean = ratingSummatory/totalOpinions
                println("Nuevo promedio: $ratingMean")

                val ratingMeanMap = hashMapOf(
                    "rating" to ratingMean,
                    "numRating" to totalOpinions
                )
                db.collection("restaurant")
                    .document(restaurantId).update(ratingMeanMap as Map<String, Any>)

        }
    }*/
    }
