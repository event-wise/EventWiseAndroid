package com.example.eventwise.groupdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewLogItemBinding

class LogListRecyclerViewAdapter : ListAdapter<String, LogItemViewHolder>(LogItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogItemViewHolder {
        return LogItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LogItemViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member)
    }
}


class LogItemViewHolder private constructor(private val binding: RecyclerViewLogItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.logContext = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): LogItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewLogItemBinding.inflate(layoutInflater, parent, false)
            return LogItemViewHolder(binding)
        }
    }
}


private object LogItemDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
