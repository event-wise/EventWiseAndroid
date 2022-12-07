package com.example.eventwise.screens.changepassword

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.PasswordChangeRequestModel
import com.example.eventwise.services.GatewayApi

class ChangePasswordRepository {

    suspend fun changePassword(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        currentPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        val request = GatewayApi.gatewayService.changePassword(
            PasswordChangeRequestModel(
                currentPassword,
                newPassword,
                newPasswordConfirmation
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
        }
    }

}
