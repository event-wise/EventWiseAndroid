package com.example.eventwise.screens.updateevent

import android.util.Log
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
        try {
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
            if (request.code() in 200..299) {
                errorMessage.value = request.errorBody().toString()
                success.value = request.body()?.success
                if (success.value == false) {
                    errorMessage.value = request.body()?.message.toString()
                }
            } else {
                success.value = false
                errorMessage.value = request.errorBody().toString()

                try {
                    errorMessage.value = request.errorBody()?.let {
                        GatewayApi.errorConverter.convert(it)?.messages?.joinToString("\n")
                    }
                } catch (e: Exception) {
                    errorMessage.value = e.message
                }
            }
        }  catch (e: Exception) {
            Log.e("UpdateEvent", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }

    suspend fun deleteEvent(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        eventId: Long
    ){
        try {
            val request = GatewayApi.gatewayService.deleteEvent(eventId)
            if (request.code() in 200..299) {
                errorMessage.value = request.errorBody().toString()
                success.value = request.body()?.success
                if (success.value == false) {
                    errorMessage.value = request.body()?.message.toString()
                }
            } else {
                success.value = false
                errorMessage.value = request.errorBody().toString()

                try {
                    errorMessage.value = request.errorBody()?.let {
                        GatewayApi.errorConverter.convert(it)?.messages?.joinToString("\n")
                    }
                } catch (e: Exception) {
                    errorMessage.value = e.message
                }
            }
        } catch (e: Exception) {
            Log.e("UpdateEvent", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }

    suspend fun getEventDetails(eventId: Long) : EventDetailsModel? {
        return try {
            GatewayApi.gatewayService.getEventDetails(eventId = eventId).body()
        } catch (e: Exception) {
            Log.e("UpdateEvent", e.toString())
            null
        }
    }
}
