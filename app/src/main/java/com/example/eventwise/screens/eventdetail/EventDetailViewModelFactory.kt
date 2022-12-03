package com.example.eventwise.screens.eventdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventDetailViewModelFactory(private val eventId: Long)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)) {
            return EventDetailViewModel(eventId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}