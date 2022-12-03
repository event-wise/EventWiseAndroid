package com.example.eventwise.screens.createevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateEventViewModel(
    private val createEventRepository: CreateEventRepository = CreateEventRepository()
) : ViewModel() {

    val eventName: MutableLiveData<String> = MutableLiveData<String>()
    val eventTime: MutableLiveData<String> = MutableLiveData<String>()
    val eventLocation: MutableLiveData<String> = MutableLiveData<String>()
    val eventType: MutableLiveData<String> = MutableLiveData<String>()
    val eventDescription: MutableLiveData<String> = MutableLiveData<String>()

}
