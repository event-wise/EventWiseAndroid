package com.example.eventwise.screens.home.events

import android.util.Log
import com.example.eventwise.models.EventsModel
import com.example.eventwise.services.GatewayApi
class HomeEventsRepository {

    suspend fun listUserEvents() : List<EventsModel> {
        return try {
            GatewayApi.gatewayService.listUserEvents().body().orEmpty()
        } catch (e: Exception){
            Log.e("HomeEvents", e.toString())
            listOf()
        }
    }

}
