package com.example.eventwise.screens.updategroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateGroupViewModelFactory(private val groupId: Long)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateGroupViewModel::class.java)) {
            return UpdateGroupViewModel(groupId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
