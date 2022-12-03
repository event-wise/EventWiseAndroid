package com.example.eventwise.screens.groupdetails

import com.example.eventwise.models.EventModel
import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.models.GroupModel
import com.example.eventwise.models.MemberModel
import com.example.eventwise.services.GatewayApi

class GroupDetailRepository {

    suspend fun getGroupDetails(groupId: Long) : GroupDetailsModel? {
        return GatewayApi.gatewayService.getGroupDetails(groupId).body()
    }

    fun generateMockGroup(): GroupModel {
        return GroupModel(1, "Deneme", "Bu bir grouptur", "Tuebingen")
    }

    fun generateMockGroupMembers(): List<MemberModel> {
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

    fun generateMockActiveEventsList(): List<EventModel> {
        return listOf(
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport", null),
        )
    }

    fun generateMockLogList() : List<String> {
        return listOf(
            "Ahmet ise gitti",
            "Ahmet isten dondu",
            "Bu bir logdur",
            "Bu yolun sonu hostur",
            "Sadece izleyelim arkadaslar",
            "ne yaziyorum bilmiyorum valla"
        )
    }

}
