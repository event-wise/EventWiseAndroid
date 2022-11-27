package com.example.eventwise.home.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewEventItemBinding
import com.example.eventwise.models.EventModel

class EventsRecyclerViewAdapter : ListAdapter<EventModel, EventItemViewHolder>(EventItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        return EventItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }
}


class EventItemViewHolder private constructor(private val binding: RecyclerViewEventItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventModel) {
        binding.eventItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): EventItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewEventItemBinding.inflate(layoutInflater, parent, false)
            return EventItemViewHolder(binding)
        }
    }
}


private object EventItemDiffCallback : DiffUtil.ItemCallback<EventModel>() {
    override fun areItemsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
        return oldItem == newItem
    }
}
