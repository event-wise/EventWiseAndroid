package com.example.eventwise.helperfunctions

import com.example.eventwise.models.*
import com.example.eventwise.services.GatewayApi

suspend fun mockDataGenerate(){

    // generate extra 100 users
//    for (i in 1..100)
//        GatewayApi.gatewayService.register(
//            RegisterRequestModel(
//                "DisplayedName$i",
//                "email$i@gmail.com",
//                "Location$i",
//                "string$i",
//                "string$i"
//            )
//        )

//    // generate 100 groups
//    for (i in 1..100)
//        GatewayApi.gatewayService.createGroup(
//            GroupSaveModel("Bu bir gruptur$i", 0, "GroupName$i", "Location$i")
//        )

    // get groups of initial user
    var groups = GatewayApi.gatewayService.listUserGroups().body().orEmpty()

    // add users to the groups
    for (group in groups){
        for (i in 1..100) {
            val userId = GatewayApi.gatewayService.searchMember(group.id, "string$i").body()?.id ?: 0

            GatewayApi.gatewayService.addRemoveMember(
                MemberAddRemoveModel(groupId = group.id, userId)
            )
        }
    }

    // generate 20 events for every group
//    for (group in groups){
//        for (i in 1..20){
//            GatewayApi.gatewayService.createEvent(
//                EventSaveModel(
//                    dateTime = "2022-12-04T12:38:55.226Z",
//                    description = "Bu bir eventtir$i",
//                    eventId = 0,
//                    eventName = "EventName$i",
//                    groupId = group.id ?: 0,
//                    location = "Location$i",
//                    type = "Deneme"
//                )
//            )
//        }
//    }


    for (i in 1..100){
        val token = GatewayApi.gatewayService.login(
            LoginRequestModel("string$i", "string$i")
        ).body()?.token

        val groups = token?.let { GatewayApi.gatewayService.listUserGroups(it).body().orEmpty() }.orEmpty()

        if (i % 3 == 0)
            for (group in groups){
                GatewayApi.gatewayService.getGroupDetails(group.id).body()?.events.orEmpty().forEach {
                    GatewayApi.gatewayService.acceptEvent(it.id, token ?: "Bearer Token")
                }
            }

    }
}
