package com.example.eventwise.screens.login

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.LoginRequestModel
import com.example.eventwise.services.GatewayApi

class LoginActivityRepository {

    suspend fun login(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        username: String,
        password: String
    ){
        val request = GatewayApi.gatewayService.login(
            LoginRequestModel(username, password)
        )
        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = false
        } else {
            success.value = true
            errorMessage.value = null
        }
    }
}