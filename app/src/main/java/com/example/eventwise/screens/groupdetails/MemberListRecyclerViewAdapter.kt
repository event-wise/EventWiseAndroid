package com.example.eventwise.screens.groupdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewMemberItemSmallTextBinding

class MemberListRecyclerViewAdapter : ListAdapter<String, MemberItemSmallViewHolder>(
    MemberItemDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberItemSmallViewHolder {
        return MemberItemSmallViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MemberItemSmallViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member)
    }
}


class MemberItemSmallViewHolder private constructor(private val binding: RecyclerViewMemberItemSmallTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.memberItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MemberItemSmallViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewMemberItemSmallTextBinding.inflate(layoutInflater, parent, false)
            return MemberItemSmallViewHolder(binding)
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
