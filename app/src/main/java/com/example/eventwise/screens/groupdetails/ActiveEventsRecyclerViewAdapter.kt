package com.example.eventwise.screens.groupdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.databinding.RecyclerViewActiveEventItemBinding
import com.example.eventwise.models.EventModel
import com.example.eventwise.screens.eventdetail.EventDetailActivity


class ActiveEventsRecyclerViewAdapter : ListAdapter<EventModel, ActiveEventItemViewHolder>(
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

    fun bind(item: EventModel) {
        binding.eventItem = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ActiveEventItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerViewActiveEventItemBinding.inflate(layoutInflater, parent, false)
            binding.recyclerViewActiveEventItemLayout.setOnClickListener {
                binding.eventItem?.id?.let { id ->
                    EventDetailActivity.newInstance(layoutInflater.context, id)
                }
            }
            return ActiveEventItemViewHolder(binding)
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
