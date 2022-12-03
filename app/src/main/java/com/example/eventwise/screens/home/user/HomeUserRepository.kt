package com.example.eventwise.screens.home.user

import com.example.eventwise.models.ProfileUpdateRequestModel
import com.example.eventwise.models.UserModel
import com.example.eventwise.services.GatewayApi

class HomeUserRepository {

    suspend fun getProfileInformation() : UserModel? {
        return GatewayApi.gatewayService.getProfile().body()
    }

    suspend fun updateProfile(profileUpdateRequestModel: ProfileUpdateRequestModel) {
        GatewayApi.gatewayService.updateProfile(profileUpdateRequestModel)
    }

    suspend fun logOut() {
        GatewayApi.gatewayService.logout()
    }
}
