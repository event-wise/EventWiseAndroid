package com.example.eventwise.screens.eventdetail

import com.example.eventwise.models.EventDetailsModel
import com.example.eventwise.services.GatewayApi

class EventDetailRepository {

    suspend fun eventDetailInformation(eventId: Long) : EventDetailsModel? {
        return try {
            GatewayApi.gatewayService.getEventDetails(eventId).body()
        } catch (e: java.lang.Exception){
            null
        }
    }

    suspend fun acceptEvent(eventId: Long) {
        GatewayApi.gatewayService.acceptEvent(eventId)
    }

    suspend fun rejectEvent(eventId: Long) {
        GatewayApi.gatewayService.rejectEvent(eventId)
    }
}
