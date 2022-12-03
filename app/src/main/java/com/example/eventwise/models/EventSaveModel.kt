package com.example.eventwise.models

data class EventSaveModel(
    var dateTime: String?,
    var description: String?,
    var eventId: Long,
    var eventName: String?,
    var groupId: Long,
    var location: String?,
    var type: String?
)
