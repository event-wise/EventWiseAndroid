package com.example.eventwise.screens.groupdetails

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.services.GatewayApi

class GroupDetailRepository {

    suspend fun getGroupDetails(groupId: Long) : GroupDetailsModel? {
        return GatewayApi.gatewayService.getGroupDetails(groupId).body()
    }

    suspend fun exitFromGroup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupId: Long
    ) {
        val request = GatewayApi.gatewayService.exitFromGroup(groupId)
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
