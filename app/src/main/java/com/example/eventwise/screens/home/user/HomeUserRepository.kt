package com.example.eventwise.screens.home.user

import com.example.eventwise.models.UserModel

class HomeUserRepository {

    fun retrieveUserInformation() : UserModel {
        return UserModel("afk", "Ahmet Furkan Kavraz", "Tuebingen")
    }
}
