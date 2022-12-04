package com.example.eventwise.screens.updategroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UpdateGroupViewModel(
    private val groupId: Long,
    private val updateGroupRepository: UpdateGroupRepository = UpdateGroupRepository()
): ViewModel() {

    val groupName: MutableLiveData<String> = MutableLiveData<String>()
    val groupDescription: MutableLiveData<String> = MutableLiveData<String>()
    val groupLocation: MutableLiveData<String> = MutableLiveData<String>()

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getGroupInformation()
    }

    private fun getGroupInformation(){
        viewModelScope.launch {
            val groupDetails = updateGroupRepository.getGroupInformation(groupId = groupId)
            groupName.value = groupDetails?.groupName.orEmpty()
            groupDescription.value = groupDetails?.description.orEmpty()
            groupLocation.value = groupDetails?.location.orEmpty()
        }
    }

    fun updateGroup(){
        viewModelScope.launch {
            updateGroupRepository.updateGroup(
                success,
                errorMessage,
                description = groupDescription.value.orEmpty(),
                location = groupLocation.value.orEmpty(),
                groupName = groupName.value.orEmpty(),
                groupId = groupId
            )
        }
    }
}