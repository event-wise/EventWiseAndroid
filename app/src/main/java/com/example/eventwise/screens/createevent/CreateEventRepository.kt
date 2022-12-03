package com.example.eventwise.screens.createevent

import com.example.eventwise.models.EventSaveModel
import com.example.eventwise.services.GatewayApi

class CreateEventRepository {

    suspend fun createEvent(eventSaveModel: EventSaveModel) {
        GatewayApi.gatewayService.createEvent(eventSaveModel)
    }
}
