package com.example.eventwise.screens.changepassword

import com.example.eventwise.models.PasswordChangeRequestModel
import com.example.eventwise.services.GatewayApi

class ChangePasswordRepository {

    suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        GatewayApi.gatewayService.changePassword(
            PasswordChangeRequestModel(
                currentPassword,
                newPassword,
                newPasswordConfirmation
            )
        )
    }

}
