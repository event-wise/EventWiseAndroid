package com.example.eventwise.screens.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.helperfunctions.instantToDateConverter
import com.example.eventwise.models.EventDetailsModel
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val eventId: Long,
    private val eventDetailRepository: EventDetailRepository = EventDetailRepository()
) : ViewModel() {

    var eventDetail: MutableLiveData<EventDetailsModel> = MutableLiveData()

    val memberList: MutableLiveData<List<String>?> = MutableLiveData()

    val eventName = Transformations.map(eventDetail){
        it.eventName
    }
    val eventDescription = Transformations.map(eventDetail){
        "Description: " + it.description
    }
    val eventLocation = Transformations.map(eventDetail){
        "Location: " + it.location
    }
    val eventTime = Transformations.map(eventDetail){
        "Time: " + instantToDateConverter(it.dateTime.orEmpty())
    }
    val eventType = Transformations.map(eventDetail){
        "Type: " + it.type
    }
    val eventOwner : MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        retrieveEventDetail()
    }

    fun retrieveEventDetail(){
        viewModelScope.launch {
            eventDetail.value = eventDetailRepository.eventDetailInformation(eventId)
            eventOwner.value = eventDetail.value?.organizer
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
