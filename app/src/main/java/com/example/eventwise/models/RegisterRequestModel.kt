package com.example.eventwise.models

data class RegisterRequestModel(
    var displayedName: String?,
    var email: String?,
    var location: String?,
    var password: String?,
    var username: String?
)
