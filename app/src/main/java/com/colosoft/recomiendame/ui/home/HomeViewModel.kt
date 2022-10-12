package com.colosoft.recomiendame.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colosoft.recomiendame.data.MensajeCifradoRepository
import com.colosoft.recomiendame.server.model.Mensaje
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var db = Firebase.firestore
    private val mensajeCifradoRepository = MensajeCifradoRepository()

    private val _mensajesLoaded : MutableLiveData<ArrayList<Mensaje>> = MutableLiveData()
    val mensajesLoaded: LiveData<ArrayList<Mensaje>> = _mensajesLoaded


    fun getMensajes() {
        viewModelScope.launch {
            val mensajesList: ArrayList<Mensaje> = ArrayList()
            val querySnapshot = mensajeCifradoRepository.getAllMessages()
            println("En el viewmodel Home antes del IF de mensajes: "+ mensajesList.size)
            if(mensajesList.size == 0) {
                for (document in querySnapshot) {
                    val mensaje: Mensaje = document.toObject<Mensaje>()
                    mensajesList.add(mensaje)
                }
            }
            println("En el viewmodel Home después del IF de mensajes: "+ mensajesList.size)

            _mensajesLoaded.postValue(mensajesList)
        }
    }

}