package com.example.eventwise.screens.home.groups

import android.util.Log
import com.example.eventwise.models.GroupsModel
import com.example.eventwise.services.GatewayApi

class HomeGroupsRepository {

    suspend fun listUserGroups() : List<GroupsModel> {
        return try {
            GatewayApi.gatewayService.listUserGroups().body().orEmpty()
        } catch (e: Exception){
            Log.e("HomeGroups", e.toString())
            listOf()
        }
    }

}
