package com.example.eventwise.models

import com.example.eventwise.helperfunctions.instantToDateConverter


data class EventsModel(
    var dateTime: String,
    var eventName: String,
    var id: Long,
    var groupId: Long? = 0
) {
    val formattedDateTime: String
        get() = instantToDateConverter(dateTime)
}
