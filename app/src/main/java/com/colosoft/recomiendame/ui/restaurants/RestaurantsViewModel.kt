package com.colosoft.recomiendame.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "En este fragment se mostrará la lista de restaurantes," +
                " donde se mostrará la información básica de ellos, como el nombre, la foto, la etiqueta de tipo" +
                " de restaurante, y la calificacion general, además se podrá ingresar individualmente a cada uno" +
                " por medio de un click, para poder evaluar o ver las evaluaciones del restaurante."
    }
    val text: LiveData<String> = _text
}