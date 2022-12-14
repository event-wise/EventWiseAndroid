package com.example.eventwise.models

data class ErrorResponse(
    val path: String?,
    val messages: List<String>?
)

data class Response(
    val success: Boolean?,
    val message: String?
)
