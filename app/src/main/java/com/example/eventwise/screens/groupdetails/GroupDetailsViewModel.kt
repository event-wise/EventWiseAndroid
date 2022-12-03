package com.example.eventwise.screens.groupdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.*
import kotlinx.coroutines.launch

class GroupDetailsViewModel(
    private val groupId: Long,
    private val groupDetailRepository: GroupDetailRepository = GroupDetailRepository()
) : ViewModel() {

    private val groupDetailsModel: MutableLiveData<GroupDetailsModel> = MutableLiveData()

    val groupDescription = Transformations.map(groupDetailsModel){
        "Description: " + it?.description
    }
    val groupLocation = Transformations.map(groupDetailsModel){
        "Location: " + it?.location
    }

    val groupMembersList: List<String>?
        get() = groupDetailsModel.value?.members

    val activeEventsList: List<EventsModel>?
        get() = groupDetailsModel.value?.events

    val logsList: List<String>?
        get() = groupDetailsModel.value?.logs


    init {
        getGroupDetails()
    }

    private fun getGroupDetails(){
        viewModelScope.launch {
            groupDetailsModel.value = groupDetailRepository.getGroupDetails(groupId)
            groupDetailsModel.value?.events?.forEach {
                it.groupId = groupId
            }
        }
    }

}
