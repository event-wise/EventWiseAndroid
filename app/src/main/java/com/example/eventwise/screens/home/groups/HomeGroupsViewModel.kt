package com.example.eventwise.screens.home.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventwise.models.GroupModel

class HomeGroupsViewModel(
    private val homeGroupsRepository: HomeGroupsRepository = HomeGroupsRepository()
) : ViewModel() {

    private val _groupList = MutableLiveData<List<GroupModel>>()

    val groupList: LiveData<List<GroupModel>>
        get() = _groupList

    init {
        refreshGroupList()
    }

    private fun refreshGroupList(){
        _groupList.value = homeGroupsRepository.retrieveMockData()
    }
}
