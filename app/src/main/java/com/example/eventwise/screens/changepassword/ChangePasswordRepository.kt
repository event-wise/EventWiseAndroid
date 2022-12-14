package com.example.eventwise.screens.changepassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.PasswordChangeRequestModel
import com.example.eventwise.services.GatewayApi
import okio.use

class ChangePasswordRepository {

    // TODO: add try catch to everywhere

    suspend fun changePassword(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        currentPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        try {
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

                try {
                    errorMessage.value = request.errorBody()?.let {
                        GatewayApi.errorConverter.convert(it)?.messages?.joinToString( "\n") }
                } catch (e: Exception) {
                    errorMessage.value = e.message
                }
            }
        } catch (e: Exception){
            Log.e("ChangePassword", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }

}
