package com.example.eventwise.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventwise.models.UserModel

class HomeUserViewModel(
    private val homeUserRepository: HomeUserRepository = HomeUserRepository()
) : ViewModel() {

    private val _userModel = MutableLiveData<UserModel>()

    val userModel: LiveData<UserModel>
        get() = _userModel

    init {
        retrieveUserInformation()
    }

    private fun retrieveUserInformation(){
        _userModel.value = homeUserRepository.retrieveUserInformation()
    }
}
