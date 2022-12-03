package com.example.eventwise.models

data class GroupSaveModel(
    var description: String?,
    var groupId: Long,
    var groupName: String?,
    var location: String?,
    var ownerId: Long
)
