package com.example.eventwise.screens.home.events

import com.example.eventwise.models.EventModel
import java.time.Instant

class HomeEventsRepository {

    fun retrieveMockData() : List<EventModel> {
        return listOf(
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null),
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null),
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null),
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null),
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null),
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null),
            EventModel(1, "Deneme", location = "Uskudar", null, null, null, null),
            EventModel(1, "Deneme2", location = "Alamanya", null, null, null, null),
            EventModel(1, "Deneme3", location = "Ingiltere", null, null, null, null)
        )
    }
}
