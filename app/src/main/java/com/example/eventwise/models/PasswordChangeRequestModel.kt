package com.example.eventwise.models

data class PasswordChangeRequestModel(
    var currentPassword: String?,
    var newPassword: String?,
    var confirmPassword: String?
)
