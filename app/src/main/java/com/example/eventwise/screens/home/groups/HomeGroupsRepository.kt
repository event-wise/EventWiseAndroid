package com.example.eventwise.screens.home.groups

import com.example.eventwise.models.GroupsModel
import com.example.eventwise.services.GatewayApi

class HomeGroupsRepository {

    suspend fun listUserGroups() : List<GroupsModel> {
        return GatewayApi.gatewayService.listUserGroups().body().orEmpty()
    }

}
