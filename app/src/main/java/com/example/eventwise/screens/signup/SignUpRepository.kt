package com.example.eventwise.screens.signup

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.RegisterRequestModel
import com.example.eventwise.services.GatewayApi

class SignUpRepository {

    suspend fun signup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        displayedName: String,
        email: String,
        location: String,
        password: String,
        username: String
    ){
        val request = GatewayApi.gatewayService.register(
            RegisterRequestModel(
                displayedName = displayedName,
                email = email,
                location = location,
                password = password,
                username = username
            )
        )
        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = request.body()?.success
            if (success.value == false){
                errorMessage.value = request.body()?.message.toString()
            }
        } else {
            success.value = true
            errorMessage.value = null
        }
    }
}
