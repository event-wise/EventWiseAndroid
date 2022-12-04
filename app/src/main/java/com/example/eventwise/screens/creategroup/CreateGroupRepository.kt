package com.example.eventwise.screens.creategroup

import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.GroupSaveModel
import com.example.eventwise.services.GatewayApi

class CreateGroupRepository {

    suspend fun createGroup(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupSaveModel: GroupSaveModel
    ) {
        val request = GatewayApi.gatewayService.createGroup(groupSaveModel)

        if (request.code() !in 200..299){
            errorMessage.value = request.errorBody().toString()
            success.value = request.body()?.success
            if (success.value == false){
                errorMessage.value = request.body()?.message.toString()
            }
        } else {
            success.value = true
            errorMessage.value = null
        }
    }
}
