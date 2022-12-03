package com.example.eventwise.models

data class PasswordChangeRequestModel(
    var id: Long?,
    var currentPassword: String?,
    var newPassword: String?,
    var confirmPassword: String?
)
