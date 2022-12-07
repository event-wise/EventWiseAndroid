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

    val displayedUsername = MutableLiveData<String>()
    val location = MutableLiveData<String>()

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)

    init {
        retrieveUserInformation()
    }

    fun retrieveUserInformation(){
        viewModelScope.launch {
            _userModel.value = homeUserRepository.getProfileInformation(
                errorMessage
            )
            displayedUsername.value = _userModel.value?.displayedName.orEmpty()
            location.value = _userModel.value?.location.orEmpty()
        }
    }

    fun updateProfileInformation(){
        viewModelScope.launch {
            homeUserRepository.updateProfile(
                errorMessage,
                ProfileUpdateRequestModel(
                    displayedName = displayedUsername.value,
                    location = location.value,
                )
            )
            retrieveUserInformation()
        }
    }

    fun logOut(){
        viewModelScope.launch {
            homeUserRepository.logOut(
                errorMessage
            )
        }
    }

    fun deleteAccount(){
        viewModelScope.launch {
            homeUserRepository.deleteAccount(
                errorMessage
            )
        }
    }

}
