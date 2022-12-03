package com.example.eventwise.screens.updateevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun signUp(){
        viewModelScope.launch {
            updateEventRepository.updateEvent(
                eventName = eventName.value.orEmpty(),
                eventId = eventId,
                location = eventLocation.value.orEmpty(),
                type = eventType.value.orEmpty(),
                description = eventDescription.value.orEmpty(),
                dateTime = eventTime.value.orEmpty(),
                groupId = groupId
            )
        }
    }
}
