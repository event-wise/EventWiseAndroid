package com.example.eventwise.models

data class ProfileUpdateRequestModel(
    var displayedName: String?,
    var email: String?,
    var id: Long?,
    var location: String?
)
