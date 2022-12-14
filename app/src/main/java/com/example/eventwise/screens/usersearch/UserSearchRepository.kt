package com.example.eventwise.screens.usersearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.eventwise.models.MemberAddRemoveModel
import com.example.eventwise.models.SearchResponseModel
import com.example.eventwise.services.GatewayApi

class UserSearchRepository {

    suspend fun searchMember(
        groupId: Long,
        search: String
    ) : SearchResponseModel? {
        return try {
            val request = GatewayApi.gatewayService.searchMember(
                groupId = groupId,
                search = search
            )
            request.body()
        } catch (e: Exception) {
            Log.e("UserSearch", e.toString())
            null
        }
    }

    suspend fun addRemoveMember(
        success: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        groupId: Long,
        userId: Long
    ){
        try {
            val request = GatewayApi.gatewayService.addRemoveMember(
                MemberAddRemoveModel(
                    groupId = groupId,
                    subjectUserId = userId
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
                    errorMessage.value = e.message
                }
            }
        } catch (e: Exception) {
            Log.e("UserSearch", e.toString())
            errorMessage.value = "Something is wront with service!"
        }
    }

}
