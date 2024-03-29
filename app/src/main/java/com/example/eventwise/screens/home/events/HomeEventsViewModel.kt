package com.example.eventwise.screens.home.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.EventsModel
import kotlinx.coroutines.launch

class HomeEventsViewModel (
    private val homeEventsRepository: HomeEventsRepository = HomeEventsRepository()
    ) : ViewModel() {

    private val _eventList = MutableLiveData<List<EventsModel>>()

    val haveAnyEvent: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        refreshEventList()
    }

    val eventList: LiveData<List<EventsModel>>
        get() = _eventList

    fun refreshEventList(){
        viewModelScope.launch {
            _eventList.value = homeEventsRepository.listUserEvents()
            haveAnyEvent.value = _eventList.value.orEmpty().isNotEmpty()
        }
    }
}
