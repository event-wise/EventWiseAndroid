package com.example.eventwise.screens.createevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateEventViewModelFactory(private val groupId: Long)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateEventViewModel::class.java)) {
            return CreateEventViewModel(groupId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
