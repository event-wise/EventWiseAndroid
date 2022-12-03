package com.example.eventwise.screens.home.groups

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.GroupsModel

@Suppress("unchecked_cast")
@BindingAdapter("groupListBindingAdapter")
fun groupListBindingAdapter(recyclerView: RecyclerView, groupList: List<GroupsModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<GroupsModel, GroupItemViewHolder>
    adapter?.submitList(groupList.orEmpty())
}
