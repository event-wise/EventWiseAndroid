package com.example.eventwise.screens.updateevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.helperfunctions.dateToInstantConverter
import com.example.eventwise.helperfunctions.instantToDateConverter
import kotlinx.coroutines.launch

class UpdateEventViewModel(
    private val eventId: Long,
    private val groupId: Long,
    private val updateEventRepository: UpdateEventRepository = UpdateEventRepository()
): ViewModel() {

    val eventName: MutableLiveData<String> = MutableLiveData<String>()
    val eventTime: MutableLiveData<String> = MutableLiveData<String>()
    val eventLocation: MutableLiveData<String> = MutableLiveData<String>()
    val eventType: MutableLiveData<String> = MutableLiveData<String>()
    val eventDescription: MutableLiveData<String> = MutableLiveData<String>()
    val eventOwner : MutableLiveData<Boolean> = MutableLiveData(true)

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getEventDetails()
    }

    fun updateEvent(){
        viewModelScope.launch {
            updateEventRepository.updateEvent(
                success,
                errorMessage,
                eventName = eventName.value.orEmpty(),
                eventId = eventId,
                location = eventLocation.value.orEmpty(),
                type = eventType.value.orEmpty(),
                description = eventDescription.value.orEmpty(),
                dateTime = dateToInstantConverter(eventTime.value.orEmpty()),
                groupId = groupId
            )
        }
    }

    fun deleteEvent(){
        viewModelScope.launch {
            updateEventRepository.deleteEvent(
                success, errorMessage, eventId
            )
        }
    }

    private fun getEventDetails(){
        viewModelScope.launch {
            val eventDetails = updateEventRepository.getEventDetails(eventId = eventId)
            eventName.value = eventDetails?.eventName.orEmpty()
            eventTime.value = instantToDateConverter(eventDetails?.dateTime.orEmpty())
            eventLocation.value = eventDetails?.location.orEmpty()
            eventType.value = eventDetails?.type.orEmpty()
            eventDescription.value = eventDetails?.description.orEmpty()
            eventOwner.value = eventDetails?.organizer
        }
    }
}
