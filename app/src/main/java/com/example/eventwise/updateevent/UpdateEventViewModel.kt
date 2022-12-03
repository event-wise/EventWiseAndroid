package com.example.eventwise.updateevent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpdateEventViewModel(
    private val repository: UpdateEventRepository = UpdateEventRepository()
): ViewModel() {

    val eventName: MutableLiveData<String> = MutableLiveData<String>()
    val eventTime: MutableLiveData<String> = MutableLiveData<String>()
    val eventLocation: MutableLiveData<String> = MutableLiveData<String>()
    val eventType: MutableLiveData<String> = MutableLiveData<String>()
    val eventDescription: MutableLiveData<String> = MutableLiveData<String>()
}
