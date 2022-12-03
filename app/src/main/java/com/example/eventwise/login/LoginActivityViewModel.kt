package com.example.eventwise.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginActivityViewModel(
    private val loginActivityRepository: LoginActivityRepository = LoginActivityRepository()
) : ViewModel() {

    val username: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()



}