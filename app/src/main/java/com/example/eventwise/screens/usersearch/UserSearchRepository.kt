package com.example.eventwise.screens.usersearch

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.MemberAddRemoveModel
import com.example.eventwise.models.SearchResponseModel
import com.example.eventwise.services.GatewayApi

class UserSearchRepository {

    suspend fun searchMember(
        groupId: Long,
        search: String
    ) : SearchResponseModel? {
        val request = GatewayApi.gatewayService.searchMember(
            groupId = groupId,
            search = search
        )
        return request.body()
    }

    suspend fun addRemoveMember(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupId: Long,
        userId: Long
    ){
        val request = GatewayApi.gatewayService.addRemoveMember(
            MemberAddRemoveModel(
                groupId = groupId,
                subjectUserId = userId
            )
        )
        if (request.code() in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = request.body()?.success
            if (success.value == false){
                errorMessage.value = request.body()?.message.toString()
            }
        } else {
            success.value = false
            errorMessage.value = request.errorBody().toString()
        }
    }

}
