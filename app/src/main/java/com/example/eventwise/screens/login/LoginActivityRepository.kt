package com.example.eventwise.screens.login

import com.example.eventwise.models.LoginRequestModel
import com.example.eventwise.services.GatewayApi

class LoginActivityRepository {

    suspend fun login(username: String, password: String){
        GatewayApi.gatewayService.login(
            LoginRequestModel(username, password)
        )
    }
}