package com.example.eventwise.models

data class LoginResponseModel(
    var authType: String?,
    var email: String?,
    var id: Long,
    var roles: List<String>?,
    var token: String?,
    var username: String?
)
