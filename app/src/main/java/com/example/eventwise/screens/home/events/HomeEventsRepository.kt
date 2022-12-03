package com.example.eventwise.screens.home.events

import android.widget.Toast
import com.example.eventwise.models.EventModel
import com.example.eventwise.models.EventsModel
import com.example.eventwise.services.Constants
import com.example.eventwise.services.GatewayApi
import retrofit2.Call
import retrofit2.Response
import java.time.Instant

class HomeEventsRepository {

    suspend fun listUserEvents() : List<EventsModel>? {
        return GatewayApi.gatewayService.listUserEvents(Constants.GLOBAL_USER_ID).body().orEmpty()
    }

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
