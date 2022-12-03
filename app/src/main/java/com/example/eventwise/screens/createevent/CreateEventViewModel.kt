package com.example.eventwise.screens.createevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.EventSaveModel
import kotlinx.coroutines.launch

class CreateEventViewModel(
    private val groupId: Long,
    private val createEventRepository: CreateEventRepository = CreateEventRepository()
) : ViewModel() {

    val eventName: MutableLiveData<String> = MutableLiveData<String>()
    val eventTime: MutableLiveData<String> = MutableLiveData<String>()
    val eventLocation: MutableLiveData<String> = MutableLiveData<String>()
    val eventType: MutableLiveData<String> = MutableLiveData<String>()
    val eventDescription: MutableLiveData<String> = MutableLiveData<String>()


    fun createEvent(){
        viewModelScope.launch {
            createEventRepository.createEvent(
                EventSaveModel(
                    eventId = 0,
                    eventName = eventName.value.orEmpty(),
                    dateTime = eventTime.value.orEmpty(),
                    location = eventTime.value.orEmpty(),
                    type = eventType.value.orEmpty(),
                    description = eventDescription.value.orEmpty(),
                    groupId = groupId
                )
            )
        }

    }
}
