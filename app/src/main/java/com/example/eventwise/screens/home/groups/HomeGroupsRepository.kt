package com.example.eventwise.screens.home.groups

import com.example.eventwise.models.GroupsModel
import com.example.eventwise.services.Constants
import com.example.eventwise.services.GatewayApi

class HomeGroupsRepository {

    suspend fun listUserGroups() : List<GroupsModel> {
        return GatewayApi.gatewayService.listUserGroups(Constants.GLOBAL_USER_ID).body().orEmpty()
    }


    fun retrieveMockData() : List<GroupsModel> {
        return listOf(
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere"),
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere"),
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere"),
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere"),
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere"),
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere"),
            GroupsModel( "Deneme", 1, location = "Uskudar"),
            GroupsModel( "Deneme2", 1, location = "Alamanya"),
            GroupsModel( "Deneme3", 1, location = "Ingiltere")
        )
    }
}
