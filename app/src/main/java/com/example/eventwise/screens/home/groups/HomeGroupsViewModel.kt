package com.example.eventwise.screens.home.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventwise.models.GroupModel
import com.example.eventwise.models.GroupsModel
import kotlinx.coroutines.launch

class HomeGroupsViewModel(
    private val homeGroupsRepository: HomeGroupsRepository = HomeGroupsRepository()
) : ViewModel() {

    private val _groupList = MutableLiveData<List<GroupsModel>>()

    val groupList: LiveData<List<GroupsModel>>
        get() = _groupList

    init {
        refreshGroupList()
    }

    private fun refreshGroupList(){
        viewModelScope.launch {
            _groupList.value = homeGroupsRepository.listUserGroups()
        }
    }
}
