package com.colosoft.recomiendame.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colosoft.recomiendame.data.ResourceRemote
import com.colosoft.recomiendame.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    val registerSuccess: LiveData<String?> = _registerSuccess

    fun validateFields(name: String, lastName: String,phone: String, email: String, password: String, repPassword: String) {
        if (name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || repPassword.isEmpty())
            _errorMsg.value = "Debe digitar todos los campos."
        else
            if (password != repPassword)
                _errorMsg.value = "Las contraseñas deben de ser iguales."
            else
                if (password.length < 6)
                    _errorMsg.value = "La contraseña debe tener al menos 6 caracteres."
                else
                    GlobalScope.launch(Dispatchers.IO) {
                        val result = userRepository.registerUser(email, password)
                        result.let { resourceRemote ->
                            when (resourceRemote){
                                is ResourceRemote.Success -> {
                                    _registerSuccess.postValue(result.data)
                                    _errorMsg.postValue("Registro Exitoso.")
                                }
                                is ResourceRemote.Error -> {
                                    var msg = result.message
                                    when (result.message) {
                                        "The email address is already in use by another account." -> msg = "Ya existe una cuenta con ese correo electrónico."
                                        "The email address is badly formatted." -> msg = "El correo electrónico está mal escrito."
                                        "A network error (suck as timeout, interrupted connection or unreachable host) has occurred." -> msg = "Revise su conexión de Internet."
                                    }
                                    _errorMsg.postValue(msg)
                                }
                                else -> {
                                    //don't use
                                }
                            }
                        }
                    }


    }
}