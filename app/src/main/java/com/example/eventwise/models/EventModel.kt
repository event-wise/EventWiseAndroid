package com.example.eventwise.models

import java.time.Instant

data class EventModel (
    var id: Long,
    var name: String?,
    var location: String?,
    var description: String?,
    var dateTime: String?,
    var type: String?,
    var creationTime: Instant?
    )

enum class EventType {

}
