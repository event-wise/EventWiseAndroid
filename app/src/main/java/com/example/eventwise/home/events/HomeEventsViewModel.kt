package com.example.eventwise.home.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventwise.models.EventModel

class HomeEventsViewModel (
    private val homeEventsRepository: HomeEventsRepository = HomeEventsRepository()
    ) : ViewModel() {

    private val _eventList = MutableLiveData<List<EventModel>>()

    init {
        refreshEventList()
    }

    val eventList: LiveData<List<EventModel>>
        get() = _eventList

    private fun refreshEventList(){
        _eventList.value = homeEventsRepository.retrieveMockData()
    }
}
