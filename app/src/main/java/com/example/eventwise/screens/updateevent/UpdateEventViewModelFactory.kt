package com.example.eventwise.screens.updateevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateEventViewModelFactory(private val eventId: Long, private val groupId: Long)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateEventViewModel::class.java)) {
            return UpdateEventViewModel(eventId, groupId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
