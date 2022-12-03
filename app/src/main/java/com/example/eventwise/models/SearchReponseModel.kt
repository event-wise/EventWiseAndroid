package com.example.eventwise.models

data class SearchResponseModel(
    var found: Boolean?,
    var id: Long?,
    var member: Boolean?,
    var username: String?
)
