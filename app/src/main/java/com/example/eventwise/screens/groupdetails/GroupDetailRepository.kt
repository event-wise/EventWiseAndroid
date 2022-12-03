package com.example.eventwise.screens.groupdetails

import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.services.GatewayApi

class GroupDetailRepository {

    suspend fun getGroupDetails(groupId: Long) : GroupDetailsModel? {
        return GatewayApi.gatewayService.getGroupDetails(groupId).body()
    }

}
