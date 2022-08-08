package com.colosoft.recomiendame.ui.surprise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SurpriseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "En este fragment se mostrarán recomendaciones de restaurantes" +
                " diarios, tomandose al azar pero tomando en cuenta un promedio de valoracion decente para" +
                " ser mostrado a los usuarios. Pueden mostrarse de 1 a 3 restaurantes diarios."
    }
    val text: LiveData<String> = _text
}