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

            if (request.code() !in 200..299) {
                errorMessage.value = request.errorBody().toString()
                success.value = false
            } else {
                success.value = true
                errorMessage.value = null
            }
        } catch (e: Exception) {
            Log.e("Login", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }
}