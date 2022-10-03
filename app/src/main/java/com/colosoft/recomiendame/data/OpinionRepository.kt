package com.colosoft.recomiendame.data

import android.util.Log
import com.colosoft.recomiendame.server.model.Opinion
import com.colosoft.recomiendame.server.model.Restaurant
import com.colosoft.recomiendame.server.model.User
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class OpinionRepository {
    private var db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth

    suspend fun getOpinions(resturantId: String): QuerySnapshot {
        db = FirebaseFirestore.getInstance()
        return db.collection("opinions").whereEqualTo("restaurant_id",resturantId).get().await()
    }
    suspend fun getAllOpinions(): QuerySnapshot {
        db = FirebaseFirestore.getInstance()
        return db.collection("opinions").get().await()
    }

     suspend fun createOpinion(opinion: Opinion): ResourceRemote<String?>{
         return try {
             opinion.id?.let { db.collection("opinions").document(it).set(opinion).await() }
             ResourceRemote.Success(data = opinion.id)
         }catch (e: FirebaseFirestoreException){
             Log.e("Opinion Created ",e.localizedMessage)
             ResourceRemote.Error(message = e.localizedMessage)
         }catch (e: FirebaseNetworkException) {
             ResourceRemote.Error(message = e.localizedMessage)
         }

    }
}