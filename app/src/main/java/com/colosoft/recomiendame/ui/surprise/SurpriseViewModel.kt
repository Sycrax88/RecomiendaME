package com.colosoft.recomiendame.ui.surprise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SurpriseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is surprise Fragment"
    }
    val text: LiveData<String> = _text
}