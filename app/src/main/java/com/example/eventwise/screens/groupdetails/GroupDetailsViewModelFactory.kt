package com.example.eventwise.screens.groupdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroupDetailsViewModelFactory(private val groupId: Long)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupDetailsViewModel::class.java)) {
            return GroupDetailsViewModel(groupId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
