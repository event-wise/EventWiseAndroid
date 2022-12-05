package com.example.eventwise.models

data class EventDetailsModel(
    var accepted: Boolean,
    var acceptedMembers: List<String>?,
    var dateTime: String,
    var description: String,
    var eventId: Long,
    var eventName: String,
    var groupId: Long,
    var location: String,
    var organizerId: Long,
    var type: String
)
