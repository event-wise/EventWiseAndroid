package com.example.eventwise.screens.updategroup

import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.models.GroupSaveModel
import com.example.eventwise.services.GatewayApi

class UpdateGroupRepository {

    suspend fun updateGroup(
        description: String,
        groupId: Long,
        location: String,
        groupName: String
    ){
        GatewayApi.gatewayService.updateGroup(
            GroupSaveModel(
                description = description,
                groupId = groupId,
                location = location,
                groupName = groupName
            )
        )
    }

    suspend fun getGroupInformation(groupId: Long) : GroupDetailsModel? {
        return GatewayApi.gatewayService.getGroupDetails(groupId = groupId).body()
    }
}
