package com.example.eventwise.screens.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val changePasswordRepository: ChangePasswordRepository = ChangePasswordRepository()
) : ViewModel() {

    val password: MutableLiveData<String> = MutableLiveData<String>()
    val newPassword: MutableLiveData<String> = MutableLiveData<String>()
    val newPasswordConfirmation: MutableLiveData<String> = MutableLiveData<String>()

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changePassword(){
        viewModelScope.launch {
            changePasswordRepository.changePassword(
                success,
                errorMessage,
                password.value.orEmpty(),
                newPassword.value.orEmpty(),
                newPasswordConfirmation.value.orEmpty()
            )
        }
    }
}
