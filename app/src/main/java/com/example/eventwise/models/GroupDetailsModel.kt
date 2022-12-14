package com.example.eventwise.models

data class GroupDetailsModel(
    var description: String,
    var events: List<EventsModel>,
    var groupName: String,
    var id: Long,
    var location: String,
    var logs: List<String>,
    var members: List<String>,
    var owner: Boolean
)
