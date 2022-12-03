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

    fun login(){
        viewModelScope.launch {
            loginActivityRepository.login(username.value.orEmpty(), password.value.orEmpty())
        }
    }
}
