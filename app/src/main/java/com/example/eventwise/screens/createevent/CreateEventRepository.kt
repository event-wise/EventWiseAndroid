package com.example.eventwise.screens.createevent

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.EventSaveModel
import com.example.eventwise.services.GatewayApi

class CreateEventRepository {

    suspend fun createEvent(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        eventSaveModel: EventSaveModel
    ) {
        val request = GatewayApi.gatewayService.createEvent(eventSaveModel)

        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = false
        } else {
            success.value = true
            errorMessage.value = null
        }
    }
}
