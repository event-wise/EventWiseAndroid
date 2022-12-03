package com.example.eventwise.screens.signup

import com.example.eventwise.models.RegisterRequestModel
import com.example.eventwise.services.GatewayApi

class SignUpRepository {

    suspend fun signup(
        displayedName: String,
        email: String,
        location: String,
        password: String,
        username: String
    ){
        GatewayApi.gatewayService.register(
            RegisterRequestModel(
                displayedName = displayedName,
                email = email,
                location = location,
                password = password,
                username = username
            )
        )
    }
}
