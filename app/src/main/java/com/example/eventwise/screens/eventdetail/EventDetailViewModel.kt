package com.example.eventwise.screens.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.EventDetailsModel
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val eventId: Long,
    private val eventDetailRepository: EventDetailRepository = EventDetailRepository()
) : ViewModel() {

    var eventDetail: MutableLiveData<EventDetailsModel> = MutableLiveData()

    val memberList: MutableLiveData<List<String>?> = MutableLiveData()

    val eventName = Transformations.map(eventDetail){
        eventDetail.value?.eventName
    }
    val eventDescription = Transformations.map(eventDetail){
        "Description: " + eventDetail.value?.description
    }
    val eventLocation = Transformations.map(eventDetail){
        "Location: " + eventDetail.value?.location
    }
    val eventTime = Transformations.map(eventDetail){
        "Time: " + eventDetail.value?.dateTime
    }
    val eventType = Transformations.map(eventDetail){
        "Type: " + eventDetail.value?.type
    }

    init {
        retrieveEventDetail()
    }

    private fun retrieveEventDetail(){
        viewModelScope.launch {
            eventDetail.value = eventDetailRepository.eventDetailInformation(eventId)
            memberList.value = eventDetail.value?.acceptedMembers
        }
    }

    fun acceptEvent(){
        viewModelScope.launch {
            eventDetailRepository.acceptEvent(eventId)
        }
    }

    fun rejectEvent(){
        viewModelScope.launch {
            eventDetailRepository.rejectEvent(eventId)
        }
    }

}
