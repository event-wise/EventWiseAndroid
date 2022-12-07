package com.example.eventwise.screens.home.user

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.ProfileUpdateRequestModel
import com.example.eventwise.models.UserModel
import com.example.eventwise.services.GatewayApi

class HomeUserRepository {

    suspend fun getProfileInformation(
        errorMessage: MutableLiveData<String>
    ) : UserModel? {
        val request = GatewayApi.gatewayService.getProfile()
        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
        } else {
            errorMessage.value = null
        }
        return request.body()
    }

    suspend fun updateProfile(
        errorMessage: MutableLiveData<String>,
        profileUpdateRequestModel: ProfileUpdateRequestModel
    ) {
        val request = GatewayApi.gatewayService.updateProfile(profileUpdateRequestModel)
        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
        } else {
            errorMessage.value = null
        }
    }

    suspend fun logOut(
        errorMessage: MutableLiveData<String>
    ) {
        val request = GatewayApi.gatewayService.logout()
        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
        } else {
            errorMessage.value = null
        }
    }

    suspend fun deleteAccount(
        errorMessage: MutableLiveData<String>
    ){
        val request = GatewayApi.gatewayService.deleteAccount()
        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
        } else {
            errorMessage.value = null
        }
    }
}
