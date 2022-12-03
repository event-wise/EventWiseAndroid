package com.example.eventwise.screens.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangePasswordViewModel(
    private val changePasswordRepository: ChangePasswordRepository = ChangePasswordRepository()
) : ViewModel() {

    val password: MutableLiveData<String> = MutableLiveData<String>()
    val newPassword: MutableLiveData<String> = MutableLiveData<String>()
    val newPasswordConfirmation: MutableLiveData<String> = MutableLiveData<String>()

}
