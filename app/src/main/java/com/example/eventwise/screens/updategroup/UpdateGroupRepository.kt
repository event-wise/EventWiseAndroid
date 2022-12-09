package com.example.eventwise.screens.updategroup

import android.util.Log
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
        try {
            val request = GatewayApi.gatewayService.updateGroup(
                GroupSaveModel(
                    description = description,
                    groupId = groupId,
                    location = location,
                    groupName = groupName
                )
            )
            if (request.code() in 200..299) {
                errorMessage.value = request.errorBody().toString()
                success.value = request.body()?.success
                if (success.value == false) {
                    errorMessage.value = request.body()?.message.toString()
                }
            } else {
                success.value = false
                errorMessage.value = request.errorBody().toString()

                try {
                    errorMessage.value = request.errorBody()?.let {
                        GatewayApi.errorConverter.convert(it)?.messages?.joinToString("\n")
                    }
                } catch (e: Exception) {
                    Log.e("UpdateGroup", e.toString())
                    errorMessage.value = e.message
                }
            }
        } catch (e: Exception) {
            Log.e("UpdateGroup", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }

    suspend fun deleteGroup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupId: Long
    ){
        try {
            val request = GatewayApi.gatewayService.deleteGroup(groupId)
            if (request.code() in 200..299) {
                errorMessage.value = request.errorBody().toString()
                success.value = request.body()?.success
                if (success.value == false) {
                    errorMessage.value = request.body()?.message.toString()
                }
            } else {
                success.value = false
                errorMessage.value = request.errorBody().toString()

                try {
                    errorMessage.value = request.errorBody()?.let {
                        GatewayApi.errorConverter.convert(it)?.messages?.joinToString("\n")
                    }
                } catch (e: Exception) {
                    errorMessage.value = e.message
                }
            }
        } catch (e: Exception) {
            Log.e("UpdateGroup", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }

    suspend fun getGroupInformation(groupId: Long) : GroupDetailsModel? {
        return try {
            GatewayApi.gatewayService.getGroupDetails(groupId = groupId).body()
        } catch (e: Exception) {
            Log.e("UpdateGroup", e.toString())
            null
        }
    }
}
