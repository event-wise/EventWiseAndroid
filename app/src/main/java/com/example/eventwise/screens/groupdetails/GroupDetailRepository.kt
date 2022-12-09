package com.example.eventwise.screens.groupdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.GroupDetailsModel
import com.example.eventwise.services.GatewayApi

class GroupDetailRepository {

    suspend fun getGroupDetails(groupId: Long) : GroupDetailsModel? {
        return try {
            GatewayApi.gatewayService.getGroupDetails(groupId).body()
        } catch (e: Exception){
            Log.e("GroupDetail", e.toString())
            null
        }
    }

    suspend fun exitFromGroup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupId: Long
    ) {
        try {
            val request = GatewayApi.gatewayService.exitFromGroup(groupId)
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
            Log.e("GroupDetail", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }
}
