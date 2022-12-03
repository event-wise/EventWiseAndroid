package com.example.eventwise.screens.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpRepository: SignUpRepository = SignUpRepository()
) : ViewModel() {

    val username: MutableLiveData<String> = MutableLiveData<String>()
    val email: MutableLiveData<String> = MutableLiveData<String>()
    val displayedName: MutableLiveData<String> = MutableLiveData<String>()
    val location: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()
    val passwordConfirmation: MutableLiveData<String> = MutableLiveData<String>()

    fun signup(){
        viewModelScope.launch {
            signUpRepository.signup(
                displayedName = displayedName.value.orEmpty(),
                username = username.value.orEmpty(),
                email = email.value.orEmpty(),
                location = location.value.orEmpty(),
                password = password.value.orEmpty()
            )
        }
    }
}
