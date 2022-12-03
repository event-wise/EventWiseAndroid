package com.example.eventwise.models

data class PasswordChangeRequest(
    var id: Long?,
    var currentPassword: String?,
    var newPassword: String?,
    var confirmPassword: String?
)
