package com.example.eventwise.screens.updateevent

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.EventDetailsModel
import com.example.eventwise.models.EventSaveModel
import com.example.eventwise.services.GatewayApi

class UpdateEventRepository {

    suspend fun updateEvent(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        dateTime: String,
        description: String,
        eventId: Long,
        eventName: String,
        groupId: Long,
        location: String?,
        type: String?
    ){
        val request = GatewayApi.gatewayService.updateEvent(
            EventSaveModel(
                dateTime = dateTime,
                description = description,
                eventId = eventId,
                eventName = eventName,
                groupId = groupId,
                location = location,
                type = type
            )
        )
        if (request.code() in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = request.body()?.success
            if (success.value == false){
                errorMessage.value = request.body()?.message.toString()
            }
        } else {
            success.value = false
            errorMessage.value = request.errorBody().toString()
        }
    }

    suspend fun deleteEvent(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        eventId: Long
    ){
        val request = GatewayApi.gatewayService.deleteEvent(eventId)
        if (request.code() in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = request.body()?.success
            if (success.value == false){
                errorMessage.value = request.body()?.message.toString()
            }
        } else {
            success.value = false
            errorMessage.value = request.errorBody().toString()
        }
    }

    suspend fun getEventDetails(eventId: Long) : EventDetailsModel? {
        return GatewayApi.gatewayService.getEventDetails(eventId = eventId).body()
    }
}
