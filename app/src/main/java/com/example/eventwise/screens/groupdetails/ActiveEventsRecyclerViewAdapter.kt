package com.example.eventwise.screens.groupdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewActiveEventItemBinding
import com.example.eventwise.models.EventsModel
import com.example.eventwise.screens.eventdetail.EventDetailActivity


class ActiveEventsRecyclerViewAdapter : ListAdapter<EventsModel, ActiveEventItemViewHolder>(
    EventItemDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveEventItemViewHolder {
        return ActiveEventItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ActiveEventItemViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }
}


class ActiveEventItemViewHolder private constructor(private val binding: RecyclerViewActiveEventItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventsModel) {
        binding.eventItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ActiveEventItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewActiveEventItemBinding.inflate(layoutInflater, parent, false)
            binding.recyclerViewActiveEventItemLayout.setOnClickListener {
                binding.eventItem?.id?.let { eventId ->
                    binding.eventItem?.groupId?.let { groupId ->
                        EventDetailActivity.newInstance(layoutInflater.context,
                            eventId, groupId
                        )
                    }
                }
            }
            return ActiveEventItemViewHolder(binding)
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
