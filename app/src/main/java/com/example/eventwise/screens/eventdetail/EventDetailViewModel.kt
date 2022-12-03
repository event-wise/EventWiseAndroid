package com.example.eventwise.screens.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.eventwise.models.EventModel
import com.example.eventwise.models.MemberModel

class EventDetailViewModel(
    private val eventDetailRepository: EventDetailRepository = EventDetailRepository()
) : ViewModel() {

    val memberList: MutableLiveData<List<MemberModel>> = MutableLiveData<List<MemberModel>>()

    private val event: MutableLiveData<EventModel> = MutableLiveData()

    val eventName = Transformations.map(event){
        it?.name
    }
    val eventDescription = Transformations.map(event){
        "Description: " + it?.description
    }
    val eventLocation = Transformations.map(event){
        "Location: " + it?.location
    }
    val eventTime = Transformations.map(event){
        "Time: " + it?.dateTime
    }
    val eventType = Transformations.map(event){
        "Type: " + it?.type
    }

    init {
        event.value = eventDetailRepository.createMockData()
        memberList.value = eventDetailRepository.createMockDataForMembers()
    }

}
