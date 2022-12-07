package com.example.eventwise.screens.updategroup

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.models.GroupSaveModel
import com.example.eventwise.services.GatewayApi

class UpdateGroupRepository {

    suspend fun updateGroup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        description: String,
        groupId: Long,
        location: String,
        groupName: String
    ){
        val request = GatewayApi.gatewayService.updateGroup(
            GroupSaveModel(
                description = description,
                groupId = groupId,
                location = location,
                groupName = groupName
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

    suspend fun deleteGroup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupId: Long
    ){
        val request = GatewayApi.gatewayService.deleteGroup(groupId)
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

    suspend fun getGroupInformation(groupId: Long) : GroupDetailsModel? {
        return GatewayApi.gatewayService.getGroupDetails(groupId = groupId).body()
    }
}
