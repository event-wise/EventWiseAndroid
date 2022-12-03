package com.example.eventwise.screens.home.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewEventItemBinding
import com.example.eventwise.models.EventsModel
import com.example.eventwise.screens.eventdetail.EventDetailActivity

class EventsRecyclerViewAdapter : ListAdapter<EventsModel, EventItemViewHolder>(EventItemDiffCallback) {

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

    fun bind(item: EventsModel) {
        binding.eventItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): EventItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewEventItemBinding.inflate(layoutInflater, parent, false)
            binding.recyclerViewEventItemLayout.setOnClickListener {
                binding.eventItem?.id?.let { eventId ->
                    binding.eventItem?.groupId?.let { groupId ->
                        EventDetailActivity.newInstance(layoutInflater.context,
                            eventId, groupId
                        )
                    }
                }
            }
            return EventItemViewHolder(binding)
        }
    }
}


private object EventItemDiffCallback : DiffUtil.ItemCallback<EventsModel>() {
    override fun areItemsTheSame(oldItem: EventsModel, newItem: EventsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventsModel, newItem: EventsModel): Boolean {
        return oldItem == newItem
    }
}
