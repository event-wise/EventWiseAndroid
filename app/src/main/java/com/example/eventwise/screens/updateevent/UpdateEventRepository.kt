package com.example.eventwise.screens.updateevent

import com.example.eventwise.models.EventSaveModel
import com.example.eventwise.services.GatewayApi

class UpdateEventRepository {

    suspend fun updateEvent(
        dateTime: String,
        description: String,
        eventId: Long,
        eventName: String,
        groupId: Long,
        location: String?,
        type: String?
    ){
        GatewayApi.gatewayService.updateEvent(
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
    }
}
