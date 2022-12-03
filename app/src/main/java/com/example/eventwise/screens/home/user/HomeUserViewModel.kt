package com.example.eventwise.screens.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.ProfileUpdateRequestModel
import com.example.eventwise.models.UserModel
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            _userModel.value = homeUserRepository.getProfileInformation()
        }
    }

    fun updateProfileInformation(){
        viewModelScope.launch {
            homeUserRepository.updateProfile(ProfileUpdateRequestModel(
                displayedName = _userModel.value?.displayedName,
                location = _userModel.value?.location,
            ))
        }
        retrieveUserInformation()
    }

    fun logOut(){
        viewModelScope.launch {
            homeUserRepository.logOut()
        }
    }

}
