package com.example.eventwise.screens.eventdetail

import com.example.eventwise.models.EventDetailsModel
import com.example.eventwise.models.EventModel
import com.example.eventwise.models.MemberModel
import com.example.eventwise.models.UserModel
import com.example.eventwise.services.Constants
import com.example.eventwise.services.GatewayApi

class EventDetailRepository {

    fun createMockData() : EventModel {
        return EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null)
    }

    fun createMockDataForMembers() : List<MemberModel> {
        return listOf(
            MemberModel(1, "Ahmet"),
            MemberModel(1, "Furkan"),
            MemberModel(1, "Kavraz"),
            MemberModel(1, "Yigit"),
            MemberModel(1, "Mihri"),
            MemberModel(1, "Tumay"),
            MemberModel(1, "Mehmet"),
            MemberModel(1, "Nur")
        )
    }

    suspend fun eventDetailInformation(eventId: Long) : EventDetailsModel? {
        return GatewayApi.gatewayService.getEventDetails(eventId).body()
    }

    suspend fun acceptEvent(eventId: Long) {
        GatewayApi.gatewayService.acceptEvent(eventId)
    }

    suspend fun rejectEvent(eventId: Long) {
        GatewayApi.gatewayService.rejectEvent(eventId)
    }
}
