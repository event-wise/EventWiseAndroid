package com.example.eventwise.creategroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateGroupViewModel(
    private val repository: CreateGroupRepository = CreateGroupRepository()
) : ViewModel() {

    val groupName: MutableLiveData<String> = MutableLiveData<String>()
    val groupDescription: MutableLiveData<String> = MutableLiveData<String>()
    val groupLocation: MutableLiveData<String> = MutableLiveData<String>()
}
