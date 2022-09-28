package com.colosoft.recomiendame.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class OpinionRepository {
    private var db = Firebase.firestore

    suspend fun getOpinions(resturantId: String): QuerySnapshot {
        db = FirebaseFirestore.getInstance()
        return db.collection("opinions").whereEqualTo("restaurant_id",resturantId).get().await()
    }
}