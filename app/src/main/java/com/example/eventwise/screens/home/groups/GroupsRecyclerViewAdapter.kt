package com.example.eventwise.screens.home.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewGroupItemBinding
import com.example.eventwise.models.GroupModel
import com.example.eventwise.models.GroupsModel
import com.example.eventwise.screens.groupdetails.GroupDetailActivity

class GroupsRecyclerViewAdapter : ListAdapter<GroupsModel, GroupItemViewHolder>(GroupItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupItemViewHolder {
        return GroupItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GroupItemViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }
}


class GroupItemViewHolder private constructor(private val binding: RecyclerViewGroupItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GroupsModel) {
        binding.groupItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): GroupItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewGroupItemBinding.inflate(layoutInflater, parent, false)
            binding.recyclerViewGroupItemLayout.setOnClickListener {
                binding.groupItem?.id?.let { groupId ->
                    GroupDetailActivity.newInstance(layoutInflater.context,
                        groupId
                    )
                }
            }
            return GroupItemViewHolder(binding)
        }
    }
}


private object GroupItemDiffCallback : DiffUtil.ItemCallback<GroupsModel>() {
    override fun areItemsTheSame(oldItem: GroupsModel, newItem: GroupsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroupsModel, newItem: GroupsModel): Boolean {
        return oldItem == newItem
    }
}
