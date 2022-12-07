package com.example.eventwise.screens.signup

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.ErrorResponse
import com.example.eventwise.models.RegisterRequestModel
import com.example.eventwise.services.GatewayApi
import okhttp3.ResponseBody
import retrofit2.Converter


class SignUpRepository {

    suspend fun signup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        displayedName: String,
        email: String,
        location: String,
        password: String,
        passwordConfirmation: String,
        username: String
    ){
        val request = GatewayApi.gatewayService.register(
            RegisterRequestModel(
                displayedName = displayedName,
                email = email,
                location = location,
                password = password,
                confirmPassword = passwordConfirmation,
                username = username
            )
        )
        if (request.code() in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = request.body()?.success
            if (success.value == false){
                errorMessage.value = request.body()?.message.toString()
            }
        } else {
            success.value = false
            errorMessage.value = request.errorBody().toString()

            try {
                errorMessage.value = request.errorBody()?.let {
                    GatewayApi.errorConverter.convert(it)?.messages?.joinToString( "\n") }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}
