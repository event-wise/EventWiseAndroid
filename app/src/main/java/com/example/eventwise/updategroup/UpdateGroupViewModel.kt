package com.example.eventwise.updategroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpdateGroupViewModel(
    private val updateGroupRepository: UpdateGroupRepository = UpdateGroupRepository()
): ViewModel() {

    val groupName: MutableLiveData<String> = MutableLiveData<String>()
    val groupDescription: MutableLiveData<String> = MutableLiveData<String>()
    val groupLocation: MutableLiveData<String> = MutableLiveData<String>()

}