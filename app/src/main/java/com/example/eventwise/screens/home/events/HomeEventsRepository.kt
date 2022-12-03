package com.example.eventwise.screens.home.events

import com.example.eventwise.models.EventsModel
import com.example.eventwise.services.GatewayApi
class HomeEventsRepository {

    suspend fun listUserEvents() : List<EventsModel> {
        return GatewayApi.gatewayService.listUserEvents().body().orEmpty()
    }

}
