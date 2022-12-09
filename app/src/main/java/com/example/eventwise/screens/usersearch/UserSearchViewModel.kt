package com.example.eventwise.screens.usersearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserSearchViewModel(
    private val groupId: Long,
    private val userSearchRepository: UserSearchRepository = UserSearchRepository()
) : ViewModel() {

    val usernameSearch: MutableLiveData<String> = MutableLiveData()

    val foundedUserName: MutableLiveData<String> = MutableLiveData()

    val userFound: MutableLiveData<Boolean> = MutableLiveData(false)

    val isMember: MutableLiveData<Boolean> = MutableLiveData(false)

    private var userId: Long? = null

    val errorMessage: MutableLiveData<String> = MutableLiveData(null)
    val success: MutableLiveData<Boolean> = MutableLiveData(false)

    fun searchMember(){
        viewModelScope.launch {
            val searchResponseModel = userSearchRepository.searchMember(
                groupId,
                usernameSearch.value.toString()
            )

            userFound.value = searchResponseModel?.found
            isMember.value = searchResponseModel?.member
            foundedUserName.value = searchResponseModel?.username
            userId = searchResponseModel?.id
        }
    }

    fun addRemoveMember(){
        viewModelScope.launch {
            if (userFound.value == true) {
                userSearchRepository.addRemoveMember(
                    success,
                    errorMessage,
                    groupId,
                    userId ?: 0
                )
            }
        }
    }

}
