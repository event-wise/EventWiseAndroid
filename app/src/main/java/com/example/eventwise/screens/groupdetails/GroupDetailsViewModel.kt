package com.example.eventwise.screens.groupdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.EventsModel
import com.example.eventwise.models.GroupDetailsModel
import kotlinx.coroutines.launch

class GroupDetailsViewModel(
    private val groupId: Long,
    private val groupDetailRepository: GroupDetailRepository = GroupDetailRepository()
) : ViewModel() {

    val isDeleted: MutableLiveData<Boolean> = MutableLiveData(false)

    private val groupDetailsModel: MutableLiveData<GroupDetailsModel> = MutableLiveData()

    val groupName: MutableLiveData<String> = MutableLiveData()
    val groupDescription: MutableLiveData<String> = MutableLiveData()
    val groupLocation: MutableLiveData<String> = MutableLiveData()

    val isGroupOwner = MutableLiveData(false)

    val groupMembersList: MutableLiveData<List<String>> = MutableLiveData()
    val activeEventsList: MutableLiveData<List<EventsModel>> = MutableLiveData()
    val logsList: MutableLiveData<List<String>> = MutableLiveData()

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getGroupDetails()
    }

    fun getGroupDetails(){
        viewModelScope.launch {
            groupDetailsModel.value  = groupDetailRepository.getGroupDetails(groupId)
            if (groupDetailsModel.value == null){
                isDeleted.value = true
            }
            groupDetailsModel.value?.events?.forEach {
                it.groupId = groupId
            }
            groupName.value = groupDetailsModel.value?.groupName.orEmpty()
            groupDescription.value = "Description: " + groupDetailsModel.value?.description.orEmpty()
            groupLocation.value = "Location: " + groupDetailsModel.value?.location.orEmpty()
            isGroupOwner.value = groupDetailsModel.value?.owner ?: false
            groupMembersList.value =  groupDetailsModel.value?.members
            activeEventsList.value = groupDetailsModel.value?.events
            logsList.value = groupDetailsModel.value?.logs
        }
    }

    fun exitFromGroup(){
        viewModelScope.launch {
            groupDetailRepository.exitFromGroup(success, errorMessage, groupId)
        }
    }
}
