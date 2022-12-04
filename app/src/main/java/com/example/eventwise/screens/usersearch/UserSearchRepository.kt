package com.example.eventwise.screens.usersearch

import com.example.eventwise.models.MemberAddRemoveModel
import com.example.eventwise.models.SearchResponseModel
import com.example.eventwise.services.GatewayApi

class UserSearchRepository {

    suspend fun searchMember(groupId: Long, search: String) : SearchResponseModel? {
        val request = GatewayApi.gatewayService.searchMember(
            groupId = groupId,
            search = search
        )
        return request.body()
    }

    suspend fun addRemoveMember(groupId: Long, userId: Long){
        GatewayApi.gatewayService.addRemoveMember(
            MemberAddRemoveModel(
                groupId = groupId,
                subjectUserId = userId
            )
        )
    }

}
