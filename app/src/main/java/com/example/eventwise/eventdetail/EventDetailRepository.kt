package com.example.eventwise.eventdetail

import com.example.eventwise.models.EventModel
import com.example.eventwise.models.MemberModel

class EventDetailRepository {

    fun createMockData() : EventModel {
        return EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport")
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
}
