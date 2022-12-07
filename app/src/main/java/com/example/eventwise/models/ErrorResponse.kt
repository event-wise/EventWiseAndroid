package com.example.eventwise.models

data class ErrorResponse(
    val path: String?,
    val messages: List<String>?
)
