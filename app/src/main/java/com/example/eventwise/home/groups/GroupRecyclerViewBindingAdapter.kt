package com.example.eventwise.home.groups

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.GroupModel

@Suppress("unchecked_cast")
@BindingAdapter("groupListBindingAdapter")
fun groupListBindingAdapter(recyclerView: RecyclerView, groupList: List<GroupModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<GroupModel, GroupItemViewHolder>
    adapter?.submitList(groupList.orEmpty())
}
