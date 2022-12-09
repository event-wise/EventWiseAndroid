package com.example.eventwise.screens.eventdetail

import android.util.Log
import com.example.eventwise.models.EventDetailsModel
import com.example.eventwise.services.GatewayApi

class EventDetailRepository {

    suspend fun eventDetailInformation(eventId: Long) : EventDetailsModel? {
        return try {
            GatewayApi.gatewayService.getEventDetails(eventId).body()
        } catch (e: java.lang.Exception){
            Log.e("EventDetail", e.toString())
            null
        }
    }

    suspend fun acceptEvent(eventId: Long) {
        try {
            GatewayApi.gatewayService.acceptEvent(eventId)
        } catch (e: Exception){
            Log.e("EventDetail", e.toString())
            return
        }
    }

    suspend fun rejectEvent(eventId: Long) {
        try {
            GatewayApi.gatewayService.rejectEvent(eventId)
        } catch (e: Exception){
            Log.e("EventDetail", e.toString())
            return
        }
    }
}
