package com.example.eventwise.groupdetails

import com.example.eventwise.models.EventModel
import com.example.eventwise.models.GroupModel
import com.example.eventwise.models.MemberModel

class GroupDetailRepository {

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
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
            EventModel(1, "Gym", "Everybody Welcome", "tuebingen", "13.00", "Sport"),
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
