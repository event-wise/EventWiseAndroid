package com.example.eventwise.models

import com.example.eventwise.helperfunctions.dateTimeCorrectFormat

data class EventsModel(
    var dateTime: String?,
    var eventName: String?,
    var id: Long
) {
    val formattedDateTime: String?
        get() = dateTimeCorrectFormat(dateTime)
}
