package com.example.eventwise.screens.createevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.helperfunctions.dateToInstantConverter
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

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    fun createEvent(){
        viewModelScope.launch {
            createEventRepository.createEvent(
                success,
                errorMessage,
                EventSaveModel(
                    eventId = 0,
                    eventName = eventName.value.orEmpty(),
                    dateTime = dateToInstantConverter(eventTime.value.orEmpty()),
                    location = eventLocation.value.orEmpty(),
                    type = eventType.value.orEmpty(),
                    description = eventDescription.value.orEmpty(),
                    groupId = groupId
                )
            )
        }

    }
}
