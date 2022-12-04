package com.example.eventwise.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginActivityViewModel(
    private val loginActivityRepository: LoginActivityRepository = LoginActivityRepository()
) : ViewModel() {

    val username: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    fun login(){
        viewModelScope.launch {
            loginActivityRepository.login(
                success,
                errorMessage,
                username.value.orEmpty(),
                password.value.orEmpty()
            )
        }
    }
}
