package com.colosoft.recomiendame.data

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.server.model.RestaurantList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RestaurantRepository {

    private var db = Firebase.firestore
    //private var auth: FirebaseAuth = Firebase.auth

     suspend fun getRestaurants(): QuerySnapshot {
        val restaurantsList = ArrayList<Restaurant>()
        db = FirebaseFirestore.getInstance()
        return db.collection("restaurant").get().await()
          /*  .addOnSuccessListener {
                if (!it.isEmpty){
                    for (data in it.documents){
                        val restaurant: Restaurant? = data.toObject(Restaurant::class.java)
                        if (restaurant != null){
                            restaurantsList.add(restaurant)
                        }
                    }
                    println("En el repositorio: "+ restaurantsList.size)
                }
            }
            .addOnFailureListener{
                println("No funcionó.")
            } */
    }
    }
