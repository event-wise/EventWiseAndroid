package com.example.eventwise.screens.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.helperfunctions.instantToDateConverter
import com.example.eventwise.models.EventDetailsModel
import com.example.eventwise.services.Constants
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val eventId: Long,
    private val eventDetailRepository: EventDetailRepository = EventDetailRepository()
) : ViewModel() {

    val isDeleted: MutableLiveData<Boolean> = MutableLiveData(false)

    var eventDetail: MutableLiveData<EventDetailsModel> = MutableLiveData()

    val memberList: MutableLiveData<List<String>?> = MutableLiveData()

    val eventName: MutableLiveData<String> = MutableLiveData()
    val eventDescription: MutableLiveData<String> = MutableLiveData()
    val eventLocation: MutableLiveData<String> = MutableLiveData()
    val eventTime: MutableLiveData<String> = MutableLiveData()
    val eventType: MutableLiveData<String> = MutableLiveData()

    val eventOwner : MutableLiveData<Boolean> = MutableLiveData(false)

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)

    init {
        retrieveEventDetail()
    }

    fun retrieveEventDetail(){
        viewModelScope.launch {
            eventDetail.value = eventDetailRepository.eventDetailInformation(eventId)
            if (eventDetail.value == null){
                isDeleted.value = true
            }
            eventName.value = eventDetail.value?.eventName.orEmpty()
            eventDescription.value = "Description: " + eventDetail.value?.description.orEmpty()
            eventLocation.value = "Location: " + eventDetail.value?.location.orEmpty()
            eventTime.value = "Time: " + instantToDateConverter(eventDetail.value?.dateTime.orEmpty())
            eventType.value = "Type: " + eventDetail.value?.type.orEmpty()
            eventOwner.value = eventDetail.value?.organizer ?: false
            memberList.value = eventDetail.value?.acceptedMembers ?: listOf()
        }
    }

    fun acceptEvent(){
        viewModelScope.launch {
            eventDetailRepository.acceptEvent(errorMessage, eventId)
            retrieveEventDetail()
        }
    }

    fun rejectEvent(){
        viewModelScope.launch {
            eventDetailRepository.rejectEvent(errorMessage, eventId)
            retrieveEventDetail()
        }
    }

}
