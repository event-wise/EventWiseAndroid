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

    private val groupDetailsModel: MutableLiveData<GroupDetailsModel> = MutableLiveData()

    val groupDescription = Transformations.map(groupDetailsModel){
        "Description: " + it?.description
    }
    val groupLocation = Transformations.map(groupDetailsModel){
        "Location: " + it?.location
    }

    val groupMembersList: MutableLiveData<List<String>> = MutableLiveData()

    val activeEventsList: MutableLiveData<List<EventsModel>> = MutableLiveData()

    val logsList: MutableLiveData<List<String>> = MutableLiveData()


    init {
        getGroupDetails()
    }

    fun getGroupDetails(){
        viewModelScope.launch {
            groupDetailsModel.value  = groupDetailRepository.getGroupDetails(groupId)
            groupDetailsModel.value?.events?.forEach {
                it.groupId = groupId
            }
            groupMembersList.value =  groupDetailsModel.value?.members
            activeEventsList.value = groupDetailsModel.value?.events
            logsList.value = groupDetailsModel.value?.logs
        }
    }

}
