package com.example.eventwise.screens.eventdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    suspend fun acceptEvent(
        errorMessage: MutableLiveData<String>,
        eventId: Long
    ) {
        try {
            val request = GatewayApi.gatewayService.acceptEvent(eventId)
            errorMessage.value = request.body()?.message
        } catch (e: Exception){
            Log.e("EventDetail", e.toString())
            return
        }
    }

    suspend fun rejectEvent(
        errorMessage: MutableLiveData<String>,
        eventId: Long
    ) {
        try {
            val request = GatewayApi.gatewayService.rejectEvent(eventId)
            errorMessage.value = request.body()?.message
        } catch (e: Exception){
            Log.e("EventDetail", e.toString())
            return
        }
    }
}
