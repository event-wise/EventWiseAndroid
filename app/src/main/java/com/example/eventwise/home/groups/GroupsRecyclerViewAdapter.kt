package com.example.eventwise.home.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewGroupItemBinding
import com.example.eventwise.models.GroupModel

class GroupsRecyclerViewAdapter : ListAdapter<GroupModel, GroupItemViewHolder>(GroupItemDiffCallback) {

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

    fun bind(item: GroupModel) {
        binding.groupItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): GroupItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewGroupItemBinding.inflate(layoutInflater, parent, false)
            return GroupItemViewHolder(binding)
        }
    }
}


private object GroupItemDiffCallback : DiffUtil.ItemCallback<GroupModel>() {
    override fun areItemsTheSame(oldItem: GroupModel, newItem: GroupModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroupModel, newItem: GroupModel): Boolean {
        return oldItem == newItem
    }
}
