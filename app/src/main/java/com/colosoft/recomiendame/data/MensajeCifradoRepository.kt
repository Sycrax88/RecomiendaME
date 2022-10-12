package com.colosoft.recomiendame.data

import android.util.Log
import com.colosoft.recomiendame.server.model.Mensaje
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

class MensajeCifradoRepository {
    private var db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth

    /*suspend fun getMessagesFrom(resturantId: String): QuerySnapshot {
        db = FirebaseFirestore.getInstance()
        return db.collection("opinions").whereEqualTo("restaurant_id",resturantId).get().await()
    } */
    suspend fun getAllMessages(): QuerySnapshot {
        db = FirebaseFirestore.getInstance()
        return db.collection("mensajes").get().await()
    }

     suspend fun createMessage(mensaje: Mensaje): ResourceRemote<String?>{
         return try {
             mensaje.id?.let { db.collection("mensajes").document(it).set(mensaje).await() }
             ResourceRemote.Success(data = mensaje.id)
         }catch (e: FirebaseFirestoreException){
             Log.e("Mensaje Creado ",e.localizedMessage)
             ResourceRemote.Error(message = e.localizedMessage)
         }catch (e: FirebaseNetworkException) {
             ResourceRemote.Error(message = e.localizedMessage)
         }

    }
}