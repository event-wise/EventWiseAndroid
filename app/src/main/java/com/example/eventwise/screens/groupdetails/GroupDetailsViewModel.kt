package com.example.eventwise.screens.groupdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.eventwise.models.EventModel
import com.example.eventwise.models.GroupModel
import com.example.eventwise.models.MemberModel

class GroupDetailsViewModel(
    private val groupDetailRepository: GroupDetailRepository = GroupDetailRepository()
) : ViewModel() {

    val group: MutableLiveData<GroupModel> = MutableLiveData()

    val groupDescription = Transformations.map(group){
        "Description: " + it?.description
    }
    val groupLocation = Transformations.map(group){
        "Location: " + it?.location
    }

    val groupMembersList: MutableLiveData<List<MemberModel>> = MutableLiveData()

    val activeEventsList: MutableLiveData<List<EventModel>> = MutableLiveData()

    val logsList: MutableLiveData<List<String>> = MutableLiveData()

    init {
        group.value = groupDetailRepository.generateMockGroup()
        groupMembersList.value = groupDetailRepository.generateMockGroupMembers()
        activeEventsList.value = groupDetailRepository.generateMockActiveEventsList()
        logsList.value = groupDetailRepository.generateMockLogList()
    }

}
