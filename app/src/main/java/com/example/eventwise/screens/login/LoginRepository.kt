package com.example.eventwise.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.LoginRequestModel
import com.example.eventwise.services.Constants
import com.example.eventwise.services.GatewayApi

class LoginRepository {

    suspend fun login(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        username: String,
        password: String
    ){
        try {
            val request = GatewayApi.gatewayService.login(
                LoginRequestModel(username, password)
            )
            Constants.BEARER_TOKEN = request.body()?.token.orEmpty()
            Constants.GLOBAL_USER_ID = request.body()?.id ?: 0

            if (request.code() in 200..299) {
                errorMessage.value = request.errorBody().toString()
                success.value = true
            } else {
                success.value = false
                errorMessage.value = request.errorBody().toString()

                try {
                    errorMessage.value = request.errorBody()?.let {
                        GatewayApi.responseConverter.convert(it)?.message
                    }.orEmpty()
                } catch (e: Exception) {
                    errorMessage.value = e.message
                }
            }
        } catch (e: Exception) {
            Log.e("Login", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }
}