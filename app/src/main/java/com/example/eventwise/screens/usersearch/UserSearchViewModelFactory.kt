package com.example.eventwise.screens.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserSearchViewModelFactory(private val groupId: Long)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserSearchViewModel::class.java)) {
            return UserSearchViewModel(groupId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
