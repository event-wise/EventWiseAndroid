package com.example.eventwise.screens.home.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.ProfileUpdateRequestModel
import com.example.eventwise.models.UserModel
import com.example.eventwise.services.GatewayApi

class HomeUserRepository {

    suspend fun getProfileInformation(
        errorMessage: MutableLiveData<String>
    ) : UserModel? {
        return try {
            val request = GatewayApi.gatewayService.getProfile()
            if (request.code() !in 200..299) {
                errorMessage.value = request.errorBody().toString()
            } else {
                errorMessage.value = null
            }
            request.body()
        } catch (e: Exception){
            Log.e("HomeUser", e.toString())
            null
        }
    }

    suspend fun updateProfile(
        errorMessage: MutableLiveData<String>,
        profileUpdateRequestModel: ProfileUpdateRequestModel
    ) {
        try {
            val request = GatewayApi.gatewayService.updateProfile(profileUpdateRequestModel)
            if (request.code() !in 200..299) {
                errorMessage.value = request.errorBody().toString()
            } else {
                errorMessage.value = null
            }
        } catch (e: Exception){
            Log.e("HomeUser", e.toString())
        }
    }

    suspend fun logOut(
        errorMessage: MutableLiveData<String>
    ) {
        try {
            val request = GatewayApi.gatewayService.logout()
            if (request.code() !in 200..299) {
                errorMessage.value = request.errorBody().toString()
            } else {
                errorMessage.value = null
            }
        } catch (e: Exception){
            Log.e("HomeUser", e.toString())
        }
    }

    suspend fun deleteAccount(
        errorMessage: MutableLiveData<String>
    ){
        try {
            val request = GatewayApi.gatewayService.deleteAccount()
            if (request.code() !in 200..299) {
                errorMessage.value = request.errorBody().toString()
            } else {
                errorMessage.value = null
            }
        } catch (e: Exception){
            Log.e("HomeUser", e.toString())
        }
    }
}
