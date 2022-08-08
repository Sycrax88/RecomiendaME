package com.colosoft.recomiendame.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "En este fragment será la página principal, aqui se mostrarán" +
                " elementos como noticias, promociones de restaurantes o los restaurantes más valorados por semana" +
                " será un espacio para que haya información general de restaurantes"
    }
    val text: LiveData<String> = _text
}