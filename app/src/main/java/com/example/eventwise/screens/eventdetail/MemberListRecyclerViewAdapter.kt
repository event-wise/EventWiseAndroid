package com.example.eventwise.screens.eventdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewMemberItemBinding
import com.example.eventwise.models.MemberModel

class MemberListRecyclerViewAdapter : ListAdapter<String, MemberItemViewHolder>(
    MemberItemDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberItemViewHolder {
        return MemberItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MemberItemViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member)
    }
}


class MemberItemViewHolder private constructor(private val binding: RecyclerViewMemberItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.memberItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MemberItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewMemberItemBinding.inflate(layoutInflater, parent, false)
            return MemberItemViewHolder(binding)
        }
    }
}


private object MemberItemDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
