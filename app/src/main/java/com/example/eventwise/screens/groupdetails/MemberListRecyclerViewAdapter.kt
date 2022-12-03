package com.example.eventwise.screens.groupdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewMemberItemSmallTextBinding
import com.example.eventwise.models.MemberModel

class MemberListRecyclerViewAdapter : ListAdapter<MemberModel, MemberItemViewHolder>(
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


class MemberItemViewHolder private constructor(private val binding: RecyclerViewMemberItemSmallTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MemberModel) {
        binding.memberItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MemberItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewMemberItemSmallTextBinding.inflate(layoutInflater, parent, false)
            return MemberItemViewHolder(binding)
        }
    }
}


private object MemberItemDiffCallback : DiffUtil.ItemCallback<MemberModel>() {
    override fun areItemsTheSame(oldItem: MemberModel, newItem: MemberModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MemberModel, newItem: MemberModel): Boolean {
        return oldItem == newItem
    }
}
