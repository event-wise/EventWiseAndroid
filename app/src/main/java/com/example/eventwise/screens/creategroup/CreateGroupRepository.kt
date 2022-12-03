package com.example.eventwise.screens.creategroup

import com.example.eventwise.models.GroupSaveModel
import com.example.eventwise.services.GatewayApi

class CreateGroupRepository {

    suspend fun createGroup(groupSaveModel: GroupSaveModel) {
        GatewayApi.gatewayService.createGroup(groupSaveModel)
    }
}
