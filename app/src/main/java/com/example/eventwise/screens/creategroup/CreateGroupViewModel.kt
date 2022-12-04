package com.example.eventwise.screens.creategroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.GroupSaveModel
import kotlinx.coroutines.launch

class CreateGroupViewModel(
    private val createGroupRepository: CreateGroupRepository = CreateGroupRepository()
) : ViewModel() {

    val groupName: MutableLiveData<String> = MutableLiveData<String>()
    val groupDescription: MutableLiveData<String> = MutableLiveData<String>()
    val groupLocation: MutableLiveData<String> = MutableLiveData<String>()

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    fun createGroup(){
        viewModelScope.launch {
            createGroupRepository.createGroup(
                success,
                errorMessage,
                GroupSaveModel(
                    description = groupDescription.value.orEmpty(),
                    groupId = 0,
                    groupName = groupName.value.orEmpty(),
                    location = groupLocation.value.orEmpty()
                )
            )
        }

    }
}
